package com.example.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.R;
import com.example.service.FirstService;
import com.example.service.FirstService.MyBinder;

public class TestBindService extends Activity {

	Button bind, unbind;
	FirstService.MyBinder binder;
	private ServiceConnection conn=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("----service connected");
			binder=(FirstService.MyBinder) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("----service disconnected");
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_service1);
		bind = (Button) findViewById(R.id.test_service_btn_bind1);
		unbind = (Button) findViewById(R.id.test_service_btn_unbind1);
		final Intent intent = new Intent();
		intent.setAction("com.example.service.FIRST_SERVICE");
		bind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(intent,conn,Service.BIND_AUTO_CREATE);
			}
		});
		unbind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(conn);
			}
		});
	}

}
