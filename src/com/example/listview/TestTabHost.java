package com.example.listview;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.example.R;

@SuppressWarnings("deprecation")
public class TestTabHost extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.tabhost,
				tabHost.getTabContentView(), true);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("已接电话")
				.setContent(R.id.test_tab_tab01));
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("未接来电")
				.setContent(R.id.test_tab_tab02));
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("通讯录")
				.setContent(R.id.test_tab_tab03));

	}
}
