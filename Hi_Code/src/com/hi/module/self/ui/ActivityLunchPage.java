package com.hi.module.self.ui;



import com.android.ruifeng.hi.R;

import com.hi.common.EXCEPTION_CODE;
import com.hi.module.base.application.AppManager;
import com.hi.utils.AnimationUtil;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author MM_Zerui �����������ݵı༭ҳ��
 */
public class ActivityLunchPage extends Activity implements OnClickListener {
	private EditText msgEditText;
	private TextView msgCount;
	private RelativeLayout backBtn;
	private ImageView msgSend;
	private Context context;
	private String UID,head,nickName,sendMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.self_activity_lunch_page);
		AppManager.getAppManager().addActivity(this);
		obtIntentValue();
		initView();
		initOnClick();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		backBtn = (RelativeLayout) findViewById(R.id.backLayout);
		msgSend = (ImageView) findViewById(R.id.fly_msg_send);
		msgEditText = (EditText) findViewById(R.id.fly_msg_content);
		msgCount = (TextView) findViewById(R.id.fly_msg_count);
		context = this;
	}

	private void initOnClick() {
		// TODO Auto-generated method stub
		msgSend.setOnClickListener(this);
		backBtn.setOnClickListener(this);
		msgEditText.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				temp = s;
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void afterTextChanged(Editable s) {
				int number = s.length();
				msgCount.setText(String.valueOf(number));
				selectionStart = msgEditText.getSelectionStart();
				selectionEnd = msgCount.getSelectionEnd();
				if (temp.length() > 140) {
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					msgEditText.setText(s);
					msgEditText.setSelection(tempSelection);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backLayout:
			finish();
			AnimationUtil.finishOut2Bottom(context);
			break;
		case R.id.fly_msg_send:
			msgSendMethod();
			break;

		default:
			break;
		}
	}

	/**
	 * �ύ���Եķ���
	 */
	private void msgSendMethod() {
		// TODO Auto-generated method stub
//		sendMsg = msgEditText.getText().toString();
//		if (sendMsg.equals("") || sendMsg == null) {
//			Toast.makeText(context, "��������Ĵ�ڨ", Toast.LENGTH_LONG).show();
//			return;
//		}
//		ReqSendFlyMsg reqMsgBean = new ReqSendFlyMsg();
//		reqMsgBean.setRid(UID);
//		System.out.println("���ͷ�uID:"+DBUtils.getUid());
//		System.out.println("���շ�uID:"+UID);
//		reqMsgBean.setUid(DBUtils.getUid());
//		reqMsgBean.setMsg(sendMsg);
//		HttpService service = new HttpService(API.LOCAL_SENDMSG, reqMsgBean,
//				handler);
//		new Thread(service).run();
	}

	/**
	 * ���Intent��������ֵ �������û�mid
	 */
	private void obtIntentValue() {
		// TODO Auto-generated method stub
		Intent intent=getIntent();
		UID=intent.getStringExtra("DATA0");
		head=intent.getStringExtra("DATA1");
		nickName=intent.getStringExtra("DATA2");
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		AnimationUtil.finishOut2BottomLightly(context);
	}

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			// �����ֳ��û��ɹ�
			case EXCEPTION_CODE.REQUEST_SUCCESS:
				Toast.makeText(context, "�ɹ���ڨ", Toast.LENGTH_LONG).show();
				NoteSendSuccess();
				
				break;
			case EXCEPTION_CODE.REQUEST_DATANULL:
				Toast.makeText(context, "����ʧ��", Toast.LENGTH_LONG).show();
				break;
			case EXCEPTION_CODE.REQUEST_DATAERROR:
				Toast.makeText(context, "���ݳ��ִ���", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}

		
	};
	/**
	 * �����Գɹ��ύ��Ĵ���
	 */
	private void NoteSendSuccess() {
		// TODO Auto-generated method stub
//		DaoFlyMsgBean daoBean=new DaoFlyMsgBean();
//		daoBean.setUid(UID);
//		daoBean.setHead(head);
//		daoBean.setIdentity("��");
//		//����Ӧ�ô洢�Լ�������
//		daoBean.setName(DBUtils.getName());
//		daoBean.setMsg(sendMsg);
//		daoBean.setTime(FormatUtils.getDateTime());
//		FlyMsgDaoImpl daoImpl=new FlyMsgDaoImpl(context);
//		daoImpl.insertMsg(daoBean);
//		finish();
//		AnimationUtil.finishOut2BottomLightly(context);
	};
}
