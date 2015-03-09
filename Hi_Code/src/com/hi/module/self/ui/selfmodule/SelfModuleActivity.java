package com.hi.module.self.ui.selfmodule;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_Gender;
import com.hi.common.writing.W_UserInfo;
import com.hi.dao.model.T_SelfIfo;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.module.self.ui.IfoSetListPage;
import com.hi.utils.AnimationUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class SelfModuleActivity extends FragmentActivity {
    @ViewInject(R.id.topBar)
    private CustomTopbarView topBar;
    @ViewInject(R.id.viewPage)
    private ViewPager viewPage;

    protected DisplayImageOptions loadOptions;
    // 图像加载器
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private SelfFragmentOne fragmentOne;
    private SelfFragmentTwo fragmentTwo;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPageAdapter pagerAdapter = new ViewPageAdapter(getSupportFragmentManager());
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_module);
        ViewUtils.inject(this);
        context=this;
        obtainIntentValue();
        initResource();
        onClickListener();

    }

    public void obtainIntentValue() {

    }

    public void initResource() {
        fragmentOne = new SelfFragmentOne();
        fragmentTwo = new SelfFragmentTwo();
        fragmentList.add(fragmentOne);
        fragmentList.add(fragmentTwo);
        viewPage.setAdapter(pagerAdapter);
//        loadOptions = new DisplayImageOptions.Builder()
//                .showImageForEmptyUri(R.drawable.default_img_head)
//                .showImageOnFail(R.drawable.default_img_head)
////		.showStubImage(R.drawable.default_img_head)
//                .cacheInMemory(true)
//                .cacheOnDisc(true).build();

    }

    public void onClickListener() {
        topBar.setCallBack(new topBarCallBack() {
            @Override
            public void call_rightTextBtnListener() {
                super.call_rightTextBtnListener();
                //跳转到个人设置页面
                AnimationUtil.tab_in2LeftIntent(context, IfoSetListPage.class);
            }
        });
    }

    public class ViewPageAdapter extends FragmentPagerAdapter {

        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }


}

