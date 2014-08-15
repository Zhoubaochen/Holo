package com.example.view;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.R;

/**
 * 利用SurfaceView绘制sin、cos波形图
 * 
 * @author Samuel
 * 
 */
public class TestCanvas_SurfaceView extends Activity {
	private SurfaceHolder holder;
	private Paint paint;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_surfaceview);
		paint = new Paint();
		SurfaceView surface = (SurfaceView) findViewById(R.id.test_surfaceview_image01);
		holder = surface.getHolder();
		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub

			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				Canvas canvas = holder.lockCanvas();
				Bitmap bg = BitmapFactory.decodeResource(
						TestCanvas_SurfaceView.this.getResources(),
						R.drawable.p5);
				canvas.drawBitmap(bg, 0, 0, null);// 绘制背景

				holder.unlockCanvasAndPost(canvas);

				// 避免下次被遮挡
				holder.lockCanvas(new Rect(0, 0, 0, 0));
				holder.unlockCanvasAndPost(canvas);

			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub

			}
		});
		surface.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int x = (int) event.getX();
					int y = (int) event.getY();
					Canvas canvas = holder.lockCanvas(new Rect(x - 20, y - 20,
							x + 20, y + 20));
					// 保存当前
					canvas.save();
					//
					canvas.rotate(30, x, y);
					paint.setColor(Color.RED);
					canvas.drawRect(x - 20, y - 20, x, y, paint);
					// 恢复之前
					canvas.restore();

					paint.setColor(Color.GREEN);
					canvas.drawRect(x, y, x + 20, y + 20, paint);

					holder.unlockCanvasAndPost(canvas);

				}
				return false;
			}
		});

	}

	
}