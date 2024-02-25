package org.duchoang.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity_Thread extends AppCompatActivity {

    String url = "https://images.pexels.com/photos/3184454/pexels-photo-3184454.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1";

    Button button2;
    ImageView imageView2;

    public void Loading(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downloadImage(url);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView2.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }

    private Bitmap downloadImage(String urlString) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thread);

        button2 = findViewById(R.id.button2);
        imageView2 = findViewById(R.id.imageView2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading(v);
            }
        });
    }
}
