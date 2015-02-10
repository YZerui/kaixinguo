package com.hi.view.customDialogStyle;

import android.util.Log;
import android.view.View;



import com.hi.view.customDialogStyle.base.BaseEffects;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by lee on 2014/7/30.
 */
public class FadeIn extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view,"alpha",0,1).setDuration(mDuration)

        );
    }
}
