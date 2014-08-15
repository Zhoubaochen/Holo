package com.example.dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.R;
import com.example.gallery.TestGallery;

public class TestDialog extends Activity {

	private static final int LIST_DIALOG = 0x113;
	protected static final int PROGRESS_DIALOG = 0x112;
	protected static final int NOTIFICATION_ID = 0x114;
	private String[] names = new String[] { "TestDialog", "TestGallery",
			"TestTabHost" };
	private int[] headers = new int[] { R.drawable.p7, R.drawable.p7,
			R.drawable.p7 };

	int progressStatus = 0;
	ProgressDialog pd;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog);

		Button btn1 = (Button) findViewById(R.id.test_dialog_btn01);
		Button btn2 = (Button) findViewById(R.id.test_dialog_btn02);
		Button btn3 = (Button) findViewById(R.id.test_dialog_btn03);
		Button btn4 = (Button) findViewById(R.id.test_dialog_btn04);
		Button btn5 = (Button) findViewById(R.id.test_dialog_btn05);
		Button btn6 = (Button) findViewById(R.id.test_dialog_btn06);
		Button btn7 = (Button) findViewById(R.id.test_dialog_btn07);
		Button btn8 = (Button) findViewById(R.id.test_dialog_btn08);
		Button btn9 = (Button) findViewById(R.id.test_dialog_btn09);
		Button btn10 = (Button) findViewById(R.id.test_dialog_btn10);
		Button btn11 = (Button) findViewById(R.id.test_dialog_btn11);
		Button btn12 = (Button) findViewById(R.id.test_dialog_btn12);

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Builder builder = new AlertDialog.Builder(TestDialog.this);

				builder.setIcon(android.R.drawable.title_bar).setTitle("自定义标题")
						.setMessage("显示信息在这里")
						.setPositiveButton("确定", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								TextView textView = (TextView) findViewById(R.id.test_dialog_tv_show);
								textView.setText("你按了确定");
							}

						}).setNegativeButton("取消", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								TextView textView = (TextView) findViewById(R.id.test_dialog_tv_show);
								textView.setText("你按了取消");
							}
						}).create().show();
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Builder builder = new AlertDialog.Builder(TestDialog.this);

				builder.setIcon(android.R.drawable.title_bar)
						.setTitle("简单列表对话框")
						.setItems(new String[] { "红色", "绿色", "蓝色" },
								new OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										TextView show = (TextView) findViewById(R.id.test_dialog_tv_show);
										switch (which) {
										case 0:
											show.setBackgroundColor(Color.RED);
											break;
										case 1:
											show.setBackgroundColor(Color.GREEN);
											break;
										case 2:
											show.setBackgroundColor(Color.BLUE);
											break;

										}
									}
								}).create().show();
			}
		});
		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Builder builder = new AlertDialog.Builder(TestDialog.this);

				builder.setIcon(android.R.drawable.title_bar)
						.setTitle("单选对话框")
						.setPositiveButton("确定", null)

						.setSingleChoiceItems(
								new String[] { "红色", "绿色", "蓝色" }, 1,
								new OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										TextView show = (TextView) findViewById(R.id.test_dialog_tv_show);
										switch (which) {
										case 0:
											show.setBackgroundColor(Color.RED);
											break;
										case 1:
											show.setBackgroundColor(Color.GREEN);
											break;
										case 2:
											show.setBackgroundColor(Color.BLUE);
											break;

										}
									}
								}).create().show();
			}
		});
		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Builder builder = new AlertDialog.Builder(TestDialog.this);

				final boolean[] checkStatus = new boolean[] { true, false, true };
				builder.setIcon(android.R.drawable.title_bar)
						.setTitle("多选对话框")
						.setPositiveButton("确定", null)
						.setMultiChoiceItems(new String[] { "红色", "绿色", "蓝色" },
								checkStatus, new OnMultiChoiceClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which, boolean isChecked) {
										// TODO Auto-generated method stub
										TextView textView = (TextView) findViewById(R.id.test_dialog_tv_show);
										String result = "你选了:";
										for (int i = 0; i < checkStatus.length; i++) {
											if (checkStatus[i]) {
												result += i + "、";
											}
										}
										textView.setText(result);

									}

								}).create().show();
			}
		});
		btn5.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(LIST_DIALOG);
			}
		});
		btn6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Builder builder = new AlertDialog.Builder(TestDialog.this);
				TableLayout loginForm = (TableLayout) getLayoutInflater()
						.inflate(R.layout.login, null);
				builder.setIcon(android.R.drawable.title_bar).setTitle("注册对话框")
						.setView(loginForm)
						.setPositiveButton("登录", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								TextView textView = (TextView) findViewById(R.id.test_dialog_tv_show);
								textView.setText("你按了确定");
							}

						}).setNegativeButton("取消", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								TextView textView = (TextView) findViewById(R.id.test_dialog_tv_show);
								textView.setText("你按了取消");
							}
						}).create().show();
			}
		});
		View root = getLayoutInflater().inflate(R.layout.popup, null);
		final PopupWindow popup = new PopupWindow(root, 250, 300);
		btn7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popup.showAsDropDown(v);
				popup.showAtLocation(findViewById(R.id.test_dialog_btn07),
						Gravity.CENTER, 20, 20);

			}
		});
		root.findViewById(R.id.test_dialog_popup_btn01).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						popup.dismiss();
					}
				});

		btn8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar c = Calendar.getInstance();
				new DatePickerDialog(TestDialog.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// TODO Auto-generated method stub
								TextView textView = (TextView) findViewById(R.id.test_dialog_tv_show);
								textView.setText("日期：" + year + "/"
										+ monthOfYear + "/" + dayOfMonth);
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		btn9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar c = Calendar.getInstance();
				new TimePickerDialog(
						TestDialog.this,
						new TimePickerDialog.OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								TextView textView = (TextView) findViewById(R.id.test_dialog_tv_show);
								textView.setText("时间：" + hourOfDay + ":"
										+ minute);
							}

						}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
						true).show();
			}
		});
		btn10.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(PROGRESS_DIALOG);
			}
		});
		btn11.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(TestDialog.this, "简单提示用toast",
						Toast.LENGTH_SHORT);
				// 带图片的toast.setView();
				toast.show();
			}
		});
		btn12.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 通过notification通知执行其他Activity
				Intent intent = new Intent(TestDialog.this, TestGallery.class);
				PendingIntent pi = PendingIntent.getActivity(TestDialog.this,
						0, intent, 0);
				Notification notification = new Notification();
				// notification.icon=
				notification.tickerText = "启动通知";
				notification.when = System.currentTimeMillis();
				notification.defaults = Notification.DEFAULT_SOUND;
				notification.defaults = Notification.DEFAULT_ALL;
				notification.setLatestEventInfo(TestDialog.this, "系统通知",
						"点击运行Gallery", pi);

				NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				nm.notify(NOTIFICATION_ID, notification);
				// nm.cancel(NOTIFICATION_ID);
			}
		});
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x111) {// 本程序发送的消息
					pd.setProgress(progressStatus);
				}
			}

		};

	}

	@Override
	public Dialog onCreateDialog(int id, Bundle bundle) {
		switch (id) {
		case LIST_DIALOG:
			final Builder builder = new AlertDialog.Builder(TestDialog.this);

			builder.setIcon(android.R.drawable.title_bar).setTitle("自定义列表对话框")
					.setPositiveButton("确定", null);

			// 1.创建List集合
			final List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < names.length; i++) {
				Map<String, Object> listItem = new HashMap<String, Object>();
				listItem.put("header", headers[i]);
				listItem.put("name", names[i]);// header+name

				listItems.add(listItem);
			}
			// 2.创建SimpleAdapter,指定列表项内容
			SimpleAdapter adapter = new SimpleAdapter(this, listItems,
					R.layout.main, new String[] { "header", "name" },
					new int[] { R.id.list_item_header, R.id.list_item_name });

			builder.setAdapter(adapter, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					TextView show = (TextView) findViewById(R.id.test_dialog_tv_show);
					show.setText(names[which]);
				}
			});
			return builder.create();
		case PROGRESS_DIALOG:
			pd = new ProgressDialog(this);
			pd.setMax(100);
			pd.setTitle("测试进度条");
			pd.setMessage("数组填充中");
			pd.setCancelable(false);
			// pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//圆形风格
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 直线风格
			pd.setIndeterminate(false);
			return pd;
		}
		return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		super.onPrepareDialog(id, dialog);
		switch (id) {
		case PROGRESS_DIALOG:
			pd.incrementProgressBy(-pd.getProgress());
			new Thread() {
				public void run() {
					while (progressStatus < 100) {
						progressStatus = doWork();
						// 发送消息到Handler
						Message msg = new Message();
						msg.what = 0x111;
						handler.sendMessage(msg);

					}
					if (progressStatus >= 100) {
						pd.dismiss();
					}
				}
			}.start();
			break;
		}
	}

	int[] data = new int[100];
	int hasData = 0;

	protected int doWork() {
		// 模拟耗时操作,填充数组data
		data[hasData++] = hasData;
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hasData;
	}

}
