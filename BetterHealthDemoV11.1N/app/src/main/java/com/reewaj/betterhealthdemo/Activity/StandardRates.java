package com.reewaj.betterhealthdemo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.reewaj.betterhealthdemo.R;

public class StandardRates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.standard_rates);

        PhotoView photoView = findViewById(R.id.photo_view);
        photoView .setImageResource(R.drawable.sr1);
    }
}

