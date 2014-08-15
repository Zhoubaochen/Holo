package com.example.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.R;

public class TestGridView extends Activity {
	int[] images = new int[] { R.drawable.p4, R.drawable.p5, R.drawable.p6,
			R.drawable.p7, R.drawable.p8 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		final ImageSwitcher switcher = (ImageSwitcher) findViewById(R.id.test_gridview_switcher);
		switcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		switcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		switcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				ImageView imageView = new ImageView(TestGridView.this);
				imageView.setBackgroundColor(0xFF000000);
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(300,300));
				return imageView;
			}
		});
		// 1.创建List集合
		final List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < images.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", images[i]);
			listItems.add(listItem);
		}
		// 2.创建SimpleAdapter,指定列表项内容
		SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.cell, new String[] { "image" },
				new int[] { R.id.test_gridview_cell });
		GridView grid = (GridView) findViewById(R.id.test_gridview_grid01);
		grid.setAdapter(adapter);

		grid.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switcher.setImageResource(images[position % images.length]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switcher.setImageResource(images[position % images.length]);

			}
		});
	}

}
