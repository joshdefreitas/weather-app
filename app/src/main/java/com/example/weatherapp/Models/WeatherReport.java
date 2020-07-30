package com.example.weatherapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherReport {
    @SerializedName("coord")
    private Coord mCoord;
    @SerializedName("weather")
    private List<Weather> mWeather;
    @SerializedName("base")
    private String mBase;
    @SerializedName("main")
    private Main mMain;
    @SerializedName("wind")
    private Wind mWind;
    @SerializedName("clouds")
    private Cloud mClouds;
    private int mDt;
    @SerializedName("sys")
    private Sys mSys;
    @SerializedName("timezone")
    private int mTimezone;
    @SerializedName("id")
    private int miD;
    @SerializedName("name")
    private String mName;
    @SerializedName("code")
    private int mCode;

    public WeatherReport(Coord mCoord, List<Weather> mWeather, String mBase, Main mMain, Wind mWind, Cloud mClouds, int mDt, Sys mSys, int mTimezone, int miD, String mName, int mCode) {
        this.mCoord = mCoord;
        this.mWeather = mWeather;
        this.mBase = mBase;
        this.mMain = mMain;
        this.mWind = mWind;
        this.mClouds = mClouds;
        this.mDt = mDt;
        this.mSys = mSys;
        this.mTimezone = mTimezone;
        this.miD = miD;
        this.mName = mName;
        this.mCode = mCode;
    }

    public Coord getmCoord() {
        return mCoord;
    }

    public void setmCoord(Coord mCoord) {
        this.mCoord = mCoord;
    }

    public List<Weather> getmWeather() {
        return mWeather;
    }

    public void setmWeather(List<Weather> mWeather) {
        this.mWeather = mWeather;
    }

    public String getmBase() {
        return mBase;
    }

    public void setmBase(String mBase) {
        this.mBase = mBase;
    }

    public Main getmMain() {
        return mMain;
    }

    public void setmMain(Main mMain) {
        this.mMain = mMain;
    }

    public Wind getmWind() {
        return mWind;
    }

    public void setmWind(Wind mWind) {
        this.mWind = mWind;
    }

    public Cloud getmClouds() {
        return mClouds;
    }

    public void setmClouds(Cloud mClouds) {
        this.mClouds = mClouds;
    }

    public int getmDt() {
        return mDt;
    }

    public void setmDt(int mDt) {
        this.mDt = mDt;
    }

    public Sys getmSys() {
        return mSys;
    }

    public void setmSys(Sys mSys) {
        this.mSys = mSys;
    }

    public int getmTimezone() {
        return mTimezone;
    }

    public void setmTimezone(int mTimezone) {
        this.mTimezone = mTimezone;
    }

    public int getMiD() {
        return miD;
    }

    public void setMiD(int miD) {
        this.miD = miD;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmCode() {
        return mCode;
    }

    public void setmCode(int mCode) {
        this.mCode = mCode;
    }
}
