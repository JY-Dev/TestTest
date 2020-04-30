package com.example.testtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class PageAdapter extends PagerAdapter {
    private Context mContext;
    private HashMap<String,String> localArray;
    private List name;

    public PageAdapter(Context context , HashMap<String,String> arrayList){
        mContext = context;
        localArray = arrayList;
        name = new ArrayList(arrayList.keySet());
    }

    @Override
    public int getCount() {
        return localArray.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }

    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null ;

        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.page_item, container, false);

            TextView textView = (TextView) view.findViewById(R.id.local_tv) ;
            textView.setText(name.get(position).toString()) ;
        }

        // 뷰페이저에 추가.
        container.addView(view) ;

        return view ;
    }
}
