package com.example.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.service.ICat.Stub;

/**
 * 使用AIDL的
 * 
 * @author Samuel
 * 
 */
public class AidlService extends Service {
	private CatBinder catBinder;
	Timer timer=new Timer();
	String[] colors = new String[] { "红", "白", "黑" };
	double[] weights = new double[] { 2.0, 3.3, 1.2 };
	private String color;
	private double weight;

	public class CatBinder extends Stub {

		@Override
		public String getColor() throws RemoteException {
			// TODO Auto-generated method stub
			return color;
		}

		@Override
		public double getWeight() throws RemoteException {
			// TODO Auto-generated method stub
			return weight;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// 返回一个IBinder对象给调用服务的人
		System.out.println("service bind..");
		return catBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		catBinder=new CatBinder();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int rand=(int)(Math.random()*3);
				color=colors[rand];
				weight=weights[rand];
				
			}
		}, 0,800);
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("service destroy");
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("service unbind..");
		return true;
	}

}
