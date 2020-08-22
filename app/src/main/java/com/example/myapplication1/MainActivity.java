package com.example.myapplication1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.AbstractCollection;

public class MainActivity extends AppCompatActivity {
    Toast toast;
    private static Context mcontext;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.jump_b);
        Button btn2 = findViewById(R.id.imei_b);
        Button btn3 = findViewById(R.id.tel_b);
        final TextView text1 = findViewById(R.id.text1);

//        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this,"跳转按钮被点击了",Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,showActivity.class);
                startActivity(intent);
            }
        });

        final String txt1 = "imei: ";
        final String txt2 = "phone";
        final String txt = "start";
        text1.setText(txt);


        final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            Toast toast = Toast.makeText(this,"权限申请失败",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText(txt1 + getImei(mcontext,telephonyManager));
//                text1.setText(txt1);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText(txt2 + getPhoneNo(mcontext,telephonyManager));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getImei(Context context, TelephonyManager telephonyManager) {


        String Imei = telephonyManager.getImei();
        Log.e("IMEI:",Imei);
        if (Imei.isEmpty()){
            Toast toast = Toast.makeText(MainActivity.this,"Imei() 获取失败",Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast = Toast.makeText(MainActivity.this,"Imei() 获取成功",Toast.LENGTH_SHORT);
            toast.show();
            return Imei;
        }

        Imei = telephonyManager.getDeviceId();
        Log.e("IMEI:",Imei);
        if (Imei.isEmpty()){
            Toast toast = Toast.makeText(MainActivity.this,"DeviceId() 获取失败",Toast.LENGTH_SHORT);
            toast.show();
        }else {

            Toast toast = Toast.makeText(MainActivity.this,"DeviceId() 获取成功",Toast.LENGTH_SHORT);
            toast.show();
            return Imei;
        }
        return "";
    }

    public String getPhoneNo(Context context, TelephonyManager telephonyManager) {
        String number = telephonyManager.getLine1Number();
        Log.e("Number:",number);
        if (number.isEmpty()){
            Toast toast = Toast.makeText(MainActivity.this,"getLine1Number() 获取失败",Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast = Toast.makeText(MainActivity.this,"getLine1Number() 获取成功",Toast.LENGTH_SHORT);
            toast.show();
            return number;
        }

        //修改了这行

        return "";
    }
}
