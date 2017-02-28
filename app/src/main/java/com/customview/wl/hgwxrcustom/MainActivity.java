package com.customview.wl.hgwxrcustom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("1",System.currentTimeMillis()+"");
        setContentView(R.layout.activity_main);
        Log.e("2",System.currentTimeMillis()+"");
    }
}
