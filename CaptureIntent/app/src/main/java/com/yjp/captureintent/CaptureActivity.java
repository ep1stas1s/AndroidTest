package com.yjp.captureintent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CaptureActivity extends AppCompatActivity {

    ImageView imageView2;
    CameraSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        imageView2 = findViewById(R.id.imageView2);
        surfaceView = findViewById(R.id.surfaceView);

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });
    }

    public void capture(){
        surfaceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                // options setting
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;           // 1/8 크기

                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                imageView2.setImageBitmap(bitmap);

                camera.startPreview();
            }
        });
    }
}
