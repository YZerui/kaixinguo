package com.hi.view.customDialogStyle.base;

import com.hi.view.customDialogStyle.FadeIn;
import com.hi.view.customDialogStyle.Fall;
import com.hi.view.customDialogStyle.FallOut;
import com.hi.view.customDialogStyle.FlipH;
import com.hi.view.customDialogStyle.FlipV;
import com.hi.view.customDialogStyle.NewsPaper;
import com.hi.view.customDialogStyle.RotateBottom;
import com.hi.view.customDialogStyle.RotateLeft;
import com.hi.view.customDialogStyle.Shake;
import com.hi.view.customDialogStyle.SideFall;
import com.hi.view.customDialogStyle.SlideBottom;
import com.hi.view.customDialogStyle.SlideLeft;
import com.hi.view.customDialogStyle.SlideRight;
import com.hi.view.customDialogStyle.SlideTop;
import com.hi.view.customDialogStyle.Slit;


public enum Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    FallOut(FallOut.class),
    Sidefill(SideFall.class);
	private Class<? extends BaseEffects> effectsClazz;
	
	private Effectstype(Class<? extends BaseEffects> mclass) {
		effectsClazz = mclass;
	}

	public BaseEffects getAnimator() {
		BaseEffects bEffects = null;
		try {
			bEffects = effectsClazz.newInstance();
		} catch (ClassCastException e) {
			throw new Error("Can not init animatorClazz instance");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new Error("Can not init animatorClazz instance");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new Error("Can not init animatorClazz instance");
		}
		return bEffects;
	}
}
