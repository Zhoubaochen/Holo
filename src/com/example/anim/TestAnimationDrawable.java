package com.example.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.R;

public class TestAnimationDrawable extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_anim1);
		final ImageView imageView = (ImageView) findViewById(R.id.test_animationdrawable_image01);
		final Animation anim = AnimationUtils.loadAnimation(this,
				R.anim.my_frame_anim1);
		anim.setFillAfter(true);// 结束后保留结束状态
		Button btn = (Button) findViewById(R.id.test_animationdrawable_btn01);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageView.startAnimation(anim);
			}
		});

	}

}
