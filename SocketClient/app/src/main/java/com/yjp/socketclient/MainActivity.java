package com.yjp.socketclient;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    class ClientThread extends Thread{
        public void run(){
            // IP 지정 (localhost = 자기 자신)
            String host = "localhost";
            int port = 5001;    // server 와 동일해야 함

            try {
                Socket socket = new Socket(host, port);

                // 서버로 보냄
                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                outStream.writeObject("Hello~!!");
                outStream.flush();
                Log.d("ClientThread", "서버로 보냄.");

                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                final Object input = inStream.readObject();
                Log.d("ClientThread", "받은 데이터 : " + input);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Object 앞에 final 을 붙여서
                        textView.setText("받은 데이터 : " + input);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
