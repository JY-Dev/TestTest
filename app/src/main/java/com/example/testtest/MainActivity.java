package com.example.testtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String[] train = {"01","02","03","04","08","09","15"};
    List<Item> trainDataItem = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reqData();
    }
    private void reqData(){
        for(int i=0;i<train.length-1;i++){
            ApiService.ApiInterface service = ApiClient.getTrainInfoClient().create(ApiService.ApiInterface.class);
            Call<TrainData> call = service.getTrainInfo("7LT0Q7XeCAuzBmGUO7LmOnrkDGK2s7GZIJQdvdZ30lf7FmnTle%2BQoOqRKpjcohP14rouIrtag9KOoCZe%2BXuNxg%3D%3D"
                    , 100
                    , 1
                    ,"NAT010000"
                    ,"NAT011668"
                    ,"20200429"
                    ,train[i]);
            call.enqueue(new Callback<TrainData>() {

                @Override
                public void onResponse(Call<TrainData> call, Response<TrainData> response) {
                    TrainData trainData = response.body();
                    List<Item> itemList = trainData.getItemList();
                    trainDataItem.addAll(itemList);
                    check();
                }

                @Override
                public void onFailure(Call<TrainData> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }

    public void check(){
        System.out.println("check="+trainDataItem.size());
    }
}
