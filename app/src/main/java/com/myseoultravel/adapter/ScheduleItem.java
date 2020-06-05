package com.myseoultravel.adapter;

import android.widget.Button;

import java.io.Serializable;
import java.util.Calendar;

public class ScheduleItem implements Serializable {
    private String scheduleDayIdx;
    private String scheduleDate;
    //private Button scheduleAdd;

    public ScheduleItem(String scheduleDayIdx, String scheduleDate) {
        this.scheduleDayIdx = scheduleDayIdx;
        this.scheduleDate = scheduleDate;
        //this.scheduleAdd = scheduleAdd;
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

}
