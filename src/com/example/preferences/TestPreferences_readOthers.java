package com.example.preferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.R;

/**
 * 获取其他程序的preferences
 * 
 * @author Samuel
 * 
 */
@SuppressLint("ShowToast")
public class TestPreferences_readOthers extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_view);
		Context useOthers = null;
		try {
			useOthers = createPackageContext("com.example.preferences",
					CONTEXT_IGNORE_SECURITY);

		} catch (Exception e) {
			// TODO: handle exception
		}
		SharedPreferences pref = useOthers.getSharedPreferences("use_count", MODE_PRIVATE);

		int count = pref.getInt("count", 0);
		Toast.makeText(this, "你访问的程序使用了" + count + "次", 1000);

	}

}
