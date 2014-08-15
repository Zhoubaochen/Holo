package com.example.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.R;
/**
 * 补间动画，旋转，缩放，透明度
 * @author Samuel
 *
 */
public class TestCanvas_Tween extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_bitmap);
		final ImageView img1 = (ImageView) findViewById(R.id.test_bitmapdrawable_image01);
		final Animation anim = AnimationUtils.loadAnimation(this,
				R.anim.my_tween_anim1);
		anim.setFillAfter(true);
		final Animation anim_reverse = AnimationUtils.loadAnimation(this,
				R.anim.my_tween_anim1_reverse);
		anim.setFillAfter(true);

		final Button btn = (Button) findViewById(R.id.test_bitmapdrawable_btn_next);
		final Button btn_reverse = (Button) findViewById(R.id.test_bitmapdrawable_btn_prior);
		btn_reverse.setClickable(false);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				img1.startAnimation(anim);
				btn_reverse.setClickable(true);
				btn.setClickable(false);

			}
		});
		btn_reverse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				img1.startAnimation(anim_reverse);
				btn_reverse.setClickable(false);
				btn.setClickable(true);
			}
		});
	}

}