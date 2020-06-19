package com.myseoultavel.adapter;

public class SelectAreaItem {
    int areaImage;
    String areaTitle;

    public int getAreaImage() { return areaImage; }

    public String getAreaTitle() { return areaTitle; }

    public void setAreaImage(int areaImage) {
        this.areaImage = areaImage;
    }

    public void setAreaTitle(String areaTitle) {
        this.areaTitle = areaTitle;
    }

    public SelectAreaItem(int areaImage, String areaTitle)
    {
        this.areaImage = areaImage;
        this.areaTitle = areaTitle;
    }
}
