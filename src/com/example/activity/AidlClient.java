package com.example.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.R;
import com.example.service.FirstService;
import com.example.service.ICat;
import com.example.service.ICat.Stub;

/**
 * 远程调用 ，使用AIDL的
 * 
 * @author Samuel
 * 
 */
public class AidlClient extends Activity {
	private ICat catService;
	private Button get;
	String color;
	double weight;
	private ServiceConnection conn=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// 获取远程service的onBind()方法返回的对象的代理
			System.out.println("----service connected");
			catService=ICat.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("----service disconnected");
			catService=null;
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_service1);

		get = (Button) findViewById(R.id.test_service_btn_bind1);
		final Intent intent = new Intent();
		intent.setAction("com.example.service.AIDL_SERVICE");
		
		//绑定远程服务
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
		
		get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					color=catService.getColor();
					Toast.makeText(getApplicationContext(), "color"+color, 1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unbindService(conn);
	}
	

}
