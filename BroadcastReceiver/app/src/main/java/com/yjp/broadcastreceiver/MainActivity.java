package com.yjp.broadcastreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 위험권한 부여
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "SMS 수신 권한 주어져 있음.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "SMS 수신 권한 없음", Toast.LENGTH_SHORT).show();
//            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
//                Toast.makeText(this, "SMS 권한 설명 필요함", Toast.LENGTH_SHORT).show();
//            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);

//            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0) {
                    // grantResults[0]에 정보가 들어가 있음
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "SMS 수신 권한을 사용자가 승인함.", Toast.LENGTH_SHORT).show();
                    } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, "SMS 수신 권한을 거부함.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "SMS 수신 권한을 부여받지 못함.", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
