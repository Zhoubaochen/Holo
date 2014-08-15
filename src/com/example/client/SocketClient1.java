package com.example.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;

public class SocketClient1 extends Activity {
	TextView show;
	EditText edit;
	Button send;
	Handler handler;
	OutputStream os = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_chartroom);
		show = (TextView) findViewById(R.id.my_chartroom_show01);
		edit = (EditText) findViewById(R.id.my_chartroom_edit01);
		send = (Button) findViewById(R.id.my_chartroom_btn_send01);
		// 定义Handler对象
		handler = new Handler() {
			@Override
			// 当有消息发送出来的时候就执行Handler的这个方法
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// 处理UI
				if (msg.what == 0x111) {
					String hello = msg.getData().getString("hello");
					
					show.append(hello);
				}

			}
		};
		new Thread() {
			@Override
			public void run() {
				// 这里放你的访问网络的代码
				try {
					Socket socket = new Socket("10.24.1.205", 30000);
					new Thread(new ClientThread(socket, handler)).start();
					os = socket.getOutputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.start();

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					os.write((edit.getText().toString())
							.getBytes("utf-8"));
					edit.setText("");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
}
