package com.example.testtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.collection.ArrayMap;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //열차 코드
    String[] train = {"01","02","03","04","08","09","15"};
    // 지역 열차코드 match
    HashMap<String,String> arrayLocal= new HashMap<>();
    //1페이지당 보여지는 갯수
    Integer maxRow = 100;
    //페이지
    Integer pageNum = 1;
    //시간표 리스트
    ListView dataList;
    //시간표 리스트 아답터
    ItemAdapter itemAdapter;
    //뷰페이저 아답터
    PagerAdapter pagerAdapter;
    //뷰페이저
    ViewPager viewPager;
    //지역이름
    List name;
    // 시간표정보 데이터
    List<Item> trainDataItem = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //초기화
        init();


        //뷰페이저 리스너
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // 데이터 요청
                if (position != arrayLocal.size()-1) reqData(arrayLocal.get(name.get(position)),arrayLocal.get(name.get(position+1)));
                else {
                    //시간표 리스트 초기화 및 갱신
                    trainDataItem.clear();
                    refreshData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //데이터 요청
    private void reqData(String depPlace,String arrPlace){
        for(int i=0;i<train.length-1;i++){
            ApiService.ApiInterface service = ApiClient.getTrainInfoClient().create(ApiService.ApiInterface.class);
            Call<TrainData> call = service.getTrainInfo("7LT0Q7XeCAuzBmGUO7LmOnrkDGK2s7GZIJQdvdZ30lf7FmnTle%2BQoOqRKpjcohP14rouIrtag9KOoCZe%2BXuNxg%3D%3D"
                    , maxRow
                    , pageNum
                    ,depPlace
                    ,arrPlace
                    , Util.todayDate()
                    ,train[i]);
            call.enqueue(new Callback<TrainData>() {

                @Override
                public void onResponse(Call<TrainData> call, Response<TrainData> response) {
                    TrainData trainData = response.body();
                    List<Item> itemList = trainData.getItemList();
                    trainDataItem.addAll(itemList);
                    refreshData();
                }

                @Override
                public void onFailure(Call<TrainData> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }

    //데이터 갱신
    public void refreshData(){
        Collections.sort(trainDataItem);
        itemAdapter.notifyDataSetChanged();
        scrollList();
    }

    //초기화
    public void init(){
        arrayLocal.put("서울","NAT010000");
        arrayLocal.put("대전","NAT011668");
        name = new ArrayList(arrayLocal.keySet());
        dataList = findViewById(R.id.list_item);
        itemAdapter = new ItemAdapter(this,trainDataItem);
        dataList.setAdapter(itemAdapter);
        pagerAdapter = new PageAdapter(this,arrayLocal);
        viewPager = findViewById(R.id.item_pager);
        viewPager.setAdapter(pagerAdapter);
        reqData(arrayLocal.get(name.get(0)),arrayLocal.get(name.get(1)));
    }

    //현재시간으로 스크롤
    public void scrollList(){
        Integer position = 0;
        for(int i =0;i<=trainDataItem.size()-1;i++){
            if(!Util.lastTime(trainDataItem.get(i).getDepplandtime())) {
                position = i;
                break;
            }
        }
        dataList.smoothScrollToPosition(position);
    }
}
