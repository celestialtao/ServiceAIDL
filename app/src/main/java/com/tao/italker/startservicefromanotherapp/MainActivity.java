package com.tao.italker.startservicefromanotherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startService(new Intent(this, AppService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  stopService(new Intent(this, AppService.class));
    }
}
