package com.example.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 提供简单的字典使用
 * 
 * @author Samuel
 * 
 */
public class DictProvider extends ContentProvider {
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int WORDS = 1;// 查询所有
	private static final int WORD = 2;// 查询单一
	private MyDictDataBaseHelper dbHelper;
	static {
		// 注册Uri
		matcher.addURI(Words.AUTHORITY, "words", WORDS);
		matcher.addURI(Words.AUTHORITY, "word/#", WORD);

	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new MyDictDataBaseHelper(this.getContext(), "my_dict1.db", 1);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		switch (matcher.match(uri)) {
		case WORDS:
			return db.query("dict", projection, selection, selectionArgs, null,
					null, sortOrder);
		case WORD:
			long id = ContentUris.parseId(uri);
			String where = Words.Word._ID + "=" + id;
			if (selection != null && !selection.equals("")) {
				selection = selection + " and " + where;
			}
			return db.query("dict", projection, selection, selectionArgs, null,
					null, sortOrder);
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);
		}

	}

	@Override
	public String getType(Uri uri) {
		switch (matcher.match(uri)) {
		case WORDS:
			return "vnd.android.cursor.dir/com.example.provider.dict";
		case WORD:
			return "vnd.android.cursor.item/com.example.provider.dict";
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insert("dict", Words.Word._ID, values);
		if (rowId > 0) {
			Uri wordUri = ContentUris.withAppendedId(uri, rowId);
			getContext().getContentResolver().notifyChange(wordUri, null);
			return wordUri;
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int num = 0;
		switch (matcher.match(uri)) {
		case WORDS:
			num = db.delete("dict", selection, selectionArgs);
			break;
		case WORD:
			long id = ContentUris.parseId(uri);
			String where = Words.Word._ID + "=" + id;
			if (selection != null && !selection.equals("")) {
				selection = selection + " and " + where;
			}
			num = db.delete("dict", selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int num = 0;
		switch (matcher.match(uri)) {
		case WORDS:
			num = db.update("dict", values, selection, selectionArgs);
			break;
		case WORD:
			long id = ContentUris.parseId(uri);
			String where = Words.Word._ID + "=" + id;
			if (selection != null && !selection.equals("")) {
				selection = selection + " and " + where;
			}
			num = db.update("dict", values, selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("未知Uri：" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}

}
