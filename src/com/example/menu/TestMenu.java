package com.example.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.R;

public class TestMenu extends Activity {

	private String[] names = new String[] { "TestMenu1","TestMenu2"};
	private Class[] classes = new Class[] { TestMenu1.class,TestMenu2.class};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 1.创建List集合
		final List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("name", names[i]);// header+name
			listItem.put("clazz", classes[i]);
			listItems.add(listItem);
		}
		// 2.创建SimpleAdapter,指定列表项内容
		SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.main, new String[] { "name" },
				new int[] { R.id.list_item_name });
		ListView list = (ListView) findViewById(R.id.mylist);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Class<?> clazz = (Class<?>) listItems.get(position)
						.get("clazz");

				Intent intent = new Intent(TestMenu.this, clazz);
				startActivity(intent);
			}
		});
	}

}
