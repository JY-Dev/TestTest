package com.example.testtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {
    List<Item> data = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context mContext;

    ItemAdapter(Context context, List<Item> itemList){
        data = itemList;
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View convertView = view;
        if(convertView == null) convertView = layoutInflater.inflate(R.layout.data_item,null);
        TextView arr_time_tv = (TextView)convertView.findViewById(R.id.arr_time);
        TextView dep_time_tv = (TextView)convertView.findViewById(R.id.dep_time);
        TextView train_name_tv = (TextView)convertView.findViewById(R.id.train_name);
        arr_time_tv.setText(Util.parselDate(data.get(position).getArrplandtime()));
        dep_time_tv.setText(Util.parselDate(data.get(position).getDepplandtime()));
        train_name_tv.setText(data.get(position).getTraingradename());
        return convertView;
    }
}
