package com.myseoultrip.adapter;

public class CourseItem {
    private String travelItemId;

    private String courseStTitle;
    private String courseStAdd;
    private Double courseStLng;
    private Double courseStLat;

    private String courseDstTitle;
    private String courseDstAdd;
    private Double courseDstLng;
    private Double courseDstLat;

    private int courseDayIdx;
    private String courseDate;

    private String courseItemId;

    public CourseItem(){};

    public CourseItem(String travelItemId, String courseStTitle, String courseStAdd, Double courseStLng, Double courseStLat, String courseDstTitle, String courseDstAdd, Double courseDstLng, Double courseDstLat) {
        this.travelItemId = travelItemId;
        this.courseStTitle = courseStTitle;
        this.courseStAdd = courseStAdd;
        this.courseStLng = courseStLng;
        this.courseStLat = courseStLat;
        this.courseDstTitle = courseDstTitle;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
    }

    public CourseItem(String travelItemId, String courseDstAdd, Double courseDstLng, Double courseDstLat) {
        this.travelItemId = travelItemId;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
    }

    public CourseItem(String travelItemId, String courseStAdd, Double courseStLng, Double courseStLat, String courseDstAdd, Double courseDstLng, Double courseDstLat) {
        this.travelItemId = travelItemId;
        this.courseStAdd = courseStAdd;
        this.courseStLng = courseStLng;
        this.courseStLat = courseStLat;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
    }

    public CourseItem(String travelItemId, String courseStAdd, Double courseStLng, Double courseStLat, String courseDstAdd, Double courseDstLng, Double courseDstLat, int courseDayIdx, String courseDate) {
        this.travelItemId = travelItemId;
        this.courseStAdd = courseStAdd;
        this.courseStLng = courseStLng;
        this.courseStLat = courseStLat;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
        this.courseDayIdx = courseDayIdx;
        this.courseDate = courseDate;
    }

    public CourseItem(String travelItemId, String courseStAdd, Double courseStLng, Double courseStLat,  String courseDstAdd, Double courseDstLng, Double courseDstLat, int courseDayIdx, String courseDate, String courseItemId) {
        this.travelItemId = travelItemId;
        this.courseStAdd = courseStAdd;
        this.courseStLng = courseStLng;
        this.courseStLat = courseStLat;
        this.courseDstAdd = courseDstAdd;
        this.courseDstLng = courseDstLng;
        this.courseDstLat = courseDstLat;
        this.courseDayIdx = courseDayIdx;
        this.courseDate = courseDate;
        this.courseItemId = courseItemId;
    }

    public int getCourseDayIdx() {
        return courseDayIdx;
    }

    public void setCourseDayIdx(int courseDayIdx) {
        this.courseDayIdx = courseDayIdx;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getTravelItemId() {
        return travelItemId;
    }

    public void setTravelItemId(String travelItemId) {
        this.travelItemId = travelItemId;
    }

    public String getCourseItemId() {
        return courseItemId;
    }

    public void setCourseItemId(String courseItemId) {
        this.courseItemId = courseItemId;
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

}
