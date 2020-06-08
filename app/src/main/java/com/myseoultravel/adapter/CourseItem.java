package com.myseoultravel.adapter;

import java.util.HashMap;

public class CourseItem {
    private String CourseItemId;

    private String courseStTitle;
    private String courseStAdd;
    private Double courseStLng;
    private Double courseStLat;

    private String courseDstTitle;
    private String courseDstAdd;
    private Double courseDstLng;
    private Double courseDstLat;

    private String courseDayIdx;
    private String courseDate;

    private String courseItemId;
    private HashMap<String, PoiItem> poiItemHashMap = new HashMap<String, PoiItem>();

    public CourseItem(){};

    public CourseItem(String courseItemId, String courseStTitle, String courseStAdd, Double courseStLng, Double courseStLat, String courseDstTitle, String courseDstAdd, Double courseDstLng, Double courseDstLat, HashMap<String, PoiItem> poiItemHashMap) {
        this.CourseItemId = courseItemId;
        this.courseStTitle = courseStTitle;
        this.courseStAdd = courseStAdd;
        this.courseStLng = courseStLng;
        this.courseStLat = courseStLat;
        this.courseDstTitle = courseDstTitle;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
        this.poiItemHashMap = poiItemHashMap;
    }

    public CourseItem(String courseItemId, String courseDstAdd, Double courseDstLng, Double courseDstLat) {
        this.CourseItemId = courseItemId;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
    }

    public CourseItem(String courseItemId, String courseStAdd, Double courseStLng, Double courseStLat, String courseDstAdd, Double courseDstLng, Double courseDstLat) {
        this.CourseItemId = courseItemId;
        this.courseStAdd = courseStAdd;
        this.courseStLng = courseStLng;
        this.courseStLat = courseStLat;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
    }

    public CourseItem(String courseItemId, String courseStAdd, Double courseStLng, Double courseStLat, String courseDstAdd, Double courseDstLng, Double courseDstLat, String courseDayIdx, String courseDate) {
        this.CourseItemId = courseItemId;
        this.courseStAdd = courseStAdd;
        this.courseStLng = courseStLng;
        this.courseStLat = courseStLat;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
        this.courseDayIdx = courseDayIdx;
        this.courseDate = courseDate;
    }

    public String getCourseDayIdx() {
        return courseDayIdx;
    }

    public void setCourseDayIdx(String courseDayIdx) {
        this.courseDayIdx = courseDayIdx;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getCourseItemId() {
        return CourseItemId;
    }

    public void setCourseItemId(String courseItemId) {
        CourseItemId = courseItemId;
    }

    public String getCourseStTitle() {
        return courseStTitle;
    }

    public void setCourseStTitle(String courseStTitle) {
        this.courseStTitle = courseStTitle;
    }

    public String getCourseStAdd() {
        return courseStAdd;
    }

    public void setCourseStAdd(String courseStAdd) {
        this.courseStAdd = courseStAdd;
    }

    public Double getCourseStLng() {
        return courseStLng;
    }

    public void setCourseStLng(Double courseStLng) {
        this.courseStLng = courseStLng;
    }

    public Double getCourseStLat() {
        return courseStLat;
    }

    public void setCourseStLat(Double courseStLat) {
        this.courseStLat = courseStLat;
    }

    public String getCourseDstTitle() {
        return courseDstTitle;
    }

    public void setCourseDstTitle(String courseDstTitle) {
        this.courseDstTitle = courseDstTitle;
    }

    public String getCourseDstAdd() {
        return courseDstAdd;
    }

    public void setCourseDstAdd(String courseDstAdd) {
        this.courseDstAdd = courseDstAdd;
    }

    public Double getCourseDstLng() {
        return courseDstLng;
    }

    public void setCourseDstLng(Double courseDstLng) {
        this.courseDstLng = courseDstLng;
    }

    public Double getCourseDstLat() {
        return courseDstLat;
    }

    public void setCourseDstLat(Double courseDstLat) {
        this.courseDstLat = courseDstLat;
    }

    public HashMap<String, PoiItem> getPoiItemHashMap() {
        return poiItemHashMap;
    }

    public void setPoiItemHashMap(HashMap<String, PoiItem> poiItemHashMap) {
        this.poiItemHashMap = poiItemHashMap;
    }
}
