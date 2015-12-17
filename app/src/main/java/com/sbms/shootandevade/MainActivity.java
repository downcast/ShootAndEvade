package com.sbms.shootandevade;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

	RelativeLayout mLayoutGame;
	Button mBtnControllerUp;
	Button mBtnControllerDown;
	Button mBtnControllerFire;
	ImageView mPlayer;
	ImageView mProjectile;

	boolean mIsBtnControllerPressed;
	CharacterMoveTask mMoveTask;

	static int sDisplayHeight;
	static int sDisplayWidth;
	static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

	/** This is the space between the player and the projectile when it fires **/
	final int PROJECTILE_MARGIN = 25;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Grab the device height for the application to use elsewhere
		sDisplayHeight = getResources().getDisplayMetrics().heightPixels;
		// Grab the device width for the application to use elsewhere
		sDisplayWidth = getResources().getDisplayMetrics().widthPixels;

		// Link xml version of views to the code version
		mLayoutGame = (RelativeLayout) findViewById(R.id.layout_game);
		mBtnControllerUp = (Button) findViewById(R.id.btn_controller_up);
		mBtnControllerDown = (Button) findViewById(R.id.btn_controller_down);
		mBtnControllerFire = (Button) findViewById(R.id.btn_controller_fire);

		// Create the view that represents the player
		mPlayer = new ImageView(this);
		mPlayer.setId(generateViewId());
		// NOTE: the following is discouraged because it slows down the main thread.
		// Create bitmap on another thread using the resource and pass it back
		mPlayer.setImageResource(R.drawable.player_plane);

		// Initialize the player movement task that will allow a fluid movement
		mMoveTask = new CharacterMoveTask();

		mIsBtnControllerPressed = false;

		// These buttons will respond a touches
		mBtnControllerUp.setOnTouchListener(this);
		mBtnControllerDown.setOnTouchListener(this);

		// These buttons will respond to clicks
		mBtnControllerFire.setOnClickListener(this);

		// Define parameters for the player starting location;
		RelativeLayout.LayoutParams playerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		playerParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		playerParams.addRule(RelativeLayout.CENTER_VERTICAL);
		playerParams.setMargins(10, 0, 0, 0);

		mLayoutGame.addView(mPlayer, playerParams);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {

		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				mMoveTask = new CharacterMoveTask();
				mMoveTask.execute(mPlayer, view);
				break;
			case MotionEvent.ACTION_UP:
				// Stop the task so that the a new button can be used to determine if the player's y coordinate will rise or fall
				if (!mMoveTask.cancel(true)){
					Log.e("MoveTask", "Task cannot be canceled");
				}
				break;
		}
		return true;
	}

	@Override
	public void onClick(View view) {
		// It is assumed the only button that can be "clicked" is the 'mBtnControllerFire'

		// Create the view that represents the projectile
		mProjectile = new ImageView(this);
		// NOTE: the following is discouraged because it slows down the main thread.
		// Create bitmap on another thread using the resource and pass it back
		mProjectile.setImageResource(R.drawable.player_plane);

		// Set the starting coordinates of the projectile to the last position of the player
		mProjectile.setX(mPlayer.getWidth() + PROJECTILE_MARGIN);
		mProjectile.setY(mPlayer.getY());

		mLayoutGame.addView(mProjectile);
	}

	/**
	 * Generate a value suitable for use for View.setId().
	 * This value will not collide with ID values generated at build time by aapt for R.id.
	 * <br>
	 *     Taken from the android source code for backward compatibility for API < 17.
	 *
	 * @return a generated ID value
	 */
	public static int generateViewId() {
		for (;;) {
			final int result = sNextGeneratedId.get();
			// aapt-generated IDs have the high byte nonzero; clamp to the range under that.
			int newValue = result + 1;
			if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
			if (sNextGeneratedId.compareAndSet(result, newValue)) {
				return result;
			}
		}
	}

}
