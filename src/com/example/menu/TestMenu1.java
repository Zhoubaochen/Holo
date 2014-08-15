package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.example.gallery.TestGallery;

public class TestMenu1 extends Activity {
	final int FONT_10 = 0x111;
	final int FONT_12 = 0x112;
	final int FONT_14 = 0x113;
	final int FONT_16 = 0x114;
	final int FONT_18 = 0x115;

	final int FONT_RED = 0x116;
	final int FONT_BLUE = 0x117;
	final int FONT_GREEN = 0x118;

	final int PLAIN_ITEM = 0x119;

	final int MALE = 0x11a;
	final int FEMALE = 0x11b;
	final int MY_RED = 0x11c;
	final int MY_BLUE = 0x11d;
	final int MY_GREEN = 0x11e;

	final int C_MENU1 = 0x11e;
	final int C_MENU2 = 0x11f;
	final int C_MENU3 = 0x120;

	EditText edit;
	TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		show = (TextView) findViewById(R.id.test_menu1_edittext02);
		edit = (EditText) findViewById(R.id.test_menu1_edittext01);
		registerForContextMenu(show);// 为show注册上下文菜单
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// ------------子菜单

		SubMenu fontMenu = menu.addSubMenu("字体大小");
		fontMenu.setHeaderIcon(R.drawable.ic_launcher);
		fontMenu.setHeaderTitle("选择字体大小");
		fontMenu.add(0, FONT_10, 0, "10号字体");
		fontMenu.add(0, FONT_12, 0, "12号字体");
		fontMenu.add(0, FONT_14, 0, "14号字体");
		fontMenu.add(0, FONT_16, 0, "16号字体");
		fontMenu.add(0, FONT_18, 0, "18号字体");// -MenuItem.setOnMenuItemClick()

		// ------------普通菜单
		menu.add(0, PLAIN_ITEM, 0, "普通菜单1");

		// -----------子菜单2
		SubMenu colorMenu = menu.addSubMenu("字体大小");
		colorMenu.setHeaderIcon(R.drawable.ic_launcher);
		colorMenu.setHeaderTitle("选择字体颜色");
		colorMenu.add(0, FONT_RED, 0, "红色");
		colorMenu.add(0, FONT_GREEN, 0, "绿色");
		colorMenu.add(0, FONT_BLUE, 0, "蓝色");
		// -----------子菜单3单选菜单
		SubMenu genderMenu = menu.addSubMenu("性别");
		genderMenu.setHeaderIcon(R.drawable.ic_launcher);
		genderMenu.setHeaderTitle("选择性别");
		genderMenu.add(0, MALE, 0, "男");
		genderMenu.add(0, FEMALE, 0, "女");
		genderMenu.setGroupCheckable(0, true, true);

		// -----------子菜单4复选
		SubMenu favorMenu = menu.addSubMenu("喜欢的颜色");
		favorMenu.setHeaderIcon(R.drawable.ic_launcher);
		favorMenu.setHeaderTitle("选择颜色");
		favorMenu.add(0, MY_RED, 0, "红色").setCheckable(true);
		favorMenu.add(0, MY_GREEN, 0, "绿色").setCheckable(true);
		favorMenu.add(0, MY_BLUE, 0, "蓝色").setCheckable(true);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 响应
		switch (item.getItemId()) {
		case FONT_10:
			edit.setTextSize(10 * 2);
			break;
		case FONT_12:
			edit.setTextSize(12 * 2);
			break;
		case FONT_14:
			edit.setTextSize(14 * 2);
			break;
		case FONT_16:
			edit.setTextSize(16 * 2);
			break;
		case FONT_18:
			edit.setTextSize(18 * 2);
			break;

		case FONT_RED:
			edit.setTextColor(Color.RED);
			break;
		case FONT_GREEN:
			edit.setTextColor(Color.GREEN);
			break;
		case FONT_BLUE:
			edit.setTextColor(Color.BLUE);
			break;

		case PLAIN_ITEM:
			// 启动其他Activity
						//item.setIntent(new Intent(this, TestGallery.class));
						
			Toast toast = Toast.makeText(TestMenu1.this, "你选择了普通菜单项",
					Toast.LENGTH_SHORT);
			toast.show();
			break;
		case MALE:
			edit.setText("性别：男");
			item.setChecked(true);
			break;

		case FEMALE:
			edit.setText("性别：女");
			item.setChecked(true);
			break;

		case MY_RED:

			if (item.isChecked()) {
				item.setChecked(false);
			} else {
				item.setChecked(true);
			}

			break;

		case MY_GREEN:

			if (item.isChecked()) {
				item.setChecked(false);
			} else {
				item.setChecked(true);
			}
			break;

		case MY_BLUE:

			if (item.isChecked()) {
				item.setChecked(false);
			} else {
				item.setChecked(true);
			}
			;
			break;

		}

		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// 上下文菜单
		menu.add(0, C_MENU1, 0, "文件夹");
		menu.add(0, C_MENU2, 0, "壁纸");
		menu.add(0, C_MENU3, 0, "小组件");

		menu.setHeaderTitle("上下文");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case C_MENU1:
			edit.setText("wenjain1");
			break;
		case C_MENU2:
			edit.setText("wenjain2");
			break;
		case C_MENU3:
			edit.setText("wenjain3");
			break;
		}
		return true;
	}

}
