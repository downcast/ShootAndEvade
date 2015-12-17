package com.sbms.shootandevade;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

/**
 * Class is used to make the character move up and down fluidly
 * @author Sherdon Brown
 */
public class CharacterMoveTask extends AsyncTask<View, Void, Void> {

	View mPlayer;
	View mButton;

	@Override
	protected Void doInBackground(View... arg) {
		// Retrieve values
		mPlayer = arg[0];
		mButton = arg [1];

		// Validate values
		if (mPlayer == null || mButton == null){
			Log.e("MoveTask", "Null argument");
			return null;
		}

		// Run until user cancels task by not pressing button
		// Sleep to slow down the movespeed of the player
		while (!isCancelled()){
			try {
				publishProgress();
				Thread.sleep(10, 0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/** Code is run on the UI thread after a call to "publishProgress" */
	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);

		// Keep player from moving higher than the top of the screen
		if (mPlayer.getY() < 0){
			mPlayer.setY(0);
			return;
		}

		// Keep player from moving below the bottom of the screen
		if (mPlayer.getY() + mPlayer.getHeight() > MainActivity.sDisplayHeight){
			mPlayer.setY(MainActivity.sDisplayHeight - mPlayer.getHeight());
			return;
		}

		// Determine which button is pressed and adjust y-coordinate accordingly
		switch (mButton.getId()){
			case R.id.btn_controller_up:
				mPlayer.setY(mPlayer.getY() - 30);
				break;
			case R.id.btn_controller_down:
				mPlayer.setY(mPlayer.getY() + 30);
				break;
		}
	}
}

