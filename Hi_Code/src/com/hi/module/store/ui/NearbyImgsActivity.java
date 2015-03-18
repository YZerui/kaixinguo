package com.hi.module.store.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.adapter.LeaveNoteAdapter;
import com.hi.common.http.E_Http_Praise;
import com.hi.common.param.Enum_Page;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.Call_httpListData;
import com.hi.http.local.model.Recv_obtMsg;
import com.hi.http.local.model.Req_obtMsg;
import com.hi.http.local.model.Req_praiseMsg;
import com.hi.http.local.req.Http_obtMsg;
import com.hi.http.local.req.Http_praiseMsg;
import com.hi.module.base.superClass.ListActivity;
import com.hi.module.locale.ui.UserDetailFragmentActivity;
import com.hi.module.locale.ui.leavenote.LeaveNoteEditActivity;
import com.hi.module.locale.ui.leavenote.LeaveNotePraisePage;
import com.hi.service.GetLocPointService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DeviceUtils;
import com.hi.view.customLayout.CustomToast;
import com.hi.view.scaleImg.DragImgShowActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.thread.HandlerExtend;
import com.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 附近照片墙
 */
public class NearbyImgsActivity extends ListActivity {
    @ViewInject(R.id.topBar)
    private CustomTopbarView topBar;
    @ViewInject(R.id.xListView)
    private XListView xListView;
    @ViewInject(R.id.pageView)
    private CustomPageView pageView;

    private LeaveNoteAdapter adapter;
    private Http_obtMsg reqHttp;
    private List<Recv_obtMsg> recvList = new ArrayList<Recv_obtMsg>();
    private Req_obtMsg reqBean = new Req_obtMsg();
    private int favourPosition;
    private String m_long,m_lat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_nearby_imgs);
        ViewUtils.inject(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void obtainIntentValue() {

    }

    @Override
    protected void setOnClickListener() {
        topBar.setCallBack(new topBarCallBack() {
            @Override
            public void call_backBtnListener() {
                super.call_backBtnListener();
                outFinish();
            }
        });
    }

    @Override
    protected void initResource() {
        // TODO Auto-generated method stub
        context = this;
        adapter = new LeaveNoteAdapter(context);
        pageView.setProgress("努力获取中...").setVisibility(View.VISIBLE);
        xListView.setHttpCallBack(new XListView.listHttpCallBack() {

            @Override
            public void initListView() {
                // TODO Auto-generated method stub
                xListView.setMode(true, true);
                adapter.setCallBack(call);
                adapter.setDatas(recvList);
                xListView.setAdapter(adapter);

                reqBean.setMid(Dao_SelfIfo.getInstance().getMid());
                //获取当前经纬度
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
//                        reqBean.setWifiMac(DeviceUtils.getWifiMac());// 测试用
                        reqBean.setM_lat(m_lat);
                        reqBean.setM_long(m_long);
                        reqHttp = (Http_obtMsg) new Http_obtMsg(callBack).onParams(
                                reqBean).onInit();
                    }
                },context);

            }

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                reqHttp.onInit();
            }

            @Override
            public void onLoadMore() {
                // TODO Auto-generated method stub
                reqHttp.onLoad();
            }

        });
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                AnimationUtil.tab_in2LeftIntent(context,
                        LeaveNotePraisePage.class, recvList.get(position - 1)
                                .getId());
            }
        });
