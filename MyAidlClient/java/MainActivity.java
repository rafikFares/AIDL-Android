package com.example.XXXX.myaidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.XXXX.myaidlserverservice.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonConcat)
    Button buttonConcat;
    @BindView(R.id.buttonString)
    Button buttonString;
    @BindView(R.id.buttonSum)
    Button buttonSum;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.etVal1)
    EditText etVal1;
    @BindView(R.id.etVal2)
    EditText etVal2;

    private IMainAidl iMainAidl = null;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        serviceConnection = getServiceConnection();

        makeBindig();
    }

    private void makeBindig() {
        if (iMainAidl == null) {
            Intent intent = new Intent("aidlservice");
            intent.setPackage("com.example.XXXX.myaidlserverservice");
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private ServiceConnection getServiceConnection() {
        return new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMainAidl = IMainAidl.Stub.asInterface(service);
                Toast.makeText(getApplicationContext(), "Service connected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                iMainAidl = null;
                Toast.makeText(getApplicationContext(), "Service connected", Toast.LENGTH_LONG).show();
            }
        };
    }

    @OnClick(R.id.buttonConcat)
    public void onButtonbuttonConcatClick(View view) {
        Log.d("testing", "onButtonbuttonConcatClick");
        String tmp = "";
        try {
            tmp = iMainAidl.getConcat(etVal1.getText().toString(), etVal2.getText().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        tvResult.setText("Result : " + tmp);
    }

    @OnClick(R.id.buttonString)
    public void onButtonbuttonStringClick(View view) {
        Log.d("testing", "onButtonbuttonStringClick");
        String tmp = "";
        try {
            tmp = iMainAidl.getStringOf(Integer.parseInt(etVal1.getText().toString()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        tvResult.setText("Result : " + tmp);
    }

    @OnClick(R.id.buttonSum)
    public void onButtonbuttonSumClick(View view) {
        Log.d("testing", "onButtonbuttonSumClick");
        int tmp = 0;
        try {
            tmp = iMainAidl.getSum(Integer.parseInt(etVal1.getText().toString()), Integer.parseInt(etVal2.getText().toString()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        tvResult.setText("Result : " + tmp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
