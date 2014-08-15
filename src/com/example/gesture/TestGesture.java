package com.example.gesture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.R;

/**
 * 通过手势实现图片缩放
 * 
 * @author Samuel
 * 
 */
public class TestGesture extends Activity implements OnGestureListener {
	GestureDetector detector;
	ImageView imageView;
	Bitmap bitmap;
	int width, height;
	float currentScale = 1;
	Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_bitmap);
		detector = new GestureDetector(this);
		imageView = (ImageView) findViewById(R.id.test_bitmapdrawable_image01);
		matrix=new Matrix();
		bitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.p5);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		imageView.setImageBitmap(BitmapFactory.decodeResource(
				this.getResources(), R.drawable.p5));
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
		// 根据手势速度计算缩放比
		velocityX = velocityX > 4000 ? 4000 : velocityX;
		velocityX = velocityX < -4000 ? -4000 : velocityX;

		currentScale += currentScale * velocityX / 4000.0f;
		currentScale = currentScale > 0.01 ? currentScale : 0.01f;

		matrix.reset();
		matrix.setScale(currentScale, currentScale, 160, 200);

		BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();
		if (!tmp.getBitmap().isRecycled()) {
			tmp.getBitmap().recycle();
		}
		Bitmap btm2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
				true);
		imageView.setImageBitmap(btm2);

		return true;
	}

}
