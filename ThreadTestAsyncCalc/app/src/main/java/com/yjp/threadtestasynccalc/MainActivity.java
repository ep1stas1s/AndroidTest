package com.yjp.threadtestasynccalc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tv_display);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncCalculation();
            }
        });
    }

    private void startAsyncCalculation() {
        // execute(1, 100_000_000)
        AsyncCalculateTask task = new AsyncCalculateTask();
        task.execute(1, Integer.MAX_VALUE);
    }

    class AsyncCalculateTask extends AsyncTask<Integer, Integer, Integer>{

        // Thread 시작 전 (e.g. 로딩을 시작합니다...)
        @Override
        protected void onPreExecute() {
            Log.d("AsyncCalculateTask", "AsyncCalculateTask Start!!");
            super.onPreExecute();
        }

        // Thread에게 할당할 과저
        /** Async Thread Method*/
        @Override
        protected Integer doInBackground(Integer... integers) {
            int number = integers[0];
            int count = integers[1];
            int result = 0;

            int percentUnit = count / 100;

            for(int i = 0; i < count; i++){
                result += number;

                if (result % percentUnit == 0){
                    publishProgress(result/percentUnit);
                }
            }
            return result;
        }


        // 진행 중 설정 (Progress)
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            tvDisplay.setText(values[0] + "%");
            Log.d("AsyncCalculateTask", values[0] + "%");
        }


        // Thread가 종료된 후, 현재 상황을 반영하기 위한 Method
        /** UI Thread */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tvDisplay.setText("Result : " + integer);
        }
    }
}
