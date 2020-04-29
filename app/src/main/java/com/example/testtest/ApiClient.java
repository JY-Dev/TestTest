package com.example.testtest;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ApiClient {
    private static Retrofit retrofitTrainInfo = null;
    private static String trainUrl = "http://openapi.tago.go.kr";
    public static Retrofit getTrainInfoClient(){
        return (retrofitTrainInfo ==null) ? setRetrofit(retrofitTrainInfo, trainUrl) : retrofitTrainInfo;
    }

    private static Retrofit setRetrofit(Retrofit retrofit,String url){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                .build();
        return retrofit;
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println("request="+message);
            }
        });

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
