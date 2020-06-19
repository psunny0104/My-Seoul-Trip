package com.myseoultrip.adapter;

import android.widget.ImageView;
import android.widget.TextView;

public class MakingItem {
    private String poiNameSt;
    private int poiIdxSt;
    private String subwayLineSt;
    private String stationMixNameSt;

    private String poiNameDst;
    private int poiIdxDst;
    private String subwayLineDst;
    private String stationMixNameDst;

    private String time;
    private String detailCourse;

    MakingItem(){};

    public MakingItem(String poiNameSt, int poiIdxSt, String subwayLineSt, String stationMixNameSt, String poiNameDst, int poiIdxDst, String subwayLineDst, String stationMixNameDst) {
        this.poiNameSt = poiNameSt;
        this.poiIdxSt = poiIdxSt;
        this.subwayLineSt = subwayLineSt;
        this.stationMixNameSt = stationMixNameSt;
        this.poiNameDst = poiNameDst;
        this.poiIdxDst = poiIdxDst;
        this.subwayLineDst = subwayLineDst;
        this.stationMixNameDst = stationMixNameDst;
    }

    public String getPoiNameSt() {
        return poiNameSt;
    }

    public void setPoiNameSt(String poiNameSt) {
        this.poiNameSt = poiNameSt;
    }

    public int getPoiIdxSt() {
        return poiIdxSt;
    }

    public void setPoiIdxSt(int poiIdxSt) {
        this.poiIdxSt = poiIdxSt;
    }

    public String getSubwayLineSt() {
        return subwayLineSt;
    }

    public void setSubwayLineSt(String subwayLineSt) {
        this.subwayLineSt = subwayLineSt;
    }

    public String getStationMixNameSt() {
        return stationMixNameSt;
    }

    public void setStationMixNameSt(String stationMixNameSt) {
        this.stationMixNameSt = stationMixNameSt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetailCourse() {
        return detailCourse;
    }

    public void setDetailCourse(String detailCourse) {
        this.detailCourse = detailCourse;
    }

    public String getPoiNameDst() {
        return poiNameDst;
    }

    public void setPoiNameDst(String poiNameDst) {
        this.poiNameDst = poiNameDst;
    }

    public int getPoiIdxDst() {
        return poiIdxDst;
    }

    public void setPoiIdxDst(int poiIdxDst) {
        this.poiIdxDst = poiIdxDst;
    }

    public String getSubwayLineDst() {
        return subwayLineDst;
    }

    public void setSubwayLineDst(String subwayLineDst) {
        this.subwayLineDst = subwayLineDst;
    }

    public String getStationMixNameDst() {
        return stationMixNameDst;
    }

    public void setStationMixNameDst(String stationMixNameDst) {
        this.stationMixNameDst = stationMixNameDst;
    }
}
