package com.example.drawable;

import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.R;

public class TestBitmapDrawable extends Activity {
	String[] images = null;
	AssetManager assets = null;
	int currentImg = 0;
	ImageView imageView=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_bitmap);
		imageView = (ImageView) findViewById(R.id.test_bitmapdrawable_image01);
		Button prior = (Button) findViewById(R.id.test_bitmapdrawable_btn_prior);
		Button next = (Button) findViewById(R.id.test_bitmapdrawable_btn_next);

		try {
			assets = getAssets();
			images = assets.list("");// 获取/assets/所有文件
		} catch (Exception e) {
			// TODO: handle exception
		}

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (currentImg >= images.length) {
					currentImg = 0;
				}
				while (!images[currentImg].endsWith(".png")
						&& !images[currentImg].endsWith(".jpg")
						&& !images[currentImg].endsWith(".gif"))

				{
					currentImg++;
					if (currentImg >= images.length) {
						currentImg = 0;
					}
				}

				InputStream assetFile = null;
				try {
					assetFile = assets.open(images[currentImg]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView
						.getDrawable();
				if (bitmapDrawable != null
						&& !bitmapDrawable.getBitmap().isRecycled()) {
					bitmapDrawable.getBitmap().recycle();// 回收
				}
				imageView.setImageBitmap(BitmapFactory.decodeStream(assetFile));
				currentImg++;
			}
		});
		prior.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (currentImg <0 ) {
					currentImg = images.length-1;
				}
				while (!images[currentImg].endsWith(".png")
						&& !images[currentImg].endsWith(".jpg")
						&& !images[currentImg].endsWith(".gif"))

				{
					currentImg--;
					if (currentImg <0 ) {
						currentImg = images.length-1;
					}
				}

				InputStream assetFile = null;
				try {
					assetFile = assets.open(images[currentImg]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView
						.getDrawable();
				if (bitmapDrawable != null
						&& !bitmapDrawable.getBitmap().isRecycled()) {
					bitmapDrawable.getBitmap().recycle();// 回收
				}
				imageView.setImageBitmap(BitmapFactory.decodeStream(assetFile));
				currentImg--;
			}
		});
	}

}
