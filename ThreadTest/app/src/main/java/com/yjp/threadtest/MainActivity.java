package com.yjp.threadtest;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


// Java Thread ver.
// Handler ver.
// AsyncTask ver.
public class MainActivity extends AppCompatActivity {
    TextView textView;

//    int value = 0;

//    ValueHandler handler = new ValueHandler();

    Handler handler2 = new Handler();

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BackgroundThread thread = new BackgroundThread();
//                thread.start();

                // Handler ver.=============================================
                // 따로 class 구현하지 않고,
                new Thread(new Runnable() {
                    boolean running = false;
                    int value = 0;

                    @Override
                    public void run() {
                        running = true;
                        while(running){
                            value += 1;

                            // 메인 thread 에서 실행되기 때문에 바로 전달할 수 있음
                            // 굳이 class 를 따로 생성하지 않아도 됨
                            handler2.post(new Runnable(){
                                @Override
                                public void run() {
                                    textView.setText("value : " + value);
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


//                            // Message 에 넣기 위한 Bundle
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("value", value);
//
//                            // Message 생성 후 handler 에 넣고 보냄
//                            Message message = handler.obtainMessage();
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                        }
                    }
                }).start();
                // Handler end ======================================================


                // AsyncTask ver. ===========================================
                ProgressTask task = new ProgressTask();
                task.execute("Start");
                // AsyncTask end ============================================
            }
        });

        // Java Thread ver. =======================================================
        // click 했을 때에만 thread 작동 현황을 보여줌 (표준 Java의 thread를 그냥 썻을 때)
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                textView.setText("Value : " + value);
            }
        });
        // Java Thread end ========================================================

        // AsyncTask ver. ===========================================
        progressBar = findViewById(R.id.progressBar);
        // AsyncTask end ============================================
    }

    // background
    /*class BackgroundThread extends Thread{
        boolean running = false;
        int value = 0;
        public void run(){
            running = true;
            while(running){
                value += 1;


                // Message 에 넣기 위한 Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);

                // Message 생성 후 handler 에 넣고 보냄
                Message message = handler.obtainMessage();
                message.setData(bundle);
                handler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Handler (UI에 접근하면서 사용하는 Thread)
    class ValueHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("Value : " + value);
        }
    }*/

    // AsyncTask ver.=============================================
    class ProgressTask extends AsyncTask<String, Integer, Integer>{
        // 아래와 같은 순서로 generic 이 적용

        int value = 0;

        // call back method (자동으로 호출)
        @Override
        protected Integer doInBackground(String... strings) { // 가변 길이 parameter
            while(true){
                if(value > 100){
                    break;
                }

                value += 1;

                publishProgress(value); // onProgressUpdate 호출

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        // progressBar 가 업데이트될 때
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            Toast.makeText(MainActivity.this, "Complete...", Toast.LENGTH_SHORT).show();
        }
    }
}
