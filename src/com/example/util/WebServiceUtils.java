package com.example.util;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
/**
 * 通过ksoap1.1调用http://WebXml.com.cn提供的天气服务
 * 查询身份列表
 * 查询城市列表
 * 查询对应天气
 * 
 * @author Samuel
 *
 */
public class WebServiceUtils {
	final static String SERVICE_NS = "http://WebXml.com.cn";
	final static String SERVICE_URL = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";
/**
 * 获得中国省份、直辖市、地区和与之对应的ID
 * @return
 */
	
	public static List<String> getProvinceList() {
		String methodName = "getRegionProvince";
		//
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		// 使用soap1.1协议创建
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		//
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		//
		envelope.bodyOut = soapObject;
		envelope.dotNet = true;
		try {
			// 调用webservice
			ht.call(SERVICE_NS + methodName, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.bodyIn;
				// 解析响应数据
				SoapObject detail = (SoapObject) result.getProperty(methodName
						+ "Result");

				return parseProvinceOrCity(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
/**
 * 获得支持的城市/地区名称和与之对应的ID
 * @param province
 * @return
 */
	public static List<String> getCityListByProvince(String province) {
		String methodName = "getSupportCityString";
		//
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		// 使用soap1.1协议创建
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		//
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		soapObject.addProperty("theRegionCode", province);

		//
		envelope.bodyOut = soapObject;
		envelope.dotNet = true;
		try {
			// 调用webservice
			ht.call(SERVICE_NS + methodName, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.bodyIn;
				// 解析响应数据
				SoapObject detail = (SoapObject) result.getProperty(methodName
						+ "Result");

				return parseProvinceOrCity(detail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获得天气预报数据
	 * 输入参数：城市/地区ID或名称，返回数据：一维字符串数组。
	 * @param cityName
	 * @return
	 */
	public static SoapObject getWeatherByCity(String cityName) {
		String methodName = "getWeather";
		//
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		// 使用soap1.1协议创建
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		//
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		soapObject.addProperty("theCityCode", cityName);

		//
		envelope.bodyOut = soapObject;
		envelope.dotNet = true;
		try {
			// 调用webservice
			ht.call(SERVICE_NS + methodName, envelope);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.bodyIn;
				// 解析响应数据
				SoapObject detail = (SoapObject) result.getProperty(methodName
						+ "Result");

				return detail;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	private static List<String> parseProvinceOrCity(SoapObject soap) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < soap.getPropertyCount(); i++) {
			result.add(soap.getProperty(i).toString().split(",")[0]);
		}
		return result;
	}
}
