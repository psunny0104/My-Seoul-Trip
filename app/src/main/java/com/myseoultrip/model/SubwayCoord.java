package com.myseoultrip.model;

public class SubwayCoord {
    private String stationCd;
    private String stationName;
    private String engName;
    private String lineNum;
    private String frCode;
    private String cyberStCode;
    private Double wgsX;
    private Double wgsY;

    public SubwayCoord(String stationCd, String stationName, String engName, String lineNum, String frCode, Double wgsY, Double wgsX) {
        this.stationCd = stationCd;
        this.stationName = stationName;
        this.engName = engName;
        this.lineNum = lineNum;
        this.frCode = frCode;
        this.wgsY = wgsY;
        this.wgsX = wgsX;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getStationCd() {
        return stationCd;
    }

    public void setStationCd(String stationCd) {
        this.stationCd = stationCd;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getFrCode() {
        return frCode;
    }

    public void setFrCode(String frCode) {
        this.frCode = frCode;
    }

    public String getCyberStCode() {
        return cyberStCode;
    }

    public void setCyberStCode(String cyberStCode) {
        this.cyberStCode = cyberStCode;
    }

    public Double getWgsX() {
        return wgsX;
    }

    public void setWgsX(Double wgsX) {
        this.wgsX = wgsX;
    }

    public Double getWgsY() {
        return wgsY;
    }

    public void setWgsY(Double wgsY) {
        this.wgsY = wgsY;
    }
}
