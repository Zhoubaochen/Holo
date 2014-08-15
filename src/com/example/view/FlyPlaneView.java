package com.example.view;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.R;
/**
 * 截取，背景移动模拟飞机飞行
 * @author Samuel
 *
 */
@SuppressLint("DrawAllocation")
public class FlyPlaneView extends View {
	final int BACK_HEIGHT=299;//背景图实际高度
	private Bitmap bg;
	private Bitmap plane;
	
	//背景图开始位置
	final int WIDTH=395;
	final int HEIGHT=100;
	private int startY=BACK_HEIGHT-HEIGHT;
	
	public FlyPlaneView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		bg=BitmapFactory.decodeResource(context.getResources(),R.drawable.p8);
		plane=BitmapFactory.decodeResource(context.getResources(),R.drawable.plane);
		
		final Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what==0x111){
					if(startY<=1){//注意边界199%3=1
						startY=BACK_HEIGHT-HEIGHT;
					}else{
						startY-=3;
					}
					
				}
				
				invalidate();
			}
			
		};
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x111);
			}
		}, 0,100);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Bitmap bm2=Bitmap.createBitmap(bg,0,startY,WIDTH,HEIGHT);
		canvas.drawBitmap(bm2, 0,0, null);
		canvas.drawBitmap(plane, 90,280, null);
	//	Log.d("dd", startY+"");
	}

}
