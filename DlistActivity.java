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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ina97 on 2018-07-19.
 */

public class DlistActivity extends Activity {
    TextView title;
    ListViewAdapter listViewAdapter;
    ListView listView;
    ArrayList <String> dlist;

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_dlist);

        SharedPreferences sf_name = getSharedPreferences("File",MODE_PRIVATE);
        String name = sf_name.getString("name","0");

        title= (TextView)findViewById(R.id.title);
        listView = (ListView)findViewById(R.id.listView);
        dlist = new ArrayList<String>();

        title.setText(name+"'s Diary");

        /*

        try{
            FileInputStream fis = openFileInput("data2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String str=br.readLine();
            while(str!=null){
                str=br.readLine();
                dlist.add(str);
            }
            br.close();
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "File not Found", Toast.LENGTH_SHORT).show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        */

        SharedPreferences sf = getSharedPreferences("Dlist", MODE_PRIVATE);
        Collection<?> col= sf.getAll().values();
        Iterator<?> it =col.iterator();

        while(it.hasNext()){
            String str=(String)it.next();
            dlist.add(str);
        }

        listViewAdapter = new ListViewAdapter(getApplicationContext(),dlist);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(),ReadActivity.class);
                intent1.putExtra("Title", dlist.get(position));
                startActivity(intent1);
            }
        });
    }

    public class ListViewAdapter extends BaseAdapter{
        final List<String> list;
        final LayoutInflater inflater;

        public ListViewAdapter(Context context, ArrayList<String> list) {
            this.list=list;
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
            ViewHolder holder = null;
            if( convertView==null){
                convertView = inflater.inflate(R.layout.item_dlist,parent,false);
                holder = new ViewHolder();
                holder.tvItemListView = (TextView)convertView.findViewById(R.id.item_dlist_title);
                convertView.setTag(holder);
            }
            else {
                holder= (ViewHolder)convertView.getTag();
            }
            holder.tvItemListView.setText(""+getItem(position));

            return convertView;
        }
    }

    private class ViewHolder{
        TextView tvItemListView;

    }
}


