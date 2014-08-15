package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * 实现手绘
 * 双缓冲
 * @author Samuel
 *
 */
public class DrawView extends View {
float preX;
float preY;
public Path path;
public Paint paint=null;
final int VIEW_WIDTH=330;
final int VIEW_HEIGHT=480;

Bitmap cacheBitmap=null;//作为缓存区
Canvas cacheCanvas=null;
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		cacheBitmap=Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT,Config.ARGB_8888);
		cacheCanvas=new Canvas();
		path=new Path();
		
		cacheCanvas.setBitmap(cacheBitmap);
		paint=new Paint(Paint.DITHER_FLAG);
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);
		paint.setAntiAlias(true);
		paint.setDither(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x=event.getX();
		float y=event.getY();
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			preX=x;
			preY=y;
			break;
		case MotionEvent.ACTION_MOVE:
			path.quadTo(preX, preY, x, y);
			preX=x;
			preY=y;
			break;
		case MotionEvent.ACTION_UP:
			cacheCanvas.drawPath(path, paint);
			break;
			
		}
		invalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint bmpPaint=new Paint();
		//绘制缓冲区的图片
		canvas.drawBitmap(cacheBitmap, 0,0,paint);
		//接着沿path绘制
		canvas.drawPath(path, paint);

	}

}
