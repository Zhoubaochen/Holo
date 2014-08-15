package com.example.net;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;

import com.example.R;

public class TestKsoap2android extends Activity{
final static String SERVICE_NS="http://sam";
final static String SERVICE_URL="http://localhost:8080/AppServer";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String method="getUserList";
		//
		HttpTransportSE ht=new HttpTransportSE(SERVICE_URL);
		//
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		//
		SoapObject soapObject=new SoapObject(SERVICE_NS,method);
		soapObject.addProperty("arg0", "客户端参数");
		
		//
		envelope.bodyOut=soapObject;
		try {
			//调用webservice
			ht.call(null, envelope);
			if(envelope.getResponse()!=null){
				SoapObject result=(SoapObject)envelope.bodyIn;
				//解析响应数据
				SoapObject detail1=(SoapObject) result.getProperty(0);
				SoapObject detail2=(SoapObject) result.getProperty(1);
					detail1.getProperty(0);
					detail1.getProperty(2);
					
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
