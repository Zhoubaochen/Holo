package com.example.view;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.R;
/**
 * 实现逐帧+补间动画。行走的女孩
 * @author Samuel
 *
 */
public class TestCanvas_Frame_tween extends Activity {
	private float curX = 0;
	private float curY = 30;
	float nextX = 0;
	float nextY = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_frame_anim1);
		final ImageView img = (ImageView) findViewById(R.id.test_frameanimation_walking_girl);
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x111) {
					if (nextX > 320) {
						curX = nextX = 0;
					} else {
						nextX += 8;// 每次向右8
					}
					TranslateAnimation anim = new TranslateAnimation(curX,
							nextX, curY, nextY);
					curX = nextX;
					curY = nextY;
					anim.setDuration(200);
					img.startAnimation(anim);
				}
			}

		};

		final AnimationDrawable girl =(AnimationDrawable) img.getBackground();//xml文件应该声明Android：background=“”
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				girl.start();
				new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						handler.sendEmptyMessage(0x111);
					}
				}, 0, 200);
			}
		});
		
	}

}