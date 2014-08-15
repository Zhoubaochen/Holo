package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 负责跟服务器交互的线程
 * 
 * @author Samuel
 * 
 */
public class ClientThread implements Runnable {
	Socket s = null;
	Handler handler = null;
	BufferedReader br = null;

	public ClientThread(Socket s, Handler handler) throws IOException {
		this.s = s;
		this.handler = handler;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String content = null;
			while ((content = br.readLine()) != null) {
				Message msg = new Message();
				Bundle data = new Bundle();
				data.putString("hello", br.readLine());
				msg.what=0x111;
				msg.setData(data);
				handler.sendMessage(msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}