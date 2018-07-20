package com.example.ina97.ina_prac;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ina97 on 2018-07-20.
 */

public class TodoSettingActivity extends Activity {
    Button bt;
    EditText et;

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_todo_setting);

        bt = (Button)findViewById(R.id.bt_todo_setting);
        et = (EditText)findViewById(R.id.et_todo_setting);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.getText().toString()!=null){
                    SharedPreferences sf = getSharedPreferences("Todo", MODE_PRIVATE);
                    SharedPreferences.Editor editor =sf.edit();
                    editor.putString(et.getText().toString(), et.getText().toString());
                    editor.commit();
                }

                Intent intent1 = new Intent(TodoSettingActivity.this, TodoActivity.class);
                startActivity(intent1);
            }
        });
    }
}
