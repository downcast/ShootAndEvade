package com.sbms.shootandevade;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents each view displayed on the screen. Consists of the Player, Projectiles and Enemies.
 * Created by Marcus on 12/17/2015.
 */
public class GameObject extends ImageView {

	//region Fields
	private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
	public static final int TYPE_PLAYER = 0;
	public static final int TYPE_PLAYER_PROJECTILE = 1;
	public static final int TYPE_ENEMY = 2;

	private int mType;

	//endregion

	//region Methods

	//region constructors
	public GameObject(Context context, int type) {
		super(context);
		mType = type;
	}

	public GameObject(Context context) {
		super(context);
	}

	public GameObject(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	//endregion

	//region Setters/Getters

	/**
	 * Automatically set the id of the view
	 */
	protected void setId() {
		setId(generateViewId());
	}

	protected void setType(int type) {
		mType = type;
	}

	public int getType(){
		return mType;
	}
	//endregion

	/**
	 * Generate a value suitable for use for View.setId().
	 * This value will not collide with ID values generated at build time by aapt for R.id.
	 * <br>
	 * Taken from the android source code for backward compatibility for API < 17.
	 *
	 * @return a generated ID value
	 */
	public static int generateViewId() {
		for (; ; ) {
			final int result = sNextGeneratedId.get();
			// aapt-generated IDs have the high byte nonzero; clamp to the range under that.
			int newValue = result + 1;
			if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
			if (sNextGeneratedId.compareAndSet(result, newValue)) {
				return result;
			}
		}
	}

	//endregion
}
