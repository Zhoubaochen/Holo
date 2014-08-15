package com.example.listview;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.file.SDcardFileExplorer;
import com.example.preferences.TestPreferences;
/**
 * 数据操作演示
 * @author Samuel
 *
 */
public class DBshow extends LauncherActivity {
	

	private String[] names = new String[] { "TestPreferences" ,"SDcardFileExplorer"};
	private Class<?>[] classes = new Class[] { TestPreferences.class,SDcardFileExplorer.class};

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

		return new Intent(DBshow.this, classes[position]);
	}

}
