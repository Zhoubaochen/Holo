package com.example.preferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.R;

@SuppressLint("ShowToast")
public class TestPreferences extends Activity {
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_view);
		
		pref=getSharedPreferences("use_count",MODE_PRIVATE);
		
		int count =pref.getInt("count", 0);
		Toast.makeText(this, "程序使用了"+count+"次", 1000);
		
		Editor editor=pref.edit();
		editor.putInt("count", ++count);
		editor.commit();
	}

}
