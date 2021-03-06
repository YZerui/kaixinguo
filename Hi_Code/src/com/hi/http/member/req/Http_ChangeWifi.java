package com.hi.http.member.req;

import android.os.Looper;

import com.exception.utils.P;
import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Req_ChangeWifi;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.GetLocPointService;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * ���¸���״̬
 *
 * @author MM_Zerui
 */
public class Http_ChangeWifi extends HttpRequestClass<Req_ChangeWifi, Class> {
    private Req_ChangeWifi reqBean;

    public Http_ChangeWifi(Call_httpData<Class> callBack) {
        // TODO Auto-generated constructor stub
        this.call_normal = callBack;
    }

    @Override
    public HttpRequestClass onParams(Req_ChangeWifi reqBean) {
        // TODO Auto-generated method stub
        this.reqBean = reqBean;
        return this;
    }

    @Override
    public HttpRequestClass onInit() {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public HttpRequestClass onLoad() {
        // TODO Auto-generated method stub
        return this;
    }


    @Override
    public void onAction() {
        // TODO Auto-generated method stub
        try {
            Looper.prepare();
        } catch (Exception e) {
            P.v(e.getMessage());
        }
        new GetLocPointService(new GetLocPointService.CallBack_Loc() {
            @Override
            public void getLocPoint(double longitude, double latitude, String city) {
                reqBean.setM_lat(String.valueOf(latitude));
                reqBean.setM_long(String.valueOf(longitude));
            }

            @Override
            public void onFail() {
                reqBean.setM_lat("");
                reqBean.setM_long("");
            }

            @Override
            public void onFinally() {
                httpAction(E_Http_Port.UPDATE_STATES.value(), reqBean, new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        // TODO Auto-generated method stub
                        new HttpResultService(arg0.result, new httpResultCallBack<Class>() {

                            @Override
                            public void onRequestFail() {
                                // TODO Auto-generated method stub
                                call_normal.onFail();
                            }

                            @Override
                            public void onSuccess() {
                                // TODO Auto-generated method stub
                                call_normal.onSuccess(null);
                            }

                            @Override
                            public void onFinally() {
                                // TODO Auto-generated method stub
                                call_normal.onFinally();
                            }
                        }, null, false);
                    }

                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        // TODO Auto-generated method stub
                        call_normal.onFail();
                        call_normal.onFinally();
                    }
                });
            }
        }, null);

    }

}
