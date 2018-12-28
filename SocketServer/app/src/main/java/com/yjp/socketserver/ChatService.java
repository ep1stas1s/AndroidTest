package com.yjp.socketserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatService extends Service {
    static final String APP = "ServerThread";

    public ChatService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Thread start
        ServerThread thread = new ServerThread();
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class ServerThread extends Thread{
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

    }
}
