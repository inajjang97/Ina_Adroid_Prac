package com.example.ina97.ina_prac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ina97 on 2018-07-19.
 */

public class ReadActivity extends Activity{
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_read);

        Intent intent = getIntent();
        String Title= intent.getStringExtra("Title");
        String fName=Title+".txt";

        TextView title =(TextView)findViewById(R.id.title);
        TextView tv = (TextView)findViewById(R.id.tv_diary);
        StringBuffer buffer = new StringBuffer();

        title.setText(Title);
        try{
            FileInputStream fis = openFileInput(fName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String str=br.readLine();
            while(str!=null){
                buffer.append(str+"\n");
                str=br.readLine();
            }
            tv.setText(buffer.toString());
            br.close();
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "File not Found", Toast.LENGTH_SHORT).show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
