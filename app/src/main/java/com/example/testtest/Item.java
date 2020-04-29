package com.example.testtest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Item implements Comparable<Item>{
    @Element(name = "adultcharge")
    private String adultcharge;
    @Element(name = "arrplacename")
    private String arrplacename;
    @Element(name = "arrplandtime")
    private String arrplandtime;
    @Element(name = "depplacename")
    private String depplacename;
    @Element(name = "depplandtime")
    private String depplandtime;
    @Element(name = "traingradename")
    private String traingradename;
    @Element(name = "trainno")
    private String trainno;

    public String getAdultcharge() {
        return adultcharge;
    }

    public void setAdultcharge(String adultcharge) {
        this.adultcharge = adultcharge;
    }

    public String getArrplacename() {
        return arrplacename;
    }

    public void setArrplacename(String arrplacename) {
        this.arrplacename = arrplacename;
    }

    public String getArrplandtime() {
        return arrplandtime;
    }

    public void setArrplandtime(String arrplandtime) {
        this.arrplandtime = arrplandtime;
    }

    public String getDepplacename() {
        return depplacename;
    }

    public void setDepplacename(String depplacename) {
        this.depplacename = depplacename;
    }

    public String getDepplandtime() {
        return depplandtime;
    }

    public void setDepplandtime(String depplandtime) {
        this.depplandtime = depplandtime;
    }

    public String getTraingradename() {
        return traingradename;
    }

    public void setTraingradename(String traingradename) {
        this.traingradename = traingradename;
    }

    public String getTrainno() {
        return trainno;
    }

    public void setTrainno(String trainno) {
        this.trainno = trainno;
    }

    @Override
    public int compareTo(Item item) {
        return this.arrplandtime.compareTo(item.arrplandtime);
    }
}
