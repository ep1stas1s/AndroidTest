package com.yjp.lifecycletest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "Myservice";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // onCreate()는 처음에만 실행되기 때문에 반복적인 service는 onStartCommand()에서 처리
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate() 호출됨");
    }

    // onStartCommand()에서 주로 method 를 처리 함
    // Service 는 프로세스를 강제로 종료시켜도 재실행되는 기능이 있음
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출됨");

        if(intent == null){
            // null 이라면, 서비스 재시작
            return Service.START_STICKY;
        }else{
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "전달받은 데이터 : " + command + ", " + name);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Service ----(Intent)---> Activity
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|      // Service 는 화면이 없기 때문에
                            Intent.FLAG_ACTIVITY_SINGLE_TOP|    // 화면이 없는 상황 -> 화면이 있는 activity 로 넘어갈 때 addFlags()를 사용
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);    // 주로 이 3가지가 쓰임
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service.");
        startActivity(showIntent);
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() 호출됨");

        super.onDestroy();
    }

}
