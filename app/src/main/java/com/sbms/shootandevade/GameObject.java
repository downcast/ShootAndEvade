package com.sbms.shootandevade;

import android.content.Context;
import android.widget.ImageView;

/**
 * Represents each view displayed on the screen. Consists of the Player, Projectiles and Enemies.
 * Created by Marcus on 12/17/2015.
 */
public class GameObject extends ImageView {

	public static final int TYPE_PLAYER = 0;
	public static final int TYPE_PLAYER_PROJECTILE = 1;
	public static final int TYPE_ENEMY = 2;

	private int mType;

	public GameObject(Context context, int type) {
		super(context);
		mType = type;
	}

	public int getType(){
		return mType;
	}
}
