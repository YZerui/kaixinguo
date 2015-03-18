package com.android.ruifeng.hi;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.exception.utils.P;
import com.hi.service.GetLocPointService;


public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new GetLocPointService(new GetLocPointService.CallBack_Loc() {
            @Override
            public void getLocPoint(double longitude, double latitude, String city) {
                P.v("��ȡλ����Ϣ�ɹ�");
                P.v("����:"+longitude);
                P.v("γ��:"+latitude);
            }

            @Override
            public void onFail() {
                P.v("��ȡλ����Ϣʧ��");
            }

            @Override
            public void onFinally() {
                P.v("��ȡλ����Ϣ����");
            }
        },this);
    }

}
