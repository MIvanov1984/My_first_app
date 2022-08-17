package com.example.enablewifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private WifiManager wifiManager;
    private static final String TAG ="wifi";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiManager=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public void enableWifif(View view) {
        Log.d(TAG, "enableWifif: enabled");
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.Q)
            wifiManager.setWifiEnabled(true);
        else
        {
            // for SDK 29 and higher as wifiManager has been deprecated
            //but this call only WiFi panel to toggle WiFi
            Intent panelIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
            startActivityForResult(panelIntent,1);
        }
    }

    public void disableWifi(View view) {
        Log.d(TAG, "disableWifi: disabled");
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.Q)
            wifiManager.setWifiEnabled(false);
        else
        {
            Intent panelIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
            startActivityForResult(panelIntent,0);
        }
    }
}