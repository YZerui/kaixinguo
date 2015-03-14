package com.hi.module.msg.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.text.Selection;
import android.text.Spannable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.android.ruifeng.hi.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.Session;
import com.avos.avoscloud.SessionManager;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.dialog.Dialog_Select;
import com.dialog.Dialog_Select.callBack_Dialog;
import com.dialog.animstyle.Effectstype;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.format.utils.Md5Util;
import com.hi.adapter.EmotionGridAdapter;
import com.hi.adapter.EmotionPagerAdapter;
import com.hi.adapter.MsgDetailItemAdapter;
import com.hi.adapter.MsgDetailItemAdapter.callBack;
import com.hi.common.db.E_DB_ImgUrlMode;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.http.E_Http_SendState;
import com.hi.common.param.Enum_ListLimit;
import com.hi.common.param.Enum_Page;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.model.T_Msg;
import com.hi.dao.supImpl.Dao_Friends;
import com.hi.dao.supImpl.Dao_IMsg;
import com.hi.dao.supImpl.Dao_IMsgSeq;
import com.hi.dao.supImpl.Dao_MsgSeq;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.application.ShareUnitDialog;
import com.hi.module.base.face.ChatEmoji;
import com.hi.module.base.face.FaceAdapter;
import com.hi.module.base.face.FaceConversionUtil;
import com.hi.module.base.face.FaceFileUtils;
import com.hi.module.base.img.ImageLoader;
import com.hi.module.base.img.ImageSelectPage;
import com.hi.module.base.img.ImageTotalPage;
import com.hi.module.base.superClass.ListActivity;
import com.hi.module.locale.ui.UserDetailFragmentActivity;
import com.hi.module.msg.service.ChatService;
import com.hi.module.msg.service.ChatUtils;
import com.hi.module.msg.service.MsgAgent;
import com.hi.module.msg.service.MsgBuilder;
import com.hi.receiver.IMessageReceiver;
import com.hi.receiver.IMessageReceiver.MessageListener;
import com.hi.receiver.model.IMMessageBean.Enum_IM_MsgFormat;
import com.hi.receiver.model.IMMessageBean.Enum_IM_MsgType;
import com.hi.receiver.model.IMMessageBean.IMMessage;
import com.hi.service.IMMessageService;
import com.hi.service.base.Call_DBListData;
import com.hi.service.db.DBReq_IMsgList;
import com.hi.service.image.Enum_ImgScaleType;
import com.hi.service.image.UploadImageService;
import com.hi.service.image.UploadImageService.CallBack;
import com.hi.utils.AnimationUtil;
import com.hi.utils.ClipboardUtils;
import com.hi.utils.DBUtils;
import com.hi.utils.DeviceUtils;
import com.hi.utils.EmotionUtils;
import com.hi.utils.FormatUtils;
import com.hi.utils.PathUtils;
import com.hi.utils.ScreenShot;
import com.hi.utils.ViewHandleUtils;
import com.hi.utils.network.Connectivity;
import com.hi.utils.network.NetAsyncTask;
import com.hi.view.EmotionEditText;
import com.hi.view.KeyboardListenRelativeLayout;
import com.hi.view.KeyboardListenRelativeLayout.IOnKeyboardStateChangedListener;
import com.hi.view.scaleImg.DragImgShowActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.utils.Util;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;

/**
 * 显示对话详情的页面
 *
 * @author MM_Zerui
 * @tip_1 当返回键触发时，将对未读数量进行情况（根据当前对方UID）
 */
public class MsgDetailActivity extends ListActivity implements MessageListener, ImageSelectPage.ImageSelectListener {
    private static final int IMAGE_REQUEST = 100;
    private static final int TAKE_CAMERA_REQUEST = 101;
    private String MSG_EDIT_TEXT = "MSG_EDIT_TEXT";
    @ViewInject(R.id.topBar)
    private CustomTopbarView topBar;
    @ViewInject(R.id.fly_msg_send_btn)
    private Button sendBtn;
    @ViewInject(R.id.fly_msg_edit_text)
    private EmotionEditText sendEditText;
    @ViewInject(R.id.fly_msg_send_btn_layout)
    private KeyboardListenRelativeLayout kebBoardLayout;
    @ViewInject(R.id.xListView)
    private XListView xListView;
    @ViewInject(R.id.chatAddLayout)
    private LinearLayout chatAddLayout;
    @ViewInject(R.id.edit_more)
    private ImageView editMore;
    @ViewInject(R.id.edit_face)
    private ImageView editFace;
    @ViewInject(R.id.emotionPager)
    private ViewPager emotionPager;
    @ViewInject(R.id.chatEmotionLayout)
    private LinearLayout chatEmotionLayout;