//        sendBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                if(!DataValidate.checkDataValid(DeviceUtils.getWifiMac())){
//                    new CustomToast(getActivity()).setText("未连接wifi，无法留言哦");
//                    return;
//                }
//                AnimationUtil.tab_in2TopIntent_result(getActivity(), LeaveNoteEditActivity.class,
//                        LeaveNoteFragment.LEAVENOTE);
//            }
//        });
    }

    private Call_httpListData<Recv_obtMsg> callBack = new Call_httpListData<Recv_obtMsg>() {

        @Override
        public void onStart() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onInit(List<Recv_obtMsg> datas) {
            // TODO Auto-generated method stub
            recvList.clear();
            recvList.addAll(datas);
//			adapter.setDatas(recvList);
            handlerExtend.onInitView();
        }

        @Override
        public void onLoad(List<Recv_obtMsg> datas) {
            // TODO Auto-generated method stub
            recvList.addAll(datas);
            handlerExtend.onLoadView();
        }

        @Override
        public void onFinally() {
            // TODO Auto-generated method stub
            handlerExtend.onFinally();
        }

        @Override
        public void onFail() {
            // TODO Auto-generated method stub
            if (reqHttp.isIfInit()) {
                handlerExtend.onFail();
            } else {
                handlerExtend.onLoadNull();
            }
        }
    };
    private HandlerExtend handlerExtend = new HandlerExtend(
            new HandlerExtend.handleCallBack() {
                @Override
                public void call_onInit() {
                    // TODO Auto-generated method stub
                    pageView.setVisibility(View.GONE);
                    xListView.setVisibility(View.VISIBLE);
//					xListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void call_onLoad() {
                    // TODO Auto-generated method stub
                    pageView.setVisibility(View.GONE);
                    xListView.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }

                public void call_onFail() {
                    xListView.setVisibility(View.GONE);
                    pageView.setTextOnly("快来留下你的留言吧")
                            .setBottomLayoutVisible(false)
                            .setVisibility(View.VISIBLE);
                };

                public void call_onFinally() {
                    xListView.onLoadStop();
                };

            });
    private LeaveNoteAdapter.callBack call = new LeaveNoteAdapter.callBack() {

        @Override
        public void onImageClick(int position) {
            // AnimationUtil.nor_toIntent(context, ImageViewPage.class, recvList
            // .get(position).getImg());
            AnimationUtil.nor_toIntent(context, DragImgShowActivity.class,
                    recvList.get(position).getImg());
        }

        @Override
        public void onHeadClick(int position) {
            // TODO Auto-generated method stub
            //点击头像进入对方主页
            Recv_obtMsg recvBean=recvList.get(position);
            AnimationUtil.tab_in2LeftIntent(context,UserDetailFragmentActivity.class,
                    recvBean.getMid(),recvBean.getHead(),recvBean.getNickName(), Enum_Page.LEAVENOTE.name());
        }

        @Override
        public void onFavour(int position, boolean isCheck) {
            // TODO Auto-generated method stub
            Recv_obtMsg recvBean = recvList.get(position);
            Req_praiseMsg reqBean = new Req_praiseMsg();
            reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
            reqBean.setMwID(recvBean.getId());
            favourPosition = position;
            if (isCheck) {
                reqBean.setType(E_Http_Praise.PRAISE.toString());
                new Http_praiseMsg(callPraise).onParams(reqBean).onAction();
            } else {
                reqBean.setType(E_Http_Praise.UNPRAISE.toString());
                new Http_praiseMsg(callUnPraise).onParams(reqBean).onAction();
            }

        }
    };
    private Call_httpData<Class<?>> callPraise = new Call_httpData<Class<?>>() {

        @Override
        public void onSuccess(Class<?> datas) {
            Recv_obtMsg recvBean = recvList.get(favourPosition);
            recvBean.setType(E_Http_Praise.PRAISE.toString());
            recvBean.setFavourNum(getFavourNum(true, recvBean.getFavourNum()));
        }

        @Override
        public void onFail() {
            Recv_obtMsg recvBean = recvList.get(favourPosition);
            recvBean.setType(E_Http_Praise.UNPRAISE.toString());
//			recvBean.setFavourNum(getFavourNum(false, recvBean.getFavourNum()));
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onFinally() {
            adapter.notifyDataSetChanged();
        }

    };

    private Call_httpData<Class<?>> callUnPraise = new Call_httpData<Class<?>>() {

        @Override
        public void onSuccess(Class<?> datas) {
            // TODO Auto-generated method stub
            Recv_obtMsg recvBean = recvList.get(favourPosition);
            recvBean.setType(E_Http_Praise.UNPRAISE.toString());
            recvBean.setFavourNum(getFavourNum(false, recvBean.getFavourNum()));
        }

        @Override
        public void onFail() {
            // TODO Auto-generated method stub
            Recv_obtMsg recvBean = recvList.get(favourPosition);
            recvBean.setType(E_Http_Praise.PRAISE.toString());
//			recvBean.setFavourNum(getFavourNum(true, recvBean.getFavourNum()));
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onFinally() {
            adapter.notifyDataSetChanged();
        }

    };

    private String getFavourNum(boolean isPlus, String favourNum) {
        try {
            if (isPlus) {
                return String.valueOf(Integer.valueOf(favourNum) + 1);
            }
            if (Integer.valueOf(favourNum) <= 0) {
                return "0";
            }
            return String.valueOf(Integer.valueOf(favourNum) - 1);
        } catch (Exception e) {
            // TODO: handle exception
            return favourNum;
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (LeaveNoteEditActivity.issueSuccess) {
            reqBean.setWifiMac(DeviceUtils.getWifiMac());
            reqHttp.onInit();
            LeaveNoteEditActivity.issueSuccess = false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        P.v(getClass().getName() + "  OnActivityResult");
        switch (requestCode) {
//            case LEAVENOTE:
//                if (LeaveNoteEditActivity.issueSuccess) {
//                    reqHttp.onInit();
//                }
//                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initCallBack() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void outFinish() {
        finish();
        AnimationUtil.finishOut2Right(context);
    }


}
