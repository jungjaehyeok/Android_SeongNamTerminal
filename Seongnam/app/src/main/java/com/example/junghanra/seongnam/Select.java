package com.example.junghanra.seongnam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

    }
    public void onClick05(View v) {                                     //고속버스
        Intent intent05 = new Intent(getApplicationContext(), expressbus.class);
        startActivity(intent05);
    }
    public void onClick06(View v) {                                     //시외버스
        Intent intent06 = new Intent(getApplicationContext(), terminal_1.class);
        startActivity(intent06);
    }
}
