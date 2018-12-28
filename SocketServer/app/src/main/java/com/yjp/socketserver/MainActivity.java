package com.yjp.socketserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
//    static final String APP = "ServerThread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ServerThread thread = new ServerThread();
//                thread.start();
                Intent intent = new Intent(getApplicationContext(), ChatService.class);
                startService(intent);
            }
        });
    }

    /*class ServerThread extends Thread{
        public void run(){
            int port = 5001;
            try {
                // 서버 실행
                ServerSocket server = new ServerSocket(port);
                Log.d(APP, "서버가 실행됨.");

                while(true){
                    // 대기상태 -> 접속하면 socket 으로 받음
                    Socket socket = server.accept();

                    // 들어오는 데이터를 처리 (파이프)
                    ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                    Object input = inStream.readObject();
                    Log.d(APP, "input : " + input);

                    // 데이터를 클라이언트로 보냄
                    ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                    outStream.writeObject("송신완료" + " from server.");
                    outStream.flush();
                    Log.d(APP, "output 보냄!");

                    // 연결이 필요없다 싶으면 끊음 (연결이 필요하면 끝으면 안 됨)
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }*/
}
