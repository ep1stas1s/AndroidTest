package com.yjp.volleylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yjp.volleylibrary.image.ImageLoadTask;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        imageView = findViewById(R.id.imageView);

        // json button
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });


        // image button
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImageRequest();
            }
        });

        // RequestQueue 초기화
        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void sendRequest(){
        String url = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {// 응답을 String 으로 받음
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);

                        processResponse(response);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ){
            // parameter 를 추가하고 싶으면 이와 같이 {}를 추가해서 아래 method 를 override 함
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        request.setShouldCache(false);      // false -> 이전 결과가 있어도 새로운 결과를 가져와라
        AppHelper.requestQueue.add(request);
        println("요청 보냄.");
    }

    public void processResponse(String response){
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response, MovieList.class);

        if(movieList != null){
            int countMovie = movieList.boxOfficeResult.dailyBoxOfficeList.size();
            println("응답받은 영화 개수 : " + countMovie + "개");
            println("박스오피스 타입" + movieList.boxOfficeResult.boxofficeType);
        }
    }

    // 이미지 받아오는 method
    public void sendImageRequest(){
        String url = "https://dimg.donga.com/wps/NEWS/IMAGE/2018/11/16/92903788.2.jpg";
        ImageLoadTask task = new ImageLoadTask(url, imageView);
        task.execute();
    }

    public void println(String data){
        textView.append(data + "\n");
    }
}
