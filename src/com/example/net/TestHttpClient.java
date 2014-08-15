package com.example.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.R;

public class TestHttpClient extends Activity{
Button get;
Button login;
TextView show;
HttpClient hclient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		get=(Button) findViewById(R.id.main_show_btn01);
		login=(Button) findViewById(R.id.main_show_btn02);
		show=(TextView) findViewById(R.id.main_show_tv01);
		hclient=new DefaultHttpClient();
		
		get.setText("get");
		login.setText("login");
		get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HttpGet hget=new HttpGet("http://localhost:8080/AppServer/index.jsp");
				try {
					HttpResponse response=hclient.execute(hget);
					HttpEntity entity=response.getEntity();
					if(entity!=null){
						BufferedReader br=new BufferedReader(new InputStreamReader(entity.getContent()));
						String line=null;
						while((line=br.readLine())!=null){
							show.append(line+"\n");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
