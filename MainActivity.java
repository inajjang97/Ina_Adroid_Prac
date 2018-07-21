package com.example.ina97.ina_prac;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tv_name;
    Button bt_cal;
    Button bt_diary;
    Button bt_todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sf
        SharedPreferences sf = getSharedPreferences("File",MODE_PRIVATE);
        String name = sf.getString("name","0");

        tv_name=(TextView)findViewById(R.id.tv2);
        bt_cal=(Button)findViewById(R.id.bt_cal);
        bt_diary=(Button)findViewById(R.id.bt_diary);
        bt_todo = (Button)findViewById(R.id.bt_todo);
        tv_name.setText("Hello, "+name);

        bt_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        bt_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });

        bt_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TodoActivity.class);
                startActivity(intent);
            }
        });
    }
}
