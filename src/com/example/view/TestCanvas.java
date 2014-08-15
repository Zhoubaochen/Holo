package com.example.view;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.R;

/**
 * 利用SurfaceView实现局部绘制图片
 * 
 * @author Samuel
 * 
 */
public class TestCanvas extends Activity {
	private SurfaceHolder holder;
	private Paint paint;
	final int WIDTH = 320;
	final int HEIGHT = 320;
	final int X_OFFSET = 5;

	private int x = X_OFFSET;
	int yaxis = HEIGHT / 2;

	Timer timer = new Timer();
	TimerTask task = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_surfaceview2);
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3);
		SurfaceView surface = (SurfaceView) findViewById(R.id.test_surfaceview2_image01);
		holder = surface.getHolder();
		Button btn_sin = (Button) findViewById(R.id.test_surfaceview_btn_sin);
		Button btn_cos = (Button) findViewById(R.id.test_surfaceview_btn_cos);

		OnClickListener listener = (new OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				drawBackground(holder);
				x = X_OFFSET;
				if (task != null) {
					task.cancel();
				}
				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int y = v.getId() == R.id.test_surfaceview_btn_sin ? yaxis
								- (int) (100 * Math.sin((x - 5) * 2 * Math.PI
										/ 150))
								: yaxis
										- (int) (100 * Math.cos((x - 5) * 2
												* Math.PI / 150));

						Canvas canvas = holder.lockCanvas(new Rect(x - 2,
								y - 2, x + 2, y + 2));
						canvas.drawPoint(x, y, paint);
						x++;
						if (x > WIDTH) {
							task.cancel();
							task = null;
						}
						holder.unlockCanvasAndPost(canvas);

					}

				};
				timer.schedule(task, 0,50);

			}
		});

		btn_sin.setOnClickListener(listener);
		btn_cos.setOnClickListener(listener);

		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub

			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				drawBackground(holder);

			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub

			}
		});

	}

	void drawBackground(SurfaceHolder holder) {
		Canvas canvas = holder.lockCanvas(new Rect(0,0,320,400));
		canvas.drawColor(Color.WHITE);
		Paint p=new Paint();
		p.setColor(Color.BLACK);
		p.setStrokeWidth(2);
		
		canvas.drawLine(X_OFFSET, yaxis, WIDTH, yaxis, p);
		canvas.drawLine(X_OFFSET, 40, X_OFFSET, HEIGHT, p);
		
		holder.unlockCanvasAndPost(canvas);
		// 避免下次被遮挡
		holder.lockCanvas(new Rect(0, 0, 0, 0));
		holder.unlockCanvasAndPost(canvas);
	}
}