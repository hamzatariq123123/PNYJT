package com.pnytrainings.pnyjt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();

        if(intent !=null){
            if(intent.hasExtra("my_input")){
                String data = intent.getStringExtra("my_input");
                textView.setText(data);
            }
        }
        Log.d("SecondActivity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("SecondActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SecondActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("SecondActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SecondActivity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
    }
}
