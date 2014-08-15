package com.example.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 字典常量类，访问该ContentProvider的常用入口
 * 
 * @author Samuel
 * 
 */
public class Words {
	public static final String AUTHORITY = "com.example.provider.dictprovider";

	public static final class Word implements BaseColumns {
public final static String _ID="_id";
public final static String WORD="word";
public final static String DETAIL="detail";
public final static Uri DICT_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/words");
public final static Uri WORD_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/word");
	}
}
