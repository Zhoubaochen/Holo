package com.example.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.R;

public class SService_TelephonyManager extends Activity {
	ListView showView;
	String[] statusNames;
	ArrayList<String> statusValues = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		// 状态名称
		statusNames = getResources().getStringArray(R.array.status_names);
		// sim卡状态
		String[] simState = getResources().getStringArray(R.array.sim_states);
		// 电话网络类型
		String[] phoneType = getResources().getStringArray(R.array.phoneTypes);
		// 设备编号
		statusValues.add(tm.getDeviceId());
		// 平台版本
		statusValues.add(tm.getDeviceSoftwareVersion() != null ? tm
				.getDeviceSoftwareVersion() : "未知");
		// 网络运营商代号
		statusValues.add(tm.getNetworkOperator());
		// 运营商名称
		statusValues.add(tm.getNetworkOperatorName());
		// 网络类型
		statusValues.add(phoneType[tm.getPhoneType()]);
		// 所在位置
		statusValues.add(tm.getCellLocation() != null ? tm.getCellLocation()
				.toString() : "未知位置");
		// sim卡国别
		statusValues.add(tm.getSimCountryIso());
		// sim卡序列号
		statusValues.add(tm.getSimSerialNumber());
		// sim卡状态
		statusValues.add(simState[tm.getSimState()]);
		showView = (ListView) findViewById(R.id.mylist);
		ArrayList<Map<String, String>> status = new ArrayList<Map<String, String>>();
		for (int i = 0; i < statusValues.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", statusNames[i]);
			map.put("value", statusValues.get(i));
			Log.d("dd",  statusValues.get(i));
			status.add(map);

		}
		SimpleAdapter adapter = new SimpleAdapter(
				SService_TelephonyManager.this, status, R.layout.list_item,
				new String[] { "name", "value" }, new int[] {
						R.id.list_item_name, R.id.list_item_value });

		showView.setAdapter(adapter);
	}

}
