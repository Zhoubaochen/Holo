package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

public class MyView1 extends View {

	public MyView1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		Paint paint = new Paint();
		paint.setAntiAlias(true);// 去锯齿
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);

		canvas.drawCircle(40, 40, 30, paint);
		canvas.drawRect(10, 80, 70, 140, paint);
		canvas.drawRect(10, 150, 70, 190, paint);

		RectF rel = new RectF(10, 200, 70, 230);
		canvas.drawRoundRect(rel, 15, 15, paint);

		RectF rel2 = new RectF(10, 240, 70, 270);
		canvas.drawOval(rel2, paint);

		// 三角形
		Path path1 = new Path();
		path1.moveTo(10, 340);
		path1.lineTo(70, 340);
		path1.lineTo(40, 390);
		path1.close();
		canvas.drawPath(path1, paint);

		// 五角星
		Path path2 = new Path();
		path2.moveTo(26, 340);
		path2.lineTo(54, 340);
		path2.lineTo(70, 390);
		path2.lineTo(40, 430);
		path2.lineTo(10, 390);
		path2.close();
		canvas.drawPath(path2, paint);

		// 设置填充风格
		canvas.drawText("设置填充", 30, 100, paint);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.RED);
		// 三角形
		Path path3 = new Path();
		path3.moveTo(90, 340);
		path3.lineTo(150, 340);
		path3.lineTo(120, 390);
		path3.close();
		canvas.drawPath(path3, paint);
		// 五角星
		Path path4 = new Path();
		path4.moveTo(106, 340);
		path4.lineTo(134, 340);
		path4.lineTo(150, 390);
		path4.lineTo(120, 430);
		path4.lineTo(90, 390);
		path4.close();
		canvas.drawPath(path4, paint);

		// 设置渐变器
		canvas.drawText("设置渐变器", 30, 100, paint);
		Shader shader1 = new LinearGradient(0, 0, 40, 60, new int[] {
				Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW }, null,
				Shader.TileMode.REPEAT);
		paint.setShader(shader1);
		paint.setShadowLayer(45, 10, 10, Color.GRAY);

		// 三角形
		Path path5 = new Path();
		path5.moveTo(170, 340);
		path5.lineTo(230, 340);
		path5.lineTo(200, 390);
		path5.close();
		canvas.drawPath(path5, paint);
		// 五角星
		Path path6 = new Path();
		path6.moveTo(186, 340);
		path6.lineTo(214, 340);
		path6.lineTo(230, 390);
		path6.lineTo(200, 430);
		path6.lineTo(170, 390);
		path6.close();
		canvas.drawPath(path6, paint);

	}

}
