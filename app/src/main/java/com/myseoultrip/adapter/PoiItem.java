package com.myseoultrip.adapter;
import java.io.Serializable;
import java.util.Date;

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
    private String travelItemId;
    private String courseItemId;

    private int subwayIdx;
    private String lineName;
    private String stationName;
    private String stationNameEng;
    private Date estimatedUsingTime;
    private Double dist;
    private int order;

    public PoiItem(){};

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

    public PoiItem(int poiIdx, int poiContentId, int poiContentTypeId, Double poiMapX, Double poiMapY, String poiAddress, String poiTitle, String poiImage, String poiType, String travelItemId) {
        this.poiIdx = poiIdx;
        this.poiContentId = poiContentId;
        this.poiContentTypeId = poiContentTypeId;
        this.poiMapX = poiMapX;
        this.poiMapY = poiMapY;
        this.poiAddress = poiAddress;
        this.poiTitle = poiTitle;
        this.poiImage = poiImage;
        this.poiType = poiType;
        this.travelItemId = travelItemId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Double getDist() {
        return dist;
    }

    public void setDist(Double dist) {
        this.dist = dist;
    }

    public int getSubwayIdx() {
        return subwayIdx;
    }

    public void setSubwayIdx(int subwayIdx) {
        this.subwayIdx = subwayIdx;
    }

    public String getStationNameEng() {
        return stationNameEng;
    }

    public void setStationNameEng(String stationNameEng) {
        this.stationNameEng = stationNameEng;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getEstimatedUsingTime() {
        return estimatedUsingTime;
    }

    public void setEstimatedUsingTime(Date estimatedUsingTime) {
        this.estimatedUsingTime = estimatedUsingTime;
    }

    public String getTravelItemId() {
        return travelItemId;
    }

    public void setTravelItemId(String travelItemId) {
        this.travelItemId = travelItemId;
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

    public String getCourseItemId() {
        return courseItemId;
    }

    public void setCourseItemId(String courseItemId) {
        this.courseItemId = courseItemId;
    }
}
