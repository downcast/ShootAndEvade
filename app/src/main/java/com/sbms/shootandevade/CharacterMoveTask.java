package com.sbms.shootandevade;

import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Class is used to make the character move up and down fluidly
 * @author Sherdon Brown
 */
public class CharacterMoveTask extends AsyncTask<Void, Void, Void> {

	private ImageView charaImage;
	private int valueX;
	private int valueY;
	private int screenHeight;
	/** A higher value means the character moves slower */
	private final int MOVESPEED= 25;

	public CharacterMoveTask(ImageView characterImage, int moveX, int moveY, int screenHeight){
		this.charaImage= characterImage;
		this.valueX= moveX;
		this.valueY= moveY;
		this.screenHeight= screenHeight;
	}

	@Override
	protected Void doInBackground(Void... ag0) {
		// TODO Auto-generated method stub
		while (!this.isCancelled()){
			/*if (this.charaImage.getX() < 0){
				this.charaImage.setyLocation(0);
			}else if (this.charaImage.getImageYEdge() > screenHeight + 100){
				this.charaImage.alignToLowerWall();
			} else{
				this.charaImage.updateYLocation(valueY);
			}
			///////////////////////////
			if (this.charaImage.getxLocation() < 0){
				this.charaImage.setxLocation(0);
			}else if (this.charaImage.getImageXEdge() > screenHeight + 100){
				this.charaImage.alignToLowerWall();
			} else{
				this.charaImage.updateXLocation(valueX);
			}

			/////////////////////
			*/
			try {
				Thread.sleep(MOVESPEED, 0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}

