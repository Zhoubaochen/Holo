package com.example.gesture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.R;

/**
 * 通过手势实现翻页效果
 * 
 * @author Samuel
 * 
 */
public class TestGesture_viewflip extends Activity implements OnGestureListener {
	ViewFlipper flipper;
	GestureDetector detector;
	Animation[] animations=new Animation[4];
	final int FLIP_DISTANCE=50;
	ImageView imageView;
	Bitmap bitmap;
	int width, height;
	float currentScale = 1;
	Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_viewflip);
		detector = new GestureDetector(this);
		flipper = (ViewFlipper) findViewById(R.id.test_gesture_viewflipper01);
		
		flipper.addView(addImageView(R.drawable.p4));
		flipper.addView(addImageView(R.drawable.p5));
		flipper.addView(addImageView(R.drawable.p6));
		flipper.addView(addImageView(R.drawable.p7));
		
		animations[0]=AnimationUtils.loadAnimation(this, R.anim.my_frame_anim1);
		animations[1]=AnimationUtils.loadAnimation(this, R.anim.my_frame_anim1);
		animations[2]=AnimationUtils.loadAnimation(this, R.anim.my_frame_anim1);
		animations[3]=AnimationUtils.loadAnimation(this, R.anim.my_frame_anim1);
		
		
	}
private View addImageView(int resId){
	ImageView imageView=new ImageView(this);
	imageView.setImageResource(resId);
	imageView.setScaleType(ImageView.ScaleType.CENTER);
	return imageView;
}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		/**
		 * 从右向左滑动
		 */
		if(e1.getX()-e2.getX()>FLIP_DISTANCE){
			flipper.setInAnimation(animations[0]);
			flipper.setOutAnimation(animations[1]);
			flipper.showPrevious();
			return true;
			
		}else if(e2.getX()-e1.getX()>FLIP_DISTANCE){
			flipper.setInAnimation(animations[2]);
			flipper.setOutAnimation(animations[3]);
			flipper.showNext();
			return true;
			
		}
		return true;
	}

}
