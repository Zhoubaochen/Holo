package com.example.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class MyRenderer implements Renderer {
	float[] triangleData = new float[] { 0.1f, 0.6f, 0.0f, -0.3f, 0.0f, 0.0f,
			0.3f, 0.1f, 0.0f };
	int[] triangleColor = new int[] { 65535, 0, 0, 0, 0, 65535, 0, 0, 0, 0,
			65535, 0

	};
	FloatBuffer triDataBuffer;
	IntBuffer triColorBuffer;

	float rotate;

	public MyRenderer() {
		triDataBuffer = ByteBuffer.allocateDirect(triangleData.length)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();

		triColorBuffer = ByteBuffer.allocateDirect(triangleColor.length)
				.order(ByteOrder.nativeOrder()).asIntBuffer();

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// 关闭抖动
		gl.glDisable(GL10.GL_DITHER);
		// 系统对透视修正
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0, 0, 0, 0);
		// 阴影平滑模式
		gl.glShadeModel(GL10.GL_SMOOTH);
		// 启用深度测试
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// 深度测试类型
		gl.glDepthFunc(GL10.GL_LEQUAL);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		// 3d视窗大小、位置
		gl.glViewport(0, 0, width, height);
		// 矩阵模式-投影矩阵
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// 初始化单位矩阵
		gl.glLoadIdentity();
		// 透视视窗宽高比
		float ratio = (float) width / height;
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		//
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		//
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		//
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		//

		// ===绘制三角形
		gl.glLoadIdentity();
		// 中心偏移
		gl.glTranslatef(-0.32f, 0.35f, -1f);
		// 旋转rotate
		gl.glRotatef(rotate, 0f, 0f, 0.1f);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, triDataBuffer);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, triColorBuffer);
		//
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

		gl.glFinish();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		rotate += 1;// 不停旋转
	}

}
