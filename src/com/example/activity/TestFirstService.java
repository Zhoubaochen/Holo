package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.R;

public class TestFirstService extends Activity {

	protected static final int PICK_CONTACT = 0x111;
	Button start, stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_service1);
		start = (Button) findViewById(R.id.test_service_btn_start1);
		stop = (Button) findViewById(R.id.test_service_btn_stop1);
		final Intent intent = new Intent();
		intent.setAction("com.example.service.FIRST_SERVICE");
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(intent);
			}
		});
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(intent);
			}
		});
	}

}
