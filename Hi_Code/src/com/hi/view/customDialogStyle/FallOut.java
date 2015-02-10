package com.hi.view.customDialogStyle;

import android.view.View;



import com.hi.view.customDialogStyle.base.BaseEffects;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by lee on 2014/7/31.
 */
public class FallOut extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 1, 3).setDuration(mDuration),
                ObjectAnimator.ofFloat(view,"scaleY",1,3).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(mDuration*3/2)

        );
    }
}