    private MsgDetailItemAdapter adapter;
    private List<T_IMsg> items;

    private String sendMsg;
    private String userId;
    private String convid;
    private String head;
    private String oppoName;
    private List<T_IMsg> flyMsgList;
    private int msgSize;
    private String PAGE_NOTE;
    private String localCameraPath = PathUtils.getTmpPath();
    // private ChatNotifyReceive chatReceive;
    private InputMethodManager iMM;
    private IMMessageService IMService = new IMMessageService();
    private DBReq_IMsgList dbReq;
    private Dialog_Select dialog_Select;
    private int PAGE_SIZE = Enum_ListLimit.MSG_LIST.value();
    String accostNote = "对方还未回复,稍后再来搭讪哦";
    private MsgAgent msgAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setContentView(R.layout.msg_detail_page);
        ViewUtils.inject(this);
        super.onCreate(savedInstanceState);
        registerReceive();
        initEmotionPager();
//        ImageSelectPage.ifSelect = false;// 标识图片未选取
    }

    /**
     * 注册监听器
     */
    private void registerReceive() {
        // TODO Auto-generated method stub
        // 注册监听器
        // chatReceive = new ChatNotifyReceive();
        // IntentFilter filter_system = new IntentFilter();
        // filter_system.addAction(Enum_Page.MSG_DETAIL.name());
        // registerReceiver(chatReceive, filter_system);
    }

    @Override
    protected void obtainIntentValue() {
        // TODO Auto-generated method stub
        userId = getIntent().getStringExtra("DATA0");
        head = getIntent().getStringExtra("DATA1");
        oppoName = getIntent().getStringExtra("DATA2");
        PAGE_NOTE = getIntent().getStringExtra("DATA3");

        MSG_EDIT_TEXT = userId;
    }

    @Override
    protected void setOnClickListener() {
        // TODO Auto-generated method stub
        kebBoardLayout
                .setOnKeyboardStateChangedListener(new IOnKeyboardStateChangedListener() {

                    @Override
                    public void onKeyboardStateChanged(int state) {
                        // TODO Auto-generated method stub
                        switch (state) {
                            case KeyboardListenRelativeLayout.KEYBOARD_STATE_HIDE:// 软键盘隐藏
                                System.out.println("键盘隐藏");
                                break;
                            case KeyboardListenRelativeLayout.KEYBOARD_STATE_SHOW:// 软键盘显示
                                System.out.println("键盘显示");
                                chatAddLayout.setVisibility(View.GONE);
                                chatEmotionLayout.setVisibility(View.GONE);
                                xListView.setSelection(items.size() - 1);
                                break;
                            default:
                                break;
                        }
                    }
                });

        topBar.setCallBack(new topBarCallBack() {
            @Override
            public void call_backBtnListener() {
                // TODO Auto-generated method stub
                super.call_backBtnListener();
                outFinish();
            }

            @Override
            public void call_rightTextBtnListener() {
                // TODO Auto-generated method stub
                super.call_rightTextBtnListener();
                new RunnableService(new runCallBack() {

                    @Override
                    public void start() {
                        // TODO Auto-generated method stub
                        String filePath = ScreenShot
                                .shoot(MsgDetailActivity.this);
                        DBUtils.setSharedPreStr(context, "SHAREIMG", filePath);
                    }

                    @Override
                    public void end() {
                        // 跳转到分享页面
                        AnimationUtil.in2TopIntent(context,
                                ShareUnitDialog.class);
                    }
                }, false);
            }
        });
        xListView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                // TODO Auto-generated method stub
                final int posi = position - 1;
                final T_IMsg iMsg = items.get(posi);
                dialog_Select.setCallBack(new callBack_Dialog() {
                    @Override
                    public void click_btn1() {
                        // TODO Auto-generated method stub
                        super.click_btn1();
                        // 复制
                        ClipboardUtils.copy(iMsg.getMsg(), context);
                        toast.setText("复制成功");
                        dialog_Select.dismiss();
                    }

                    @Override
                    public void click_btn2() {
                        // TODO Auto-generated method stub
                        super.click_btn2();
                        // 删除
                        Dao_IMsg.updateMsgState(E_Http_SendState.HIDE,
                                iMsg.getObjectId(), iMsg.getUid());
                        items.remove(posi);
                        adapter.notifyDataSetChanged();
                        dialog_Select.dismiss();
                    }
                }).withShow();
                return false;
            }
        });
    }

    @Override
    protected void initResource() {
        // 判断对方是否在线
        // if(IMService.isOnLine(userId)){
        // topBar.setViceTitle("[在线]");
        // }else {
        // topBar.setViceTitle("[不在线]");
        // }
        msgSize = PAGE_SIZE;
        convid = ChatUtils.convid(Dao_SelfIfo.getInstance().getMid(), userId);
        msgAgent = new MsgAgent(userId);
        // 判断是否有未编辑文本
        dialog_Select = new Dialog_Select(context).withTitle("对内容进行以下操作")
                .withBtn_1("复制").withBtn_2("删除").withAnimat(Effectstype.Fadein);
        String editText = DBUtils.getSharedPreStr(context, MSG_EDIT_TEXT, "");
        sendEditText.setText(editText);
        sendEditText.setSelection(editText.length());
        // 消除未读数
        Dao_IMsgSeq.clearUnRead(userId);
        // watch对方
        IMService.watchPeer(userId);
        if (PAGE_NOTE != null && PAGE_NOTE.equals("USER_DETAIL_PAGE")) {
            topBar.setBackText("他的主页");
        }
        topBar.setTitle(oppoName);
        // 初始隐藏键盘
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        items = new ArrayList<T_IMsg>();
        adapter = new MsgDetailItemAdapter(this);
        adapter.setData(items);
        xListView.setAdapter(adapter);
        flyMsgList = new ArrayList<T_IMsg>();
        iMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        xListView.setHttpCallBack(new listHttpCallBack() {
            @Override
            public void initListView() {
                // TODO Auto-generated method stub
                xListView.setMode(false, true);
//                dbReq = new DBReq_IMsgList(callBack).onUserId(userId);
//                handler.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        dbReq.onInit();
//                    }
//                }, 300);
                loadMsgsFromDB(true, true);
            }

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
//				dbReq.onLoad();
                msgSize += PAGE_SIZE;
                loadMsgsFromDB(false, true);
//                new GetDataTask(context,false,false).execute();
            }

            @Override
            public void onLoadMore() {
                // TODO Auto-generated method stub

            }

        });
        adapter.setCallBack(new callBack() {

            @Override
            public void onImageClick(int position) {
                // TODO Auto-generated method stub
                T_IMsg item = items.get(position);
                String url = "";
                if (item.getImageUrlMode() == E_DB_ImgUrlMode.LOCAL.value()) {
                    url = "file://" + item.getMsg();
                } else {
                    url = item.getMsg();
                }
                AnimationUtil.nor_toIntent(context, DragImgShowActivity.class,
                        url);
            }

            @Override
            public void onHeadClick(int position) {
                // TODO Auto-generated method stub
                try {
                    T_IMsg item = items.get(position);
                    if (item.getIdentity() == E_DB_MsgSource.USER.value()) {
                        AnimationUtil.in2LeftIntent(context,
                                UserDetailFragmentActivity.class,
                                item.getUid(), item.getHead(), item.getName(),
                                Enum_Page.MSG_DETAIL.name());
                    } else {
                        AnimationUtil.in2LeftIntent(context,
                                UserDetailFragmentActivity.class, Dao_SelfIfo
                                        .getInstance().getMid(), Dao_SelfIfo
                                        .getInstance().getHead(), Dao_SelfIfo
                                        .getInstance().getNickName(),
                                Enum_Page.MSG_DETAIL.name());
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    toast.setText("信息有误,无法查看对方详情");
                }
            }
        });
    }

    @OnClick(R.id.edit_more)
    public void onEditMoreClick(View v) {
        // 弹出更多选项编辑框
        if (chatAddLayout.getVisibility() == View.VISIBLE) {
            chatAddLayout.setVisibility(View.GONE);
        } else {
            chatAddLayout.setVisibility(View.VISIBLE);

            chatEmotionLayout.setVisibility(View.GONE);
            // 隐藏键盘
            // Util.hideSoftInputView(this);
            iMM.hideSoftInputFromWindow(sendEditText.getWindowToken(), 0);
            xListView.setSelection(items.size() - 1);
        }

    }

    @OnClick(R.id.addImageBtn)
    public void onAddImgClick(View v) {
        AnimationUtil.in2TopIntent_result(MsgDetailActivity.this,
                ImageTotalPage.class, IMAGE_REQUEST);
    }

    @OnClick(R.id.addCameraBtn)
    public void onAddCameraClick(View v) {
        // 拍摄取图
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = Uri.fromFile(new File(localCameraPath));
        P.v(localCameraPath);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(openCameraIntent, TAKE_CAMERA_REQUEST);
        overridePendingTransition(0, R.anim.out_right_to_left_page);
    }

    @OnClick(R.id.addLocationBtn)
    public void onAddLocationClick(View v) {
        // 添加位置
    }

    @OnClick(R.id.edit_face)
    public void onEditFaceClick(View v) {
        // 弹出表情选项框
        if (chatEmotionLayout.getVisibility() == View.VISIBLE) {
            chatEmotionLayout.setVisibility(View.GONE);
        } else {
            chatEmotionLayout.setVisibility(View.VISIBLE);

            chatAddLayout.setVisibility(View.GONE);
            // 隐藏键盘
            // Util.hideSoftInputView(this);
            iMM.hideSoftInputFromWindow(sendEditText.getWindowToken(), 0);
            xListView.setSelection(items.size() - 1);
        }
    }

    private void initEmotionPager() {
        // List<String> list=FaceFileUtils.getEmojiFile(context);
        final List<List<ChatEmoji>> emojis = FaceConversionUtil.getInstace().emojiLists;
        List<View> views = new ArrayList<View>();
        List<FaceAdapter> faceAdapters = new ArrayList<FaceAdapter>();
        for (int i = 0; i < emojis.size(); i++) {
            GridView view = new GridView(context);
            FaceAdapter adapter = new FaceAdapter(context, emojis.get(i));
            view.setAdapter(adapter);
            faceAdapters.add(adapter);

            view.setNumColumns(7);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setPadding(5, 0, 5, 0);
            view.setSelector(new ColorDrawable(Color.TRANSPARENT));
            view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.FILL_PARENT));
            view.setGravity(Gravity.CENTER);
            // view.setOnItemClickListener(this);
            view.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    ChatEmoji chatEmoji = (ChatEmoji) parent.getAdapter()
                            .getItem(position);
                    if (chatEmoji.getId() == R.drawable.face_del_icon) {
                        int selection = sendEditText.getSelectionStart();
                        String text = sendEditText.getText().toString();
                        if (selection < 1) {
                            return;
                        }
                        String text2 = text.substring(selection - 1);
                        if ("]".equals(text2)) {
                            int start = text.lastIndexOf("[");
                            int end = selection;
                            sendEditText.getText().delete(start, end);
                            return;
                        }
                        sendEditText.getText().delete(selection - 1, selection);
                        return;
                    }
                    String emotionText = chatEmoji.getFaceName();
                    int start = sendEditText.getSelectionStart();
                    StringBuffer sb = new StringBuffer(sendEditText.getText());
                    sb.replace(sendEditText.getSelectionStart(),
                            sendEditText.getSelectionEnd(), emotionText);
                    sendEditText.setText(sb.toString());
                    CharSequence info = sendEditText.getText();
                    if (info instanceof Spannable) {
                        Spannable spannable = (Spannable) info;
                        Selection.setSelection(spannable,
                                start + emotionText.length());
                    }
                }
            });
            views.add(view);
        }
        EmotionPagerAdapter pagerAdapter = new EmotionPagerAdapter(views);
        emotionPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_CAMERA_REQUEST:
                    // 将图像信息显示在列表上
                    if (!checkAccostAvailable()) {
                        topBar.onNoteText(accostNote);
                        return;
                    }
                    msgAgent.createAndSendMsg(new MsgAgent.MsgBuilderHelper() {
                        @Override
                        public void specifyType(MsgBuilder msgBuilder) {
                            msgBuilder.sendImg(localCameraPath, E_DB_ImgUrlMode.LOCAL.value());
                        }

                        @Override
                        public void setFromUserInfo(MsgBuilder msgBuilder) {
                            msgBuilder.setFromIdInfo(oppoName, head);
                        }
                    }, new DefaultSendCallback() {
                        @Override
                        public void onSuccess(T_IMsg msg) {
                            super.onSuccess(msg);
                        }
                    });

