package com.hi.module.self.ui.selfmodule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ruifeng.hi.R;

/**
 * 个人模块的信息展示Fragment 2
 */
public class SelfFragmentTwo extends Fragment {


    private View mMainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment_self_fragment_two,
                (ViewGroup) getActivity().findViewById(R.id.viewPage), false);

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
