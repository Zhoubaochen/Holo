package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.example.service.IPet.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


/**
 * 使用Parcelable ,AIDL的
 * 
 * @author Samuel
 * 
 */
public class AidlService_Parcelable extends Service {
	private PetBinder petBinder;
	private static Map<Person,List<Pet>> pets=new HashMap<Person,List<Pet>>();
	static{
		ArrayList<Pet> list1=new ArrayList<Pet>();
		list1.add(new Pet("fuldog",23.3));
		list1.add(new Pet("fsgid",22.1));
		pets.put(new Person(1,"zhou","123"), list1);
	}
	
	Timer timer=new Timer();
	String[] colors = new String[] { "红", "白", "黑" };
	double[] weights = new double[] { 2.0, 3.3, 1.2 };
	private String color;
	private double weight;

	public class PetBinder extends Stub {

		@Override
		public List<Pet> getPets(Person owner) throws RemoteException {
			// TODO Auto-generated method stub
			return pets.get(owner);
		}

	
	}

	@Override
	public IBinder onBind(Intent intent) {
		// 返回一个IBinder对象给调用服务的人
		System.out.println("service bind..");
		return petBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		petBinder=new PetBinder();
		
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