//                    T_IMsg msg = new T_IMsg();
//                    msg.setIdentity(E_DB_MsgSource.SELF.value());
//                    msg.setMid(Dao_SelfIfo.getInstance().getMid());
//                    msg.setSendState(E_Http_SendState.NORMAL.value());
//                    msg.setMsg(localCameraPath);
//                    msg.setMsgType(Enum_IM_MsgType.ACCOST.value());
//                    msg.setUid(userId);
//                    msg.setMsgFormat(Enum_IM_MsgFormat.IMAGE.value());
//                    msg.setHead(head);
//                    msg.setName(oppoName);
//                    msg.setObjectId(Md5Util.myUUID());
//                    msg.setImageUrlMode(E_DB_ImgUrlMode.LOCAL.value());// 标识为本地图片路径
//                    items.add(msg);
//                    adapter.notifyDataSetChanged();
//                    xListView.setSelection(items.size() - 1);
//                    getImageByPath(localCameraPath);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    private void getImageByPath(String localSelectPath) {
//        // 压缩处理
//        final String newPath = PathUtils.getChatFilePath(Md5Util.myUUID());
//        ViewHandleUtils.compressImage(localSelectPath, newPath);
//        UploadImageService service = new UploadImageService();
//        service.setHeight(400).setImageType(Enum_ImgScaleType.TYPE_0)
//                .setImagePath(newPath).uploadImage(new CallBack() {
//
//            @Override
//            public void onSuccess(String url) {
//                // TODO Auto-generated method stub
//                imgSendMethod(url);
//            }
//
//            @Override
//            public void onStart() {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onFinally() {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onFail() {
//                // TODO Auto-generated method stub
//                T_IMsg iMsg = items.get(items.size() - 1);
//                iMsg.setName("发送失败");
//                iMsg.setTime(FormatUtils.getCurrentDateValue_long());
//                iMsg.setSendState(E_Http_SendState.FAIL.value());
//                adapter.notifyDataSetChanged();
//                MsgSendFail(iMsg);
//            }
//        });
//    }

    /**
     * 图片发送方法
     *
     * @param url
     */
    private void imgSendMethod(String url) {
        P.v("发送图片url:" + url);

        T_IMsg iMsg = items.get(items.size() - 1);
        if (url == null) {
            // 提示发送失败
            // toast.setText("图像错误，无法发送");

            iMsg.setName("发送失败");
            iMsg.setTime(FormatUtils.getCurrentDateValue_long());
            iMsg.setSendState(E_Http_SendState.FAIL.value());
            adapter.notifyDataSetChanged();
            MsgSendFail(iMsg);
            return;
        }
        IMService.setContent(url).setMessageFormat(Enum_IM_MsgFormat.IMAGE)
                .setMessagePeerId(userId).setAnimId(userId)
                .setObjectId(iMsg.getObjectId())
                .setMessageType(Enum_IM_MsgType.ACCOST).send();
    }

