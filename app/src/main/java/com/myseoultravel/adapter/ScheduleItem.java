package com.myseoultravel.adapter;

import java.io.Serializable;

public class ScheduleItem implements Serializable {
    private int scheduleDayIdx;
    private String scheduleDate;
    private String scheduleSt;
    private String scheduleDst;
    private String scheduleCourseId;

    private Double scheduleStLng;
    private Double scheduleStLat;

    private Double scheduleDstLng;
    private Double scheduleDstLat;

    public ScheduleItem(){};

    public ScheduleItem(int scheduleDayIdx, String scheduleDate) {
        this.scheduleDayIdx = scheduleDayIdx;
        this.scheduleDate = scheduleDate;
        this.scheduleSt = "";
        this.scheduleDst = "";
    }

    public ScheduleItem(int scheduleDayIdx, String scheduleDate, String scheduleCourseId) {
        this.scheduleDayIdx = scheduleDayIdx;
        this.scheduleDate = scheduleDate;
        this.scheduleCourseId = scheduleCourseId;
    }

    public ScheduleItem(int scheduleDayIdx, String scheduleDate, String scheduleSt, String scheduleDst) {
        this.scheduleDayIdx = scheduleDayIdx;
        this.scheduleDate = scheduleDate;
        this.scheduleSt = scheduleSt;
        this.scheduleDst = scheduleDst;
    }

    public ScheduleItem(int scheduleDayIdx, String scheduleDate, String scheduleSt, String scheduleDst, String scheduleCourseId) {
        this.scheduleDayIdx = scheduleDayIdx;
        this.scheduleDate = scheduleDate;
        this.scheduleSt = scheduleSt;
        this.scheduleDst = scheduleDst;
        this.scheduleCourseId = scheduleCourseId;
    }


    public Double getScheduleStLng() {
        return scheduleStLng;
    }

    public void setScheduleStLng(Double scheduleStLng) {
        this.scheduleStLng = scheduleStLng;
    }

    public Double getScheduleStLat() {
        return scheduleStLat;
    }

    public void setScheduleStLat(Double scheduleStLat) {
        this.scheduleStLat = scheduleStLat;
    }

    public Double getScheduleDstLng() {
        return scheduleDstLng;
    }

    public void setScheduleDstLng(Double scheduleDstLng) {
        this.scheduleDstLng = scheduleDstLng;
    }

    public Double getScheduleDstLat() {
        return scheduleDstLat;
    }

    public void setScheduleDstLat(Double scheduleDstLat) {
        this.scheduleDstLat = scheduleDstLat;
    }

    public String getScheduleCourseId() {
        return scheduleCourseId;
    }

    public void setScheduleCourseId(String scheduleCourseId) {
        this.scheduleCourseId = scheduleCourseId;
    }

    public int getScheduleDayIdx() {
        return scheduleDayIdx;
    }

    public void setScheduleDayIdx(int scheduleDayIdx) {
        this.scheduleDayIdx = scheduleDayIdx;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleSt() {
        return scheduleSt;
    }

    public void setScheduleSt(String scheduleSt) {
        this.scheduleSt = scheduleSt;
    }

    public String getScheduleDst() {
        return scheduleDst;
    }

    public void setScheduleDst(String scheduleDst) {
        this.scheduleDst = scheduleDst;
    }
}
