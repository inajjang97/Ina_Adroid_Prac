package com.example.ina97.ina_prac;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ina97 on 2018-07-19.
 */

public class HelloActivity extends Activity {
    Button bt;
    EditText et;
    String Name = "File";
    public void onCreate(final Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_hello);

        bt= (Button)findViewById(R.id.bt);
        et= (EditText)findViewById(R.id.edit);

        et.setText(getPreference());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreference();
                Intent intent = new Intent(HelloActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onStop(){
        super.onStop();
        savePreference();
    }

    public String getPreference(){
        SharedPreferences sf = getSharedPreferences(Name,0);
        return sf.getString("name","");
    }

    //이름 저장하기
    public void savePreference() {
        SharedPreferences sf = getSharedPreferences(Name, 0);
        SharedPreferences.Editor editor = sf.edit();
        String str = et.getText().toString();
        editor.putString("name", str);
        editor.putString("xx", "xx");
        editor.commit();
    }

}
