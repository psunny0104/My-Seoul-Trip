package com.myseoultravel.adapter;

import java.util.ArrayList;

public class TravelItem {
    private String travelId;
    private String userId;
    private String travelIdx;
    private String travelStartDate;
    private String travelEndDate;
    private ArrayList<ScheduleItem> scheduleItems = new ArrayList<>();

    public TravelItem(){};

    public TravelItem(String travelIdx, String travelStartDate, String travelEndDate, ArrayList<ScheduleItem> scheduleItems) {
        this.travelIdx = travelIdx;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
        this.scheduleItems = scheduleItems;
    }

    public TravelItem(String userId, String travelIdx, String travelStartDate, String travelEndDate, ArrayList<ScheduleItem> scheduleItems) {
        this.userId = userId;
        this.travelIdx = travelIdx;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
        this.scheduleItems = scheduleItems;
    }

    public TravelItem(String travelId, String userId, String travelIdx, String travelStartDate, String travelEndDate, ArrayList<ScheduleItem> scheduleItems) {
        this.travelId = travelId;
        this.userId = userId;
        this.travelIdx = travelIdx;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
        this.scheduleItems = scheduleItems;
    }

    public TravelItem(String travelId, String userId, String travelIdx, String travelStartDate, String travelEndDate) {
        this.travelId = travelId;
        this.userId = userId;
        this.travelIdx = travelIdx;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getTravelIdx() {
        return travelIdx;
    }

    public void setTravelIdx(String travelIdx) {
        this.travelIdx = travelIdx;
    }

    public String getTravelStartDate() {
        return travelStartDate;
    }

    public void setTravelStartDate(String travelStartDate) {
        this.travelStartDate = travelStartDate;
    }

    public String getTravelEndDate() {
        return travelEndDate;
    }

    public void setTravelEndDate(String travelEndDate) {
        this.travelEndDate = travelEndDate;
    }

    public ArrayList<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

    public void setScheduleItems(ArrayList<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
