package com.myseoultravel.adapter;
import java.io.Serializable;

public class PoiItem implements Serializable{
    private int poiIdx;
    private int poiContentId;
    private int poiContentTypeId;
    private Double poiMapX;
    private Double poiMapY;
    private String poiAddress;
    private String poiTitle;
    private String poiImage;
    private String poiType;
    private String courseItemId;
    PoiItem(){};

    public PoiItem(int poiIdx, int poiContentId, int poiContentTypeId, Double poiMapX, Double poiMapY, String poiAddress, String poiTitle, String poiImage) {
        this.poiIdx = poiIdx;
        this.poiContentId = poiContentId;
        this.poiContentTypeId = poiContentTypeId;
        this.poiMapX = poiMapX;
        this.poiMapY = poiMapY;
        this.poiAddress = poiAddress;
        this.poiTitle = poiTitle;
        this.poiImage = poiImage;
    }

    public PoiItem(int poiIdx, int poiContentId, int poiContentTypeId, Double poiMapX, Double poiMapY, String poiAddress, String poiTitle, String poiImage, String poiType) {
        this.poiIdx = poiIdx;
        this.poiContentId = poiContentId;
        this.poiContentTypeId = poiContentTypeId;
        this.poiMapX = poiMapX;
        this.poiMapY = poiMapY;
        this.poiAddress = poiAddress;
        this.poiTitle = poiTitle;
        this.poiImage = poiImage;
        this.poiType = poiType;
    }

    public String getCourseItemId() {
        return courseItemId;
    }

    public void setCourseItemId(String courseItemId) {
        this.courseItemId = courseItemId;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getPoiImage() {
        return poiImage;
    }

    public void setPoiImage(String poiImage) {
        this.poiImage = poiImage;
    }

    public int getPoiIdx() {
        return poiIdx;
    }

    public void setPoiIdx(int poiIdx) {
        this.poiIdx = poiIdx;
    }

    public int getPoiContentId() {
        return poiContentId;
    }

    public void setPoiContentId(int poiContentId) {
        this.poiContentId = poiContentId;
    }

    public int getPoiContentTypeId() {
        return poiContentTypeId;
    }

    public void setPoiContentTypeId(int poiContentTypeId) {
        this.poiContentTypeId = poiContentTypeId;
    }

    public Double getPoiMapX() {
        return poiMapX;
    }

    public void setPoiMapX(Double poiMapX) {
        this.poiMapX = poiMapX;
    }

    public Double getPoiMapY() {
        return poiMapY;
    }

    public void setPoiMapY(Double poiMapY) {
        this.poiMapY = poiMapY;
    }

    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }

    public String getPoiTitle() {
        return poiTitle;
    }

    public void setPoiTitle(String poiTitle) {
        this.poiTitle = poiTitle;
    }
}
