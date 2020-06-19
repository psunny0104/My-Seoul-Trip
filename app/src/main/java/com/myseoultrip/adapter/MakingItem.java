package com.myseoultravel.adapter;

public class MakingItem {

    private String subwayName;
    private String poiName;
    private String subwayType;
    MakingItem(){};

    public MakingItem(String subwayName, String poiName) {
        this.subwayName = subwayName;
        this.poiName = poiName;
    }
    public MakingItem(String subwayName){
        this.subwayName = subwayName;
        this.poiName = "";
    }

    public String getSubwayName() {
        return subwayName;
    }

    public void setSubwayName(String subwayName) {
        this.subwayName = subwayName;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }
}