//    public Call_DBListData<T_IMsg> callBack = new Call_DBListData<T_IMsg>() {
//
//        @Override
//        public void onStart() {
//
//        }
//
//        @Override
//        public void onLoad(List<T_IMsg> datas) {
//            flyMsgList.clear();
//            flyMsgList = datas;
//            for (T_IMsg item : flyMsgList) {
//                items.add(0, item);
//            }
//            handlerExtend.onLoadView();
//        }
//
//        @Override
//        public void onInit(List<T_IMsg> datas) {
//            items.clear();
//            // items = datas;
//            // Collections.reverse(items);
//            flyMsgList.clear();
//            flyMsgList = datas;
//            for (T_IMsg item : flyMsgList) {
//                items.add(0, item);
//            }
//            handlerExtend.onInitView();
//        }
//
//        @Override
//        public void onFinally() {
//            handlerExtend.onFinally();
//        }
//
//        @Override
//        public void onFail() {
//            handlerExtend.onFail();
//        }
//    };
//    private HandlerExtend handlerExtend = new HandlerExtend(
//            new handleCallBack() {
//
//                @Override
//                public void call_onInit() {
//                    // TODO Auto-generated method stub
//                    xListView.setSelection(items.size() - 1);
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void call_onLoad() {
//                    // TODO Auto-generated method stub
//                    adapter.notifyDataSetChanged();
//                }
//
//                public void call_onFail() {
//
//                }
//
//                ;
//
//                public void call_onLoadNull() {
//
//                }
//
//                ;
//
//                public void call_onFinally() {
//                    xListView.onLoadStop();
//                }
//
//                ;
//            });

    /**
     * 发送消息
     *
     * @param v
     */
    @OnClick(R.id.fly_msg_send_btn)
    public void msgSendClick(View v) {
        iMM.hideSoftInputFromWindow(sendEditText.getWindowToken(), 0);
        msgSendMethod();
    }

    private void msgSendMethod() {
        // TODO Auto-generated method stub
        sendMsg = sendEditText.getText().toString();
        if (!DataValidate.checkDataValid(sendMsg)) {
            toast.setText("请输入你的搭讪");
            return;
        }
        if (!isStateFine()) {
            return;
        }
        if (!checkAccostAvailable()) {
            topBar.onNoteText(accostNote);
            return;
        }

        msgAgent.createAndSendMsg(new MsgAgent.MsgBuilderHelper() {
            @Override
            public void specifyType(MsgBuilder msgBuilder) {
                msgBuilder.sendText(sendMsg);
            }

            @Override
            public void setFromUserInfo(MsgBuilder msgBuilder) {
                msgBuilder.setFromIdInfo(oppoName, head);
            }
        }, new DefaultSendCallback() {
            @Override
            public void onSuccess(T_IMsg msg) {
                super.onSuccess(msg);
                // 发送后清空编辑框
                sendEditText.setText("");
            }
        });

//		T_IMsg msgBean = new T_IMsg();
//		// 用于初始列表
//		msgBean.setSendState(E_Http_SendState.NORMAL.value());
//		msgBean.setHead(head);
//		msgBean.setName(oppoName);
//		msgBean.setMsg(sendMsg);
//		msgBean.setTime(FormatUtils.getCurrentDateValue_long());
//		msgBean.setIdentity(E_DB_MsgSource.SELF.value());
//		msgBean.setMsgType(Enum_IM_MsgType.ACCOST.value());
//		msgBean.setMsgFormat(Enum_IM_MsgFormat.TEXT.value());
//		msgBean.setMid(Dao_SelfIfo.getInstance().getMid());
//		msgBean.setUid(userId);
//		msgBean.setObjectId(Md5Util.myUUID());
//		items.add(msgBean);
//		adapter.notifyDataSetChanged();
//		xListView.setSelection(items.size() - 1);
//		// 发送
//		IMService.setContent(msgBean.getMsg())
//				.setMessageFormat(Enum_IM_MsgFormat.TEXT)
//				.setMessageType(Enum_IM_MsgType.ACCOST).setAnimId(userId)
//				.setObjectId(msgBean.getObjectId()).setMessagePeerId(userId)
//				.send();
    }

    /**
     * 图片是否选取
     *
     * @param isSelect
     */
    @Override
    public void isImageSelect(boolean isSelect) {
        if(!isSelect){
            topBar.onNoteText("未选取图片");
            return;
        }
        if (!isStateFine()) {
            return;
        }
        if (!checkAccostAvailable()) {
            topBar.onNoteText(accostNote);
            return;
        }
        msgAgent.createAndSendMsg(new MsgAgent.MsgBuilderHelper() {
                @Override
                public void specifyType(MsgBuilder msgBuilder) {
                    msgBuilder.sendImg(ImageSelectPage.imgPath,E_DB_ImgUrlMode.LOCAL.value());
                }

                @Override
                public void setFromUserInfo(MsgBuilder msgBuilder) {
                    msgBuilder.setFromIdInfo(oppoName,head);
                }
            },new DefaultSendCallback(){
                @Override
                public void onSuccess(T_IMsg msg) {
                    super.onSuccess(msg);
                }
            });

    }

    class DefaultSendCallback implements MsgAgent.SendCallback {
        @Override
        public void onError(Exception e) {
            e.printStackTrace();
            loadMsgsFromDB(false, false);
        }

        @Override
        public void onStart(T_IMsg msg) {
            loadMsgsFromDB(true, false);
        }

        @Override
        public void onSuccess(T_IMsg msg) {
            loadMsgsFromDB(false, false);
        }
    }

    private MsgAgent.SendCallback sendCallback = new DefaultSendCallback();

    public boolean isStateFine() {
        if (Connectivity.isConnected(context) == false) {
            ViewHandleUtils.toastNormal("请检查网络环境");
            return false;
        } else if (ChatService.isSessionPaused()) {
//            ctx.sendBroadcast(new Intent(RETRY_ACTION));
            ViewHandleUtils.toastNormal("会话暂停，请等待网络良好");
            return false;
        } else {
            return true;
        }
    }
//	@Override
//	public void onIMSendMessage(String sendTime) {
//		T_IMsg iMsg = items.get(items.size() - 1);
//		iMsg.setSendState(E_Http_SendState.SUCCESS.value());
//		iMsg.setTime(Long.valueOf(sendTime));
//		iMsg.setWifiMac(DeviceUtils.getWifiMac());
//		if (iMsg.getMsgFormat() == Enum_IM_MsgFormat.IMAGE.value()) {
//			iMsg.setMsg(IMService.getiMMessageBean().getContent());
//			iMsg.setImageUrlMode(E_DB_ImgUrlMode.URL.value());
//		}
//		// items.get(items.size()-1).setMsgId(datas.getId());
//		adapter.notifyDataSetChanged();
//		// 保持最后一项的可见状态
//		xListView.setSelection(items.size() - 1);
//
//		MsgSendSuccess(iMsg);
//	}

    private void MsgSendSuccess(T_IMsg items) {
        // 存入本地数据库
        Dao_IMsg.addMessage(items, false, true);
        // 更新搭讪状态
        Dao_Friends.updateAccostNum(userId, E_DB_MsgSource.SELF.value());
    }

//	@Override
//	public void onIMRecvMessage(IMMessage msgBean, String recvTime) {
//		// TODO Auto-generated method stub
//		// 收到数据后直接存入
//		T_IMsg iMsg = new T_IMsg();
//		iMsg.setHead(msgBean.getU_head());
//		iMsg.setIdentity(E_DB_MsgSource.USER.value());
//		iMsg.setMid(Dao_SelfIfo.getInstance().getMid());
//		iMsg.setMsg(msgBean.getM_content());
//		iMsg.setMsgFormat(msgBean.getM_formate());
//		iMsg.setMsgType(msgBean.getM_type());
//		iMsg.setName(msgBean.getU_name());
//		iMsg.setSendState(E_Http_SendState.SUCCESS.value());
//		iMsg.setTime(Long.valueOf(recvTime));
//		iMsg.setUid(msgBean.getU_sendID());
//		iMsg.setWifiMac(DeviceUtils.getWifiMac());
//		iMsg.setImageUrlMode(E_DB_ImgUrlMode.URL.value());
//		iMsg.setObjectId(msgBean.getObjectId());
//		items.add(iMsg);
//		adapter.notifyDataSetChanged();
//		xListView.setSelection(items.size() - 1);
//
//		// Dao_IMsg.addMessage(iMsg, false, true);
//	}

//	@Override
//	public void onIMFail() {
//		// TODO Auto-generated method stub
//		P.v("发送失败");
//		T_IMsg iMsg = items.get(items.size() - 1);
//		// iMsg.setName("发送失败");
//		iMsg.setSendState(E_Http_SendState.FAIL.value());
//		adapter.notifyDataSetChanged();
//		MsgSendFail(iMsg);
//	}

    private void MsgSendFail(T_IMsg iMsg) {
        // 存入本地数据库
        Dao_IMsg.addMessage(iMsg, false, false);
    }

    /**
     * 监测和该用户间是否可以继续搭讪（连续搭讪次数是否已经超过限制）
     *
     * @return
     */
    private boolean checkAccostAvailable() {
        List<T_IMsg> list = Dao_IMsg.getMessageNum(userId, 3);
        if (!DataValidate.checkDataValid(list)) {
            return true;
        }
        int accostNum = 0;
        for (T_IMsg item : list) {
            if (item.getIdentity() == E_DB_MsgSource.SELF.value()) {
                accostNum++;
            }
        }
        if (accostNum < 3) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 清空该用户的未读数量

        outFinish();
    }

    @Override
    protected void outFinish() {
        // TODO Auto-generated method stub
        finish();
        if (PAGE_NOTE != null && PAGE_NOTE.equals("USER_DETAIL_PAGE")) {
            AnimationUtil.finishOut2Bottom(context);
            return;
        }
        AnimationUtil.finishOut2Right(context);
        String editText = sendEditText.getText().toString();
        if (editText != null) {
            DBUtils.setSharedPreStr(context, MSG_EDIT_TEXT, editText);
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
//		IMessageReceiver.registerSessionListener(userId, this);
        ImageSelectPage.addImageSelect(this);
        IMessageReceiver.addMsgListener(this);
//        ImageLoader.getInstance().recycleCache();
//        if(ImageSelectPage.ifSelect){
//            msgAgent.createAndSendMsg(new MsgAgent.MsgBuilderHelper() {
//                @Override
//                public void specifyType(MsgBuilder msgBuilder) {
//                    msgBuilder.sendImg(ImageSelectPage.imgPath,E_DB_ImgUrlMode.LOCAL.value());
//                }
//
//                @Override
//                public void setFromUserInfo(MsgBuilder msgBuilder) {
//                    msgBuilder.setFromIdInfo(oppoName,head);
//                }
//            },new DefaultSendCallback(){
//                @Override
//                public void onSuccess(T_IMsg msg) {
//                    super.onSuccess(msg);
//                }
//            });
//
//        }
//        if (ImageSelectPage.ifSelect) {
//            if (!checkAccostAvailable()) {
//                topBar.onNoteText(accostNote);
//                return;
//            }
//            String localSelectPath = ImageSelectPage.imgPath;
//            // final String newPath =
//            // PathUtils.getChatFilePath(Md5Util.myUUID());
//            // ViewHandleUtils.compressImage(localSelectPath, newPath);
//            // 将图像信息显示在列表上
//            T_IMsg msg = new T_IMsg();
//            msg.setSendState(E_Http_SendState.NORMAL.value());
//            msg.setIdentity(E_DB_MsgSource.SELF.value());
//            msg.setMid(Dao_SelfIfo.getInstance().getMid());
//            msg.setMsg(localSelectPath);
//            msg.setMsgType(Enum_IM_MsgType.ACCOST.value());
//            msg.setUid(userId);
//            msg.setObjectId(Md5Util.myUUID());
//            msg.setMsgFormat(Enum_IM_MsgFormat.IMAGE.value());
//            msg.setHead(head);
//            msg.setName(oppoName);
//            msg.setImageUrlMode(E_DB_ImgUrlMode.LOCAL.value());// 标识为本地图片路径
//            items.add(msg);
//            xListView.setSelection(items.size() - 1);
//            adapter.notifyDataSetChanged();
//            getImageByPath(localSelectPath);
//            ImageSelectPage.ifSelect = false;
//        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
//        IMessageReceiver.unregisterSessionListener(userId);
        IMessageReceiver.removeMsgListener(this);

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        // 取消监听器的注册
        // unregisterReceiver(chatReceive);

        // unwatch对方
        IMService.unwatchPeer(userId);
        ImageSelectPage.removeSelectListener(this);
    }

    @Override
    public boolean onMessageUpdate(String otherId, T_IMsg msgBean) {
        if (otherId.equals(userId)) {
            if (msgBean != null) {
                Dao_IMsg.addMessage(msgBean, false, true);
            }
            loadMsgsFromDB(true, false);
            return true;
        }
        return false;
    }

    private void loadMsgsFromDB(boolean b, boolean isRefresh) {
        new GetDataTask(context, false, b, isRefresh).execute();
    }

    @Override
    public void onOnLine(List<String> peerIds) {
        // TODO Auto-generated method stub
        // if(peerIds.contains(userId)){
        // topBar.setViceTitle("[在线]");
        // }
    }

    @Override
    public void onOffLine(List<String> peerIds) {
        // TODO Auto-generated method stub
        // if(peerIds.contains(userId)){
        // topBar.setViceTitle("[不在线]");
        // }
    }

    class GetDataTask extends NetAsyncTask {
        boolean scrollToLast = true;
        boolean isRefresh = true;
        List<T_IMsg> datas = new ArrayList<>();

        GetDataTask(Context ctx, boolean isDialog, boolean scroolToLast, boolean isRefresh) {
            super(ctx, isDialog);
            this.scrollToLast = scroolToLast;
            this.isRefresh = isRefresh;
        }

        @Override
        protected void doInBack() throws Exception {
            flyMsgList = Dao_IMsg.getMessage2(convid, msgSize);
            for (T_IMsg item : flyMsgList) {
                datas.add(0, item);
            }
        }

        @Override
        protected void onPost(Exception e) {
            if (e != null) {
                ViewHandleUtils.toastNormal("网络环境不佳");
            }
            if (!DataValidate.checkDataValid(items)) {
                items = new ArrayList<>();
            }
            addMsgsAndRefresh(datas, scrollToLast);
        }

        private void addMsgsAndRefresh(List<T_IMsg> msgs, boolean scrollToLast) {
            int lastN = adapter.getCount();
            int newN = msgs.size();
            items = msgs;
            adapter.setData(items);
            adapter.notifyDataSetChanged();
            if (scrollToLast) {
                scrollToLast();
            } else {
                xListView.setSelection(newN - lastN - 1);
//                if (lastN == newN) {
//                    ViewHandleUtils.toastNormal("加载完毕");
//                }
            }
            xListView.onLoadStop();
            P.v("本次加载数:" + (newN - lastN));
            if (isRefresh && (newN - lastN < PAGE_SIZE)) {
                xListView.setPullRefreshEnable(false);
            } else {
                xListView.setPullRefreshEnable(true);
            }
        }

    }

    private void scrollToLast() {
        xListView.setSelection(xListView.getCount() - 1);
    }

    // class ChatNotifyReceive extends BroadcastReceiver {
    //
    // @Override
    // public void onReceive(Context context, Intent intent) {
    // // TODO Auto-generated method stub
    // if (intent.getAction().equals(Enum_Page.MSG_DETAIL.name())) {
    // // 获得发送方的ID
    // try {
    // if (userId.equals(intent.getStringExtra("DATA"))) {
    // updateChatView();
    // } else {
    // FunUtils.AcquireWakeLock(context);
    // }
    // } catch (Exception e) {
    // // TODO: handle exception
    // P.v("更新对话列表错误");
    // }
    // }
    // }
    // }

    /**
     * 更新对话列表
     *
     * @步骤 获取当前列表最新的对话记录(msgId值), 根据msgId值从数据库中获取剩下的记录
     * @步骤 根据获取的数据刷新当前列表
     */
    // private void updateChatView() {
    // // TODO Auto-generated method stub
    // // dbReq.onInit();
    // try {
    // T_IMsg msgBean = items.get(items.size() - 1);
    // List<T_Msg> listDatas = Dao_Msg.getRecordsById(msgBean.getUid(),
    // msgBean.getMsgId());
    // items.addAll(listDatas);
    // adapter.notifyDataSetChanged();
    // } catch (Exception e) {
    // // TODO: handle exception
    // P.v(e.getMessage());
    // }
    //
    // }

}
