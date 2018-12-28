package com.yjp.captureintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        File sdcard = Environment.getExternalStorageDirectory();
        file = new File(sdcard, "capture.jpg");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });

        // CaptureActivity 로 이동
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
                startActivity(intent);
            }
        });
    }

    public void capture(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));      // 어떤 파일로 저장하게 할 거냐
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            // (용량이 큰) 찍은 사진을 그냥 넣으면 메모리가 초과될 수 있기 때문에 줄이는 작업
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;           // 1/8 크기로 줄이는 option

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            imageView.setImageBitmap(bitmap);
        }
    }
}
