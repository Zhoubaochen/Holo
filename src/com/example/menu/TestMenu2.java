package com.example.menu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
/**
 * 通过xml文件布局菜单，对比TestMenu1就知道好处了
 * 
 * @author Samuel
 *
 */
public class TestMenu2 extends Activity {
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
		MenuInflater inflater=new MenuInflater(this);
		inflater.inflate(R.menu.my_menu1, menu);
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
						
			Toast toast = Toast.makeText(TestMenu2.this, "你选择了普通菜单项",
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
		MenuInflater inflater=new MenuInflater(this);
		inflater.inflate(R.menu.my_context_menu1, menu);
		menu.setHeaderTitle("请选择背景色");
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
