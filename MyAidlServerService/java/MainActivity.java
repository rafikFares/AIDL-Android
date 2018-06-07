package com.example.XXXX.myaidlserverservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.start)
    public void onButtonStartClick(View view) {
        Log.d("testing", "onButtonStartClick");
        startService(new Intent(this, MainServiceServer.class));
    }

    @OnClick(R.id.stop)
    public void onButtonStropClick(View view) {
        Log.d("testing", "onButtonStropClick");
        stopService(new Intent(this, MainServiceServer.class));
    }


}
