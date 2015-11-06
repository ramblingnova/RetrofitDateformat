package com.example.tacademy.retrofit1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

//        Intent intent = getIntent();
//        TextView tv = (TextView)findViewById(R.id.sub_text);
//        tv.setText(intent.getStringExtra("user"));
        ImageView iv = (ImageView)findViewById(R.id.sub_image);
//        iv.setImageBitmap();

        ImageLoader loader;
        loader = ImageLoader.getInstance();
        loader.displayImage("http://bikee.s3.amazonaws.com/detail_1446776196619.jpg", iv);
    }

    
}
