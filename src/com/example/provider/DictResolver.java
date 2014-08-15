package com.example.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.R;

/**
 * 访问DictProvider共享的数据
 * 
 * @author Samuel
 * 
 */
public class DictResolver extends Activity {
	ContentResolver resolver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btn_insert = new Button(null);
		Button btn_search = new Button(null);
		btn_insert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String word = "";
				String detail = "";
				ContentValues values = new ContentValues();
				values.put(Words.Word.WORD, word);
				values.put(Words.Word.DETAIL, detail);

				resolver.insert(Words.Word.WORD_CONTENT_URI, values);
				Toast.makeText(DictResolver.this, "插入成功", 3000).show();

			}
		});
		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String key = "";
				Cursor cursor = resolver.query(Words.Word.DICT_CONTENT_URI,
						null, "word like ? or detail like ? ", new String[] {
								"%" + key + "%", "%" + key + "%" }, null);
				Bundle data=new Bundle();
				data.putSerializable("data", convertCursorToList(cursor));
				Intent intent=new Intent(DictResolver.this,ResultActivity.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});

	}

	private ArrayList<Map<String, String>> convertCursorToList(Cursor cursor) {
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(Words.Word.WORD, cursor.getString(1));
			map.put(Words.Word.DETAIL, cursor.getString(2));
			result.add(map);
		}
		return result;
	}

}
