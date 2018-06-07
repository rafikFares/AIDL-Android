package com.example.XXXX.myaidlserverservice;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainServiceServer extends Service {

    private final IMainAidl.Stub binder = new IMainAidl.Stub() {

        @Override
        public String getConcat(String val1, String val2) throws RemoteException {
            String tmp = val1 + " & " + val2;
            return tmp;
        }

        @Override
        public String getStringOf(int val) throws RemoteException {
            String tmp = String.valueOf(val);
            return tmp;
        }

        @Override
        public int getSum(int val1, int val2) throws RemoteException {
            int tmp = val1 + val2;
            return tmp;
        }
    };

    public MainServiceServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ServicesTest", "MyService est dans onStartCommand");

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ServicesTest", "MyService est dans onDestroy");
    }


}