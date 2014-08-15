package com.example.holo;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.activity.TestOpenGL_triangle;
import com.example.activity.TestSysAction;
import com.example.activity.WebService_MyWeather;
import com.example.anim.TestAnimationDrawable;
import com.example.client.SService_TelephonyManager;
import com.example.client.SocketClient1;
import com.example.dialog.TestDialog;
import com.example.drawable.TestBitmapDrawable;
import com.example.drawable.TestClipDrawable;
import com.example.gallery.TestGallery;
import com.example.gesture.TestGesture;
import com.example.gesture.TestGesture_viewflip;
import com.example.listview.TestExpandableListView;
import com.example.listview.TestGridView;
import com.example.listview.DBshow;
import com.example.listview.TestTabHost;
import com.example.menu.TestMenu;
import com.example.view.TestCanvas;

public class MainActivity extends LauncherActivity {
	// launchActivity直接启动列表项的activity

	private String[] names = new String[] { "TestDialog", "TestGallery",
			"TestTabHost", "数据操作演示", "TestExpandableListView", "TestGridView",
			"TestMenu", "TestSysAction", "TestClipDrawable",
			"TestAnimationDrawable", "TestBitmapDrawable", "TestCanvas",
			"TestGesture", "TestGesture_viewflip", "SService_TelephonyManager",
			"TestOpenGL_triangle", "SocketClient1","WebService_MyWeather" };
	private Class<?>[] classes = new Class[] { TestDialog.class,
			TestGallery.class, TestTabHost.class, DBshow.class,
			TestExpandableListView.class, TestGridView.class, TestMenu.class,
			TestSysAction.class, TestClipDrawable.class,
			TestAnimationDrawable.class, TestBitmapDrawable.class,
			TestCanvas.class, TestGesture.class, TestGesture_viewflip.class,
			SService_TelephonyManager.class, TestOpenGL_triangle.class,
			SocketClient1.class ,WebService_MyWeather.class};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names);
		setListAdapter(adapter);

	}

	@Override
	protected Intent intentForPosition(int position) {
		// TODO Auto-generated method stub

		return new Intent(MainActivity.this, classes[position]);
	}

}
