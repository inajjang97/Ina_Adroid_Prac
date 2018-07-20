package com.example.ina97.ina_prac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ina97 on 2018-07-19.
 */

public class CalendarActivity extends Activity {
    private TextView tvDate;
    private GridAdapter gridAdapter;
    private ArrayList<String> dayList;
    private GridView gridView;
    private Calendar mcal;

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_cal);

        tvDate = (TextView)findViewById(R.id.tv_date);
        gridView = (GridView)findViewById(R.id.gridView);

        //오늘 날짜 세팅
        long today = System.currentTimeMillis();
        final Date date = new Date(today);

        //년도,월,일
        final SimpleDateFormat curYear = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonth = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDay = new SimpleDateFormat("dd", Locale.KOREA);

        tvDate.setText(curYear.format(date)+"/"+curMonth.format(date)+"/"+curDay.format(date));

        dayList = new ArrayList<String>();
        dayList.add("SUN");
        dayList.add("MON");
        dayList.add("TUE");
        dayList.add("WED");
        dayList.add("THU");
        dayList.add("FRI");
        dayList.add("SAT");

        mcal = Calendar.getInstance();

        //1일이 무슨 요일인지
        mcal.set(Integer.parseInt(curYear.format(date)),Integer.parseInt(curMonth.format(date))-1,1);
        int dayNum = mcal.get(Calendar.DAY_OF_WEEK);
        for(int i=0; i<dayNum; i++){
            dayList.add("");
        }
        setCalendarDate(mcal.get(Calendar.MONTH)+1);
        gridAdapter= new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

    }

    private void setCalendarDate(int month) {
        mcal.set(Calendar.MONTH,month-1);
        for(int i=0; i<mcal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            dayList.add(""+(i+1));
        }
    }

    private class GridAdapter extends BaseAdapter{
        private final List<String> list;
        private final LayoutInflater inflater;

        private GridAdapter(Context context, ArrayList<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                convertView = inflater.inflate(R.layout.item_cal,parent,false);
                holder = new ViewHolder();
                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.item_tv);
                convertView.setTag(holder);
            }
            else {
                holder= (ViewHolder)convertView.getTag();
            }
            holder.tvItemGridView.setText(""+getItem(position));

            mcal = Calendar.getInstance();

            Integer today = mcal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);

            if( sToday.equals(getItem(position))){
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorToday));
            }
            return convertView;
        }

    }

    private class ViewHolder{
        TextView tvItemGridView;
    }
}
