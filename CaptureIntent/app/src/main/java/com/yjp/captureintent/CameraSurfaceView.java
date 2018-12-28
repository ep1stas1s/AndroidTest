package com.yjp.captureintent;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    SurfaceHolder holder;

    // 예전방식까지 지원하기 때문에
    // android.hardware.Camera 를 사용
    Camera camera = null;

    // 일반 activity처럼 레이아웃을 추가할 수도 있음
    public CameraSurfaceView(Context context) {
        super(context);

        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        holder = getHolder();
        holder.addCallback(this);
    }

    // 만들어지는 시점
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();

        try {
            // Camera 객체에 이 SurfaceView 를 미리보기로 쓰는 것으로 set
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 변경되는 시점
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
    }

    // 제거되는 시점
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 메모리를 많이 잡아먹기 때문에 멈춤
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public boolean capture(Camera.PictureCallback callback){
        boolean result = false;
        if(camera != null){
            camera.takePicture(null, null, callback);
            result = true;
        }
        return result;
    }
}
