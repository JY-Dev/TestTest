package com.example.testtest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

class ApiService {
    interface ApiInterface{
        @GET("/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo?")
        Call<TrainData> getTrainInfo(@Query(value = "serviceKey" ,encoded = true) String serviceKey,
                                     @Query("numOfRows") Integer numOfRows,
                                     @Query("pageNo") Integer pageNo,
                                     @Query("depPlaceId") String depPlaceId,
                                     @Query("arrPlaceId") String arrPlaceId,
                                     @Query("depPlandTime") String depPlandTime,
                                     @Query("trainGradeCode") String trainGradeCode);
    }

}
