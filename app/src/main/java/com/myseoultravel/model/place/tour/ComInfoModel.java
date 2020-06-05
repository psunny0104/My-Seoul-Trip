package com.myseoultravel.model.place.tour;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComInfoModel {
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

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }
    }

    public class Header {

        @SerializedName("resultCode")
        @Expose
        public String resultCode;
        @SerializedName("resultMsg")
        @Expose
        public String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    public class Body {

        @SerializedName("items")
        @Expose
        public Items items;
        @SerializedName("numOfRows")
        @Expose
        public Integer numOfRows;
        @SerializedName("pageNo")
        @Expose
        public Integer pageNo;
        @SerializedName("totalCount")
        @Expose
        public Integer totalCount;

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }

        public Integer getNumOfRows() {
            return numOfRows;
        }

        public void setNumOfRows(Integer numOfRows) {
            this.numOfRows = numOfRows;
        }

        public Integer getPageNo() {
            return pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }
    }

    public class Items {

        @SerializedName("item")
        @Expose
        public Item item;

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }
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
        public Integer areacode;
        @SerializedName("booktour")
        @Expose
        public Integer booktour;
        @SerializedName("contentid")
        @Expose
        public Integer contentid;
        @SerializedName("contenttypeid")
        @Expose
        public Integer contenttypeid;
        @SerializedName("createdtime")
        @Expose
        public long createdtime;
        @SerializedName("firstimage")
        @Expose
        public String firstimage;
        @SerializedName("firstimage2")
        @Expose
        public String firstimage2;
        @SerializedName("homepage")
        @Expose
        public String homepage;
        @SerializedName("mapx")
        @Expose
        public Double mapx;
        @SerializedName("mapy")
        @Expose
        public Double mapy;
        @SerializedName("mlevel")
        @Expose
        public Integer mlevel;
        @SerializedName("modifiedtime")
        @Expose
        public long modifiedtime;
        @SerializedName("overview")
        @Expose
        public String overview;
        @SerializedName("tel")
        @Expose
        public String tel;
        @SerializedName("telname")
        @Expose
        public String telname;
        @SerializedName("sigungucode")
        @Expose
        public Integer sigungucode;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("zipcode")
        @Expose
        public String zipcode;

        @Override
        public String toString() {
            return "Item{" +
                    "addr1='" + addr1 + '\'' +
                    ", addr2='" + addr2 + '\'' +
                    ", areacode=" + areacode +
                    ", booktour=" + booktour +
                    ", contentid=" + contentid +
                    ", contenttypeid=" + contenttypeid +
                    ", createdtime=" + createdtime +
                    ", firstimage='" + firstimage + '\'' +
                    ", firstimage2='" + firstimage2 + '\'' +
                    ", homepage='" + homepage + '\'' +
                    ", mapx=" + mapx +
                    ", mapy=" + mapy +
                    ", mlevel=" + mlevel +
                    ", modifiedtime=" + modifiedtime +
                    ", overview='" + overview + '\'' +
                    ", sigungucode=" + sigungucode +
                    ", title='" + title + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    '}';
        }

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

        public Integer getAreacode() {
            return areacode;
        }

        public void setAreacode(Integer areacode) {
            this.areacode = areacode;
        }

        public Integer getBooktour() {
            return booktour;
        }

        public void setBooktour(Integer booktour) {
            this.booktour = booktour;
        }

        public Integer getContentid() {
            return contentid;
        }

        public void setContentid(Integer contentid) {
            this.contentid = contentid;
        }

        public Integer getContenttypeid() {
            return contenttypeid;
        }

        public void setContenttypeid(Integer contenttypeid) {
            this.contenttypeid = contenttypeid;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
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

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
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

        public Integer getMlevel() {
            return mlevel;
        }

        public void setMlevel(Integer mlevel) {
            this.mlevel = mlevel;
        }

        public long getModifiedtime() {
            return modifiedtime;
        }

        public void setModifiedtime(long modifiedtime) {
            this.modifiedtime = modifiedtime;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public Integer getSigungucode() {
            return sigungucode;
        }

        public void setSigungucode(Integer sigungucode) {
            this.sigungucode = sigungucode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTelname() {
            return telname;
        }

        public void setTelname(String telname) {
            this.telname = telname;
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
