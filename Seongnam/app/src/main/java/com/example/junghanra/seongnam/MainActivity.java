package com.example.junghanra.seongnam;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick01(View v) {                                     //시간표 Button
        Intent intent01 = new Intent(getApplicationContext(), Select.class);
        startActivity(intent01);
    }
    public void onClick02(View v)                                       //오시는길 Button
    {
        Intent intent02 = new Intent(getApplicationContext(), googlemap.class);
        startActivity(intent02);

    }
    public void onClick03(View v)                                       //문의전화 Button
    {
        Intent intent03 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel: 1644-2689"));
        startActivity(intent03);

    }
    public void onClick04(View v)                                       //예약url Button
    {
        Intent intent04 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://txbus.t-money.co.kr/otck/trmlInfEnty.do"));
        startActivity(intent04);

    }


}
