package com.sbms.shootandevade;

import android.animation.Animator;
import android.widget.RelativeLayout;

/**
 * Handles the movement of projectiles.
 * Created by Marcus on 12/17/2015.
 */
public class ProjectileAnimator implements Animator.AnimatorListener {

	//region Fields
	private GameObject mTarget;
	private RelativeLayout mLayout;
	//endregion

	//region Methods

	public ProjectileAnimator(GameObject target, RelativeLayout layout) {
		mTarget = target;
		mLayout = layout;
	}

	//region Event Handlers
	@Override
	public void onAnimationStart(Animator animator) {
	}

	@Override
	public void onAnimationEnd(Animator animator) {
		mLayout.removeView(mTarget);
	}

	@Override
	public void onAnimationCancel(Animator animator) {
		mLayout.removeView(mTarget);
	}

	@Override
	public void onAnimationRepeat(Animator animator) {
	}
	//endregion

	//endregion
}