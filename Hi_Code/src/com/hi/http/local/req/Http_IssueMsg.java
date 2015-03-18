package com.hi.http.local.req;

import java.io.File;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.common.http.E_Http_Port;
import com.hi.common.http.E_Http_QNType;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.local.model.Req_issueMsg;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.GetLocPointService;
import com.hi.service.HttpResultService;
import com.hi.service.image.UploadImageService;
import com.hi.utils.DeviceUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * ∑¢≤º¡Ù—‘≤Ÿ◊˜
 *
 * @author MM_Zerui
 *
 */
public class Http_IssueMsg extends HttpRequestClass<Req_issueMsg, Class<?>> {

	private Req_issueMsg reqBean;
    private String m_long;
    private String m_lat;
	public Http_IssueMsg(Call_httpData<Class<?>> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal = callBack;
	}

	@Override
	public HttpRequestClass onParams(Req_issueMsg reqBean) {
		// TODO Auto-generated method stub
		this.reqBean = reqBean;

		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
        new GetLocPointService(new GetLocPointService.CallBack_Loc() {
            @Override
            public void getLocPoint(double longitude, double latitude, String city) {
                m_long=String.valueOf(longitude);
                m_lat=String.valueOf(latitude);
            }

            @Override
            public void onFail() {
                m_long="";
                m_lat="";
            }

            @Override
            public void onFinally() {


                reqBean.setM_long(m_long);
                reqBean.setM_lat(m_lat);
                //≈–∂œ «∑Ò”–ƒ⁄»›
                if (DataValidate.checkDataValid(reqBean.getContent())) {
//                    params.addBodyParameter("content",reqBean.getContent());
                    reqBean.setContent(reqBean.getContent());
                }
                //≈–∂œ «∑Ò”–Õº∆¨
                if (DataValidate.checkDataValid(reqBean.getImgUrl())) {
                    new UploadImageService().setUploadType(E_Http_QNType.LEAVEMSG)
                            .setImagePath(reqBean.getImgUrl())
                            .uploadImage(new UploadImageService.CallBack() {
                                @Override
                                public void onStart() {

                                }

                                @Override
                                public void onFail() {

                                }

                                @Override
                                public void onSuccess(String url) {
                                    reqBean.setImgUrl(url);
                                }

                                @Override
                                public void onFinally() {
                                    httpAction();
                                }
                            });
//                    params.addBodyParameter("img", new File(reqBean.getImg()));
//                    P.v("¡Ù—‘«ΩÕº∆¨¬∑æ∂:"+reqBean.getImg());
                }else {
                    httpAction();
                }
//                http.send(HttpMethod.POST, E_Http_Port.LEAVE_MSG_ISSUE.value(),reqBean,
//                        new RequestCallBack<String>() {
//                            @Override
//                            public void onStart() {
//                                // TODO Auto-generated method stub
//                                super.onStart();
//                                call_normal.onStart();
//                            }
//
//                            @Override
//                            public void onFailure(HttpException arg0, String arg1) {
//                                // TODO Auto-generated method stub
//                                call_normal.onFail();
//                                call_normal.onFinally();
//                            }
//
//                            @Override
//                            public void onSuccess(ResponseInfo<String> arg0) {
//                                // TODO Auto-generated method stub
//                                new HttpResultService(arg0.result,
//                                        new httpResultCallBack<Class<?>>() {
//
//                                            @Override
//                                            public void onRequestFail() {
//                                                // TODO Auto-generated method stub
//                                                call_normal.onFail();
//                                            }
//
//                                            @Override
//                                            public void onSuccess() {
//                                                // TODO Auto-generated method stub
//                                                call_normal.onSuccess(null);
//                                            }
//
//                                            @Override
//                                            public void onFinally() {
//                                                // TODO Auto-generated method stub
//                                                call_normal.onFinally();
//                                            }
//                                        }, Req_issueMsg.class, false);
//                            }
//                        });
            }
        },null);

	}
    public void httpAction(){
        RequestParams params = new RequestParams();
        params.addBodyParameter("uid", reqBean.getUid());
        params.addBodyParameter("wifiMac",reqBean.getWifiMac());
        params.addBodyParameter("m_long",reqBean.getM_long());
        params.addBodyParameter("m_lat",reqBean.getM_lat());
        params.addBodyParameter("content",reqBean.getContent());
        params.addBodyParameter("imgUrl",reqBean.getImgUrl());
        http.send(HttpMethod.POST,E_Http_Port.LEAVE_MSG_ISSUE.value(),params,new RequestCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                call_normal.onStart();
            }

            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                new HttpResultService(objectResponseInfo.result,
                        new httpResultCallBack<Class<?>>() {

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
            public void onFailure(HttpException e, String s) {
                call_normal.onFail();
                call_normal.onFinally();
            }
        });
//        httpAction(E_Http_Port.LEAVE_MSG_ISSUE.value(), reqBean, new RequestCallBack<String>() {
//            @Override
//            public void onStart() {
//                super.onStart();
//                call_normal.onStart();
//            }
//
//            @Override
//            public void onSuccess(ResponseInfo<String> arg) {
//                ResponseInfo<String> arg2 = arg;
//                new HttpResultService(arg2.result,
//                        new httpResultCallBack<Class<?>>() {
//
//                            @Override
//                            public void onRequestFail() {
//                                // TODO Auto-generated method stub
//                                call_normal.onFail();
//                            }
//
//                            @Override
//                            public void onSuccess() {
//                                // TODO Auto-generated method stub
//                                call_normal.onSuccess(null);
//                            }
//
//                            @Override
//                            public void onFinally() {
//                                // TODO Auto-generated method stub
//                                call_normal.onFinally();
//                            }
//                        }, null, false);
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                call_normal.onFail();
//                call_normal.onFinally();
//            }
//        });
    }

}
