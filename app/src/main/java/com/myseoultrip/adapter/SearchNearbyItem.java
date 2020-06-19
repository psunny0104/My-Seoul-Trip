package com.myseoultavel.adapter;

public class SearchNearbyItem {
    String engName;
    String korName;
    String photo;

    public SearchNearbyItem(String engName, String photo){
        this.engName = engName;
        this.photo = photo;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
