package com.example.ina97.ina_prac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by ina97 on 2018-07-20.
 */

public class TodoActivity extends Activity {
    Button bt;
    Button bt_home;
    ArrayList<String> tdlist;
    ListView listView;
    ListViewAdapter listViewAdapter;
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_todo);

        bt = (Button)findViewById(R.id.bt_todo);
        bt_home = (Button)findViewById(R.id.bt_home);
        listView = (ListView)findViewById(R.id.tdlist);
        tdlist = new ArrayList<>();

        //Todolist 목록 받아와서 ListView에 띄우기
        SharedPreferences sf = getSharedPreferences("Todo", MODE_PRIVATE);
        Collection <?> collection = sf.getAll().values();
        Iterator<?> iterator = collection.iterator();

        while(iterator.hasNext()){
            tdlist.add(iterator.next().toString());
        }

        listViewAdapter = new ListViewAdapter(getApplicationContext(),tdlist);
        listView.setAdapter(listViewAdapter);

        //리스트 아이템 클릭시
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //해당 아이템 삭제
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sf = getSharedPreferences("Todo",MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                editor.remove(String.valueOf(listViewAdapter.getItem(position)));
                editor.commit();

                tdlist.remove(position);
                listView.setAdapter(listViewAdapter);
            }
        });

        //페이지 이동
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodoActivity.this, TodoSettingActivity.class);
                startActivity(intent);
            }
        });

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private class ListViewAdapter extends BaseAdapter{
        private ArrayList<String> list;
        private LayoutInflater inflater;

        private ListViewAdapter(Context context, ArrayList<String> arrayList){
            this.list=arrayList;
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if( convertView==null){
                convertView = inflater.inflate(R.layout.item_todo,parent,false);
                holder = new TodoActivity.ViewHolder();
                holder.tv = (TextView)convertView.findViewById(R.id.item_todo_tv);
                convertView.setTag(holder);
            }
            else {
                holder= (TodoActivity.ViewHolder)convertView.getTag();
            }

            holder.tv.setText(""+getItem(position));

            return convertView;
        }
    }

    private class ViewHolder{
        TextView tv;
    }
}
