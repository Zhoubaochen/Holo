package com.example.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.example.R;
/**
 * 读写sd卡的文件
 * @author Samuel
 *
 */
@SuppressLint("ShowToast")
public class TestSDcard extends Activity {
	SharedPreferences pref;
final String FILE_NAME="/sam.info";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_view);
		
		pref=getSharedPreferences("use_count",MODE_PRIVATE);
		
		int count =pref.getInt("count", 0);
		Toast.makeText(this, "程序使用了"+count+"次", 1000);
		
		Editor editor=pref.edit();
		editor.putInt("count", ++count);
		editor.commit();
	}

	private String read(){
		try {
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File sdCardDir=Environment.getExternalStorageDirectory();
				FileInputStream fis=new FileInputStream(sdCardDir.getCanonicalPath()+FILE_NAME);
				BufferedReader br=new BufferedReader(new InputStreamReader(fis));
			
				StringBuilder sb=new StringBuilder("");
				String line=null;
				while((line=br.readLine())!=null){
					sb.append(line);
				}
				return sb.toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	private void write(String content){
		try {
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File sdCardDir=Environment.getExternalStorageDirectory();
				File targetFile=new File(sdCardDir.getCanonicalPath()+FILE_NAME);
				RandomAccessFile raf=new RandomAccessFile(targetFile, "rw");
			
				raf.seek(targetFile.length());
				raf.write(content.getBytes());
				raf.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
