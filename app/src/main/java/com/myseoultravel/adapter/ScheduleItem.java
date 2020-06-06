package com.myseoultravel.adapter;

import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

public class ScheduleItem implements Serializable {
    private String scheduleItem;
    private String scheduleDayIdx;
    private String scheduleDate;
    private String scheduleSt;
    private String scheduleDst;

    public ScheduleItem(){};

    public ScheduleItem(String scheduleDayIdx, String scheduleDate) {
        this.scheduleDayIdx = scheduleDayIdx;
        this.scheduleDate = scheduleDate;
        this.scheduleSt = "";
        this.scheduleDst = "";
    }

    public ScheduleItem(String scheduleDayIdx, String scheduleDate, String scheduleSt, String scheduleDst) {
        this.scheduleDayIdx = scheduleDayIdx;
        this.scheduleDate = scheduleDate;
        this.scheduleSt = scheduleSt;
        this.scheduleDst = scheduleDst;
    }

    public ScheduleItem(String scheduleItem, String scheduleDayIdx, String scheduleDate, String scheduleSt, String scheduleDst) {
        this.scheduleItem = scheduleItem;
        this.scheduleDayIdx = scheduleDayIdx;
        this.scheduleDate = scheduleDate;
        this.scheduleSt = scheduleSt;
        this.scheduleDst = scheduleDst;
    }

    public String getScheduleItem() {
        return scheduleItem;
    }

    public void setScheduleItem(String scheduleItem) {
        this.scheduleItem = scheduleItem;
    }

    public String getScheduleDayIdx() {
        return scheduleDayIdx;
    }

    public void setScheduleDayIdx(String scheduleDayIdx) {
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
