package com.hi.module.self.ui.selfmodule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.ruifeng.hi.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 个人模块的信息展示Fragment 2
 */
public class SelfFragmentTwo extends Fragment {
    protected DisplayImageOptions loadOptions;
    // 图像加载器
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private ImageView frameImage;
    private View mMainView;
    private String headUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment_self_fragment_two,
                (ViewGroup) getActivity().findViewById(R.id.viewPage), false);
        initView();
        initRes();
    }

    public void setImgUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    private void initView() {
        frameImage = (ImageView) mMainView.findViewById(R.id.frameImage);
    }

    private void initRes() {
        loadOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.default_img_head)
                .showImageOnFail(R.drawable.default_img_head)
//		.showStubImage(R.drawable.default_img_head)
                .cacheInMemory(true)
                .cacheOnDisc(true).build();
        imageLoader.displayImage(headUrl,frameImage,loadOptions,null);
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


}
