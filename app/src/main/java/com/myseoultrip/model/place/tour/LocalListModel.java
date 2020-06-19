package com.myseoultrip.model.place.tour;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//8자리 넘어가는 Date->long으로 저장

public class LocalListModel {

    @SerializedName("response")
    @Expose
    public Response response;

    public class Response {

        @SerializedName("header")
        @Expose
        public Header header;
        @SerializedName("body")
        @Expose
        public Body body;

    }
    public class Header {

        @SerializedName("resultCode")
        @Expose
        public String resultCode;
        @SerializedName("resultMsg")
        @Expose
        public String resultMsg;

    }

    public class Body {

        @SerializedName("items")
        @Expose
        public Items items;
        @SerializedName("numOfRows")
        @Expose
        public int numOfRows;
        @SerializedName("pageNo")
        @Expose
        public int pageNo;
        @SerializedName("totalCount")
        @Expose
        public int totalCount;

    }

    public class Items {

        @SerializedName("item")
        @Expose
        //public Item item;
//        public List<Item> item = new ArrayList();
//
        public Item item;

    }

    public class Item {

        @SerializedName("addr1")
        @Expose
        public String addr1;
        @SerializedName("addr2")
        @Expose
        public String addr2;
        @SerializedName("areacode")
        @Expose
        public int areacode;
        @SerializedName("booktour")
        @Expose
        public int booktour;
        @SerializedName("cat1")
        @Expose
        public String cat1;
        @SerializedName("cat2")
        @Expose
        public String cat2;
        @SerializedName("cat3")
        @Expose
        public String cat3;
        @SerializedName("contentid")
        @Expose
        public int contentid;
        @SerializedName("contenttypeid")
        @Expose
        public int contenttypeid;
        @SerializedName("createdtime")
        @Expose
        public long createdtime;
        @SerializedName("dist")
        @Expose
        public int dist;
        @SerializedName("firstimage")
        @Expose
        public String firstimage;
        @SerializedName("firstimage2")
        @Expose
        public String firstimage2;
        @SerializedName("mapx")
        @Expose
        public Double mapx;
        @SerializedName("mapy")
        @Expose
        public Double mapy;
        @SerializedName("mlevel")
        @Expose
        public int mlevel;
        @SerializedName("modifiedtime")
        @Expose
        public long modifiedtime;
        @SerializedName("readcount")
        @Expose
        public int readcount;
        @SerializedName("sigungucode")
        @Expose
        public int sigungucode;
        @SerializedName("title")
        @Expose
        public String title;

        public String getAddr1() {
            return addr1;
        }

        public void setAddr1(String addr1) {
            this.addr1 = addr1;
        }

        public String getAddr2() {
            return addr2;
        }

        public void setAddr2(String addr2) {
            this.addr2 = addr2;
        }

        public int getAreacode() {
            return areacode;
        }

        public void setAreacode(int areacode) {
            this.areacode = areacode;
        }

        public int getBooktour() {
            return booktour;
        }

        public void setBooktour(int booktour) {
            this.booktour = booktour;
        }

        public String getCat1() {
            return cat1;
        }

        public void setCat1(String cat1) {
            this.cat1 = cat1;
        }

        public String getCat2() {
            return cat2;
        }

        public void setCat2(String cat2) {
            this.cat2 = cat2;
        }

        public String getCat3() {
            return cat3;
        }

        public void setCat3(String cat3) {
            this.cat3 = cat3;
        }

        public int getContentid() {
            return contentid;
        }

        public void setContentid(int contentid) {
            this.contentid = contentid;
        }

        public int getContenttypeid() {
            return contenttypeid;
        }

        public void setContenttypeid(int contenttypeid) {
            this.contenttypeid = contenttypeid;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public int getDist() {
            return dist;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        public String getFirstimage() {
            return firstimage;
        }

        public void setFirstimage(String firstimage) {
            this.firstimage = firstimage;
        }

        public String getFirstimage2() {
            return firstimage2;
        }

        public void setFirstimage2(String firstimage2) {
            this.firstimage2 = firstimage2;
        }

        public Double getMapx() {
            return mapx;
        }

        public void setMapx(Double mapx) {
            this.mapx = mapx;
        }

        public Double getMapy() {
            return mapy;
        }

        public void setMapy(Double mapy) {
            this.mapy = mapy;
        }

        public int getMlevel() {
            return mlevel;
        }

        public void setMlevel(int mlevel) {
            this.mlevel = mlevel;
        }

        public long getModifiedtime() {
            return modifiedtime;
        }

        public void setModifiedtime(long modifiedtime) {
            this.modifiedtime = modifiedtime;
        }

        public int getReadcount() {
            return readcount;
        }

        public void setReadcount(int readcount) {
            this.readcount = readcount;
        }

        public int getSigungucode() {
            return sigungucode;
        }

        public void setSigungucode(int sigungucode) {
            this.sigungucode = sigungucode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
