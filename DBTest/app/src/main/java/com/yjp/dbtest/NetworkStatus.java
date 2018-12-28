package com.yjp.dbtest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {

    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 3;

    public static int getConnectivityStatus(Context context){ // SystemService를 사용하기 위해 context를 받음
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null){
            int type = networkInfo.getType();       // WIFI인지, LTE, 3G인지 확인
            if(type == ConnectivityManager.TYPE_MOBILE){
                return TYPE_MOBILE;
            }else if (type == ConnectivityManager.TYPE_WIFI){
                return TYPE_WIFI;
            }
        }
        return TYPE_NOT_CONNECTED;
    }
}
