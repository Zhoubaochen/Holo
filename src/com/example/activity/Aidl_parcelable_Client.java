package com.example.activity;

import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.R;
import com.example.service.IPet;
import com.example.service.Person;
import com.example.service.Pet;

/**
 * 远程调用 ，使用AIDL的
 * 
 * @author Samuel
 * 
 */
public class Aidl_parcelable_Client extends Activity {
	private IPet petService;
	private Button get;

	String color;
	double weight;
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// 获取远程service的onBind()方法返回的对象的代理
			System.out.println("----service connected");
			petService = IPet.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("----service disconnected");
			petService = null;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		get = (Button) findViewById(R.id.test_service_btn_bind1);
		final Intent intent = new Intent();
		intent.setAction("com.example.service.AIDL_SERVICE_PARCELABLE");

		// 绑定远程服务
		bindService(intent, conn, Service.BIND_AUTO_CREATE);

		get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {// 调用服务的方法
				try {
					String pname = "";
					List<Pet> pets = petService.getPets(new Person(1, pname,
							"123"));
					//
					ArrayAdapter<Pet> adapter = new ArrayAdapter<Pet>(
							Aidl_parcelable_Client.this,
							android.R.layout.simple_list_item_1, pets);
					//show.setAdapter(adapter);

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
