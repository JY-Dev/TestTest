package com.example.testtest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String todayDate(){
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(today);
    }
    public static String parselDate(String date){
        String time = date.substring(8,12);
        return time.substring(0,2)+":"+time.substring(2,4);
    }
}