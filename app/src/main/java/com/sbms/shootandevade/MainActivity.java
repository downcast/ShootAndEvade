package com.sbms.shootandevade;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

	//region Fields
	public static int sDisplayHeight;
	public static int sDisplayWidth;

	/** This is the space between the player and the projectile when it fires **/
	public final int PROJECTILE_MARGIN = 25;
	public final int PROJECTILE_SPEED = 1500;

	private RelativeLayout mLayoutGame;
	private GameObject mPlayer;

	private boolean mButtonDown = false;
	private boolean mFirstTime = true;
	private ObjectAnimator mXTranslator;
	private LinearInterpolator mLinInterpolator = new LinearInterpolator();
	//endregion

	//region Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Link xml version of views to the code version
		mLayoutGame = (RelativeLayout) findViewById(R.id.layout_game);
		Button btnControllerUp = (Button) findViewById(R.id.btn_controller_up);
		Button btnControllerDown = (Button) findViewById(R.id.btn_controller_down);
		Button btnControllerFire = (Button) findViewById(R.id.btn_controller_fire);
		mPlayer = (GameObject) findViewById(R.id.view_player);

		mLayoutGame.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				// Grab the device dimensions for the application to use elsewhere
				MainActivity.sDisplayHeight = mLayoutGame.getHeight();
				MainActivity.sDisplayWidth = mLayoutGame.getWidth();
			}
		});

		mPlayer.setType(GameObject.TYPE_PLAYER);

		// These buttons will respond a touches
		btnControllerUp.setOnTouchListener(this);
		btnControllerDown.setOnTouchListener(this);

		// These buttons will respond to clicks
		btnControllerFire.setOnClickListener(this);
	}

	//region Event Handlers
	@Override
	public boolean onTouch(View view, MotionEvent event) {

		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				float duration;

				switch (view.getId()) {
					case R.id.btn_controller_up:
						if (!mButtonDown || mFirstTime) {
							if (mXTranslator != null) {
								mXTranslator.cancel();
							}
							duration = Math.abs(((((mPlayer.getY() + mPlayer.getHeight()) / (MainActivity.sDisplayHeight) - 1) * 3 + 3) * 1000));

							mXTranslator = ObjectAnimator.ofFloat(mPlayer, View.TRANSLATION_Y, (-MainActivity.sDisplayHeight / 2) + (mPlayer.getHeight() / 2));
							mXTranslator.setInterpolator(mLinInterpolator);
							mXTranslator.setDuration((long) duration);
							mButtonDown = true;
							mFirstTime = false;
							mXTranslator.start();
						}
						break;
					case R.id.btn_controller_down:
						if (mButtonDown || mFirstTime) {
							if (mXTranslator != null) {
								mXTranslator.cancel();
							}
							duration = Math.abs(((mPlayer.getY() / MainActivity.sDisplayHeight) * 3 - 3) * 1000);

							mXTranslator = ObjectAnimator.ofFloat(mPlayer, View.TRANSLATION_Y, (MainActivity.sDisplayHeight / 2) - (mPlayer.getHeight() / 2));
							mXTranslator.setInterpolator(mLinInterpolator);
							mXTranslator.setDuration((long) duration);
							mButtonDown = false;
							mFirstTime = false;
							mXTranslator.start();
						}
				}
				break;
			case MotionEvent.ACTION_UP:
				// Commenting out his line will allow the plane to keep moving even when a button is not being pressed
				//mXTranslator.cancel();
				mFirstTime = true;
				break;
		}
		return true;
	}

	@Override
	public void onClick(View view) {
		// It is assumed the only button that can be "clicked" is the 'mBtnControllerFire'

		// Create the view that represents the projectile
		GameObject projectile = new GameObject(this, GameObject.TYPE_PLAYER_PROJECTILE);
		// NOTE: the following is discouraged because it slows down the main thread.
		// Create bitmap on another thread using the resource and pass it back
		projectile.setImageResource(R.drawable.player_plane);
		projectile.setId();

		// Set the starting coordinates of the projectile to the last position of the player
		projectile.setX(mPlayer.getWidth() + PROJECTILE_MARGIN);
		projectile.setY(mPlayer.getY());

		mLayoutGame.addView(projectile);

		// This animates the projectile to go straight to the right end of the screen, in 1.5 seconds immediately
		ObjectAnimator xTranslator = ObjectAnimator.ofFloat(projectile, View.TRANSLATION_X, MainActivity.sDisplayWidth / 2);
		xTranslator.setInterpolator(new LinearInterpolator());
		xTranslator.setDuration(PROJECTILE_SPEED);
		xTranslator.addListener(new ProjectileAnimator(projectile, mLayoutGame));
		xTranslator.start();
	}
	//endregion

	//endregion
}
