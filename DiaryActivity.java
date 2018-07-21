package com.example.ina97.ina_prac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.PrintWriter;


/**
 * Created by ina97 on 2018-07-19.
 */

public class DiaryActivity extends Activity {
    String fname;
    Button bt;
    TextView tv;
    EditText et;
    DatePicker datePicker;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_diary);

        SharedPreferences sf = getSharedPreferences("File",MODE_PRIVATE);
        String name = sf.getString("name","0");

        tv=(TextView)findViewById(R.id.title);
        bt = (Button) findViewById(R.id.bt);
        et = (EditText) findViewById(R.id.content);
        datePicker = (DatePicker) findViewById(R.id.date);

        tv.setText(name+"'s Diary");
        int year=datePicker.getYear();
        int month = datePicker.getMonth()+1;
        int day=datePicker.getDayOfMonth();
        fname = Integer.toString(year) + "_"
                + Integer.toString(month ) + "_"
                + Integer.toString(day);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                fname = Integer.toString(year) + "_"
                        + Integer.toString(month+1) + "_"
                        + Integer.toString(day);
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiary(fname);
                Intent intent = new Intent(DiaryActivity.this, DlistActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveDiary(String fname) {
        try{
            //일기 쓴 날짜 기록
            SharedPreferences sf = getSharedPreferences("Dlist", MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(fname,fname);
            editor.commit();

            /*
            FileOutputStream data = openFileOutput("data2.txt",Context.MODE_APPEND);
            PrintWriter writer = new PrintWriter(data);
            writer.println(fname);
            writer.close();
            Toast.makeText(this,"Save "+fname, Toast.LENGTH_SHORT).show();
            */

            //일기 내용 기록
            FileOutputStream fos = openFileOutput(fname+".txt", Context.MODE_APPEND);
            PrintWriter writer = new PrintWriter(fos);
            writer.println(et.getText().toString());
            writer.close();
        }

        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
