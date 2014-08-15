package com.example.drawable;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.example.R;

public class TestClipDrawable extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_clip);
		ImageView imageView = (ImageView) findViewById(R.id.test_clipdrawable_image01);

		final ClipDrawable drawable = (ClipDrawable) imageView.getDrawable();
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x1112) {
					drawable.setLevel(drawable.getLevel() + 100);
				}
			}

		};

		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = new Message();
				msg.what = 0x1112;
				handler.sendMessage(msg);
				if (drawable.getLevel() > 10000) {
					timer.cancel();
				}
			}
		}, 0, 100);
	}

}
