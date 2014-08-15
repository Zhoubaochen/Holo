package com.example.provider;

import android.app.Activity;
import android.os.Bundle;
/**
 * 展示字典单词详细
 * @author Samuel
 *
 */
public class ResultActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle data=getIntent().getExtras();
	}

}
