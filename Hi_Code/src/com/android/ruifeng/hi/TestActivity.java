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
                P.v("获取位置信息成功");
                P.v("经度:"+longitude);
                P.v("纬度:"+latitude);
            }

            @Override
            public void onFail() {
                P.v("获取位置信息失败");
            }

            @Override
            public void onFinally() {
                P.v("获取位置信息结束");
            }
        },this);
    }

}
