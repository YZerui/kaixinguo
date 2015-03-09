package com.hi.module.self.ui.selfmodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.writing.W_UserInfo;
import com.hi.dao.model.T_SelfIfo;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 个人模块的信息展示Fragment 1
 */
public class SelfFragmentOne extends Fragment {
    private View mMainView;
    private TextView textName, textAge, textXingZuo, textCharmValue, textState;
    private String mName, mAge, mXingZuo, mCharmValue, mState, mHead;
    private ImageView imgHead;
    private Context context;
    protected DisplayImageOptions loadOptions;
    // 图像加载器
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment_self_fragment_one,
                (ViewGroup) getActivity().findViewById(R.id.viewPage), false);
        context = getActivity();
        initView();
        initRes();
        invalidete();
    }

    private void initRes() {
        loadOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.default_img_head)
                .showImageOnFail(R.drawable.default_img_head)
//		.showStubImage(R.drawable.default_img_head)
                .cacheInMemory(true)
                .cacheOnDisc(true).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup group = (ViewGroup) mMainView.getParent();
        if (group != null) {
            group.removeAllViewsInLayout();
        }
        return mMainView;
    }

    private void initView() {
        textName = (TextView) mMainView.findViewById(R.id.text_name);
        textAge = (TextView) mMainView.findViewById(R.id.text_age);
        textCharmValue = (TextView) mMainView.findViewById(R.id.text_charmValue);
        textXingZuo = (TextView) mMainView.findViewById(R.id.text_xingzuo);
        textState = (TextView) mMainView.findViewById(R.id.text_state);
        imgHead = (ImageView) mMainView.findViewById(R.id.img_head);
    }

    public void initData(String textName, String textAge, String textCharmValue
            , String textXingZuo, String textState, String imgHead) {
        this.mName = textName;
        this.mAge = textAge;
        this.mCharmValue = textCharmValue;
        this.mXingZuo = textXingZuo;
        this.mState = textState;
        this.mHead = imgHead;
    }

    private void invalidete() {
        //个人信息的初始化
        T_SelfIfo infoBean = Dao_SelfIfo.getInstance();
        W_UserInfo.Default(E_DB_SelfIfo.nickName, this.textName, infoBean.getNickName());
//        W_UserInfo.Default(E_DB_SelfIfo.occupation, fragmentOne.getT, infoBean.getOccupation());
//        W_UserInfo.Default(E_DB_SelfIfo.note, labelItem.getContent(), infoBean.getNote());
        W_UserInfo.Default(E_DB_SelfIfo.currentState, this.textState, infoBean.getCurrentState());
        W_UserInfo.Default(E_DB_SelfIfo.xingzuo, this.textXingZuo, infoBean.getXingZuo());
        W_UserInfo.Default(E_DB_SelfIfo.age, this.textAge, infoBean.getAge());
        imageLoader.displayImage(infoBean.getHead(), this.imgHead, loadOptions, null);
//        if (infoBean.getSex().equals(E_Http_Gender.MAN.toString())) {
//            selfGenderImg.setImageResource(R.drawable.weibo_gender_m);
//        } else if (infoBean.getSex().equals(E_Http_Gender.WOMEN.toString())) {
//            selfGenderImg.setImageResource(R.drawable.weibo_gender_f);
//        } else {
//            selfGenderImg.setVisibility(View.INVISIBLE);
//        }
    }

    public TextView getTextName() {
        return textName;
    }

    public TextView getTextAge() {
        return textAge;
    }

    public TextView getTextXingZuo() {
        return textXingZuo;
    }

    public TextView getTextCharmValue() {
        return textCharmValue;
    }

    public TextView getTextState() {
        return textState;
    }

    public ImageView getImgHead() {
        return imgHead;
    }

    public void setTextName(TextView textName) {
        this.textName = textName;
    }

    public void setTextAge(TextView textAge) {
        this.textAge = textAge;
    }

    public void setTextXingZuo(TextView textXingZuo) {
        this.textXingZuo = textXingZuo;
    }

    public void setTextCharmValue(TextView textCharmValue) {
        this.textCharmValue = textCharmValue;
    }

    public void setTextState(TextView textState) {
        this.textState = textState;
    }

    public void setImgHead(ImageView imgHead) {
        this.imgHead = imgHead;
    }


}
