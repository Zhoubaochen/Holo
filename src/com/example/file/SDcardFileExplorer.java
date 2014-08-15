package com.example.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;

/**
 * 浏览sd卡文件
 * 
 * @author Samuel
 * 
 */
@SuppressLint("ShowToast")
public class SDcardFileExplorer extends Activity {
	ListView listView;
	TextView textView;
	File currentParent;
	File[] currentFiles;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_sdfile_explorer);

		listView = (ListView) findViewById(R.id.test_sdfile_explorer_list);
		textView = (TextView) findViewById(R.id.test_sdfile_explorer_path);
		Button btn = (Button) findViewById(R.id.test_sdfile_explorer_btn_parent);

		File root = null;
		try {
			root = new File(Environment.getExternalStorageDirectory().getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (root.exists()) {
			File[] tmp = root.listFiles();
			if (tmp == null || tmp.length == 0) {
				// 没有文件
				Toast.makeText(SDcardFileExplorer.this, "当前文件夹为空", 1000);
			} else {
				currentParent = root;
				currentFiles = tmp;
				//
				inflateListView(currentFiles);
			}
			
		}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (currentFiles[position].isFile())
					return;
				File[] tmp = currentFiles[position].listFiles();
				if (tmp == null || tmp.length == 0) {
					// 没有文件
					Toast.makeText(SDcardFileExplorer.this, "当前文件夹为空", 1000);
				} else {
					currentParent = currentFiles[position];
					currentFiles = tmp;
					//
					inflateListView(currentFiles);
				}
			}
		});

		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if(!currentParent.getCanonicalPath().equals(Environment.getExternalStorageDirectory().getPath())){
						currentParent=currentParent.getParentFile();
						currentFiles=currentParent.listFiles();
						//
						inflateListView(currentFiles);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		
		
	}

	private void inflateListView(File[] files){
		List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
		for(int i=0;i<files.length;i++){
			Map<String,Object> listItem=new HashMap<String ,Object>();
			if(files[i].isDirectory()){
				listItem.put("icon",R.drawable.dir1);
			}else{
				listItem.put("icon", R.drawable.file1);
			}
			listItem.put("fileName", files[i]);
			listItems.add(listItem);
		}
		SimpleAdapter simple=new SimpleAdapter(this,listItems,R.layout.list_item,new String[]{"icon","fileName"},new int[]{R.id.list_item_header,R.id.list_item_name});
		listView.setAdapter(simple);
		try {
			textView.setText("当前路径："+currentParent.getCanonicalPath());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
