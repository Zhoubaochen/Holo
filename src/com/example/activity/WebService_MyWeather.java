package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.R;
import com.example.util.WebServiceUtils;

/**
 * 远程调用 webservice的天气预报demo
 * 
 * @author Samuel
 * 
 */
public class WebService_MyWeather extends Activity {
	private Spinner provinceSpinner;
	private Spinner citySpinner;
	private ImageView todayIcon1;
	private ImageView todayIcon2;
	private TextView todayText;
	List<String> cities = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_weather_view);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());

		provinceSpinner = (Spinner) findViewById(R.id.test_webservice_weather_spinner_province);
		citySpinner = (Spinner) findViewById(R.id.test_webservice_weather_spinner_city);
		todayText = (TextView) findViewById(R.id.test_webservice_weather_today_detail);

		// 远程调用webservice服务
		List<String> provinces = WebServiceUtils.getProvinceList();

		ListAdapter adapter = new ListAdapter(this, provinces);
		provinceSpinner.setAdapter(adapter);
		provinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				cities = WebServiceUtils.getCityListByProvince(provinceSpinner
						.getSelectedItem().toString());
				ListAdapter adapter2 = new ListAdapter(
						WebService_MyWeather.this, cities);
				citySpinner.setAdapter(adapter2);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showWeather(cities.get(position));

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	private class ListAdapter extends BaseAdapter implements SpinnerAdapter {
		WebService_MyWeather weather;
		List<String> list;

		public ListAdapter(WebService_MyWeather weather, List<String> list) {
			this.weather = weather;
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView text = new TextView(weather);
			text.setText(list.get(position));
			return text;
		}

	}

	private void showWeather(String city) {
		String wtoday = null;
		String wcurrent = null;
		int iconToday[] = new int[2];
		SoapObject detail = WebServiceUtils.getWeatherByCity(city);
		wcurrent = detail.getProperty(4).toString();
		String date = detail.getProperty(7).toString();
		String[] dates = date.split("");

		wtoday = "今天:" + dates[0];
		wtoday = wtoday + "\n天气:" + dates[1];
		wtoday = wtoday + "\n气温:" + detail.getProperty(8).toString();
		wtoday = wtoday + "\n风力:" + detail.getProperty(9).toString();
		wtoday = wtoday + "\n图1:" + detail.getProperty(10).toString();
		wtoday = wtoday + "\n图2:" + detail.getProperty(11).toString();

		todayText.setText(wtoday);

	}

	// /////////////////////
}
