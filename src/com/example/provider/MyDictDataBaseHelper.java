package com.example.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDictDataBaseHelper extends SQLiteOpenHelper{
final String CREATE_TABLE_DICT="create table dict(_id integer primary key autoincrement ,word , detail)";

	public MyDictDataBaseHelper(Context context, String name,
		 int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 首次进入，建表
		db.execSQL(CREATE_TABLE_DICT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
