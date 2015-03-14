package com.hi.module.base.application;

import java.util.Stack;

import com.exception.utils.P;
import com.hi.utils.Benchmark;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * Ӧ�ó���Activity�����ࣺ����Activity�����Ӧ�ó����˳�
 *
 * @version 1.0
 * @created 2012-3-21
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * ��һʵ��
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * ���Activity����ջ
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        if(activityStack.contains(activity)){
            return;
        }
        activityStack.add(activity);
    }

    /**
     * ��ȡ��ǰActivity����ջ�����һ��ѹ��ģ�
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * ������ǰActivity����ջ�����һ��ѹ��ģ�
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * ����ָ����Activity
     */
    public void finishActivity(Activity activity) {

        if (activity != null) {
            activityStack.remove(activity);
            P.v("�������:"+activity.getLocalClassName());
            activity.finish();
            activity = null;

        }
    }

    /**
     * ����ָ��������Activity
     */
    public void finishActivity(Class<?> cls) {
        try {
//			synchronized (activityStack) {
            P.v("����Activity:" + cls.getName());
            Benchmark.start("APP");
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
            Benchmark.end("APP");
//			}
        } catch (Exception e) {
            // TODO: handle exception
            P.v("����Activity�쳣:" + e.getMessage());
            finishActivity(cls);
        }
    }

    /**
     * ��������Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * �˳�Ӧ�ó���
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}