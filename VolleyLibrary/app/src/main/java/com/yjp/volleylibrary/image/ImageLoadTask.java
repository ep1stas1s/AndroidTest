package com.yjp.volleylibrary.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String urlStr;
    private ImageView imageView;

    private static HashMap<String, Bitmap> bitmapHash = new HashMap<>();

    public ImageLoadTask(String urlStr, ImageView imageView){
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    // 1
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // 2
    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {
            if(bitmapHash.containsKey(urlStr)){
                Bitmap oldBitmap = bitmapHash.remove(urlStr);
                // 이전에 만든 것이 있으면 oldBitmap을 제거
                if(oldBitmap != null){
                    oldBitmap.recycle();
                    oldBitmap = null;
                }
            }

            URL url = new URL(urlStr);
            // image 를 받아옴
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            bitmapHash.put(urlStr, bitmap);
            if(bitmap == null){
                Log.d("why?", "I don't know");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    // 3
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    // 4
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
        imageView.invalidate();             // Image 를 다시 그려줌
    }
}
