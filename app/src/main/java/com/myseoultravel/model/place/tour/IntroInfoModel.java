package com.myseoultravel.model.place.tour;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IntroInfoModel {


    @SerializedName("response")
    @Expose
    public Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

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
        /**
         * 공통
         */
        @SerializedName("contentid")
        @Expose
        public Integer contentid;
        @SerializedName("contenttypeid")
        @Expose
        public Integer contenttypeid;


        /**
         * 관광지
         */
        @SerializedName("chkbabycarriage")
        @Expose
        public String chkbabycarriage;//유모차대여여부
        @SerializedName("chkcreditcard")
        @Expose
        public String chkcreditcard;//신용카드가능여부
        @SerializedName("chkpet")
        @Expose
        public String chkpet;//애완동물가능여부
        @SerializedName("expguide")
        @Expose
        public String expguide;//체험안내
        @SerializedName("heritage1")
        @Expose
        public Integer heritage1;//세계문화유산유무
        @SerializedName("heritage2")
        @Expose
        public Integer heritage2;//세계자연유산유무
        @SerializedName("heritage3")
        @Expose
        public Integer heritage3;//세계기록유산유무
        @SerializedName("infocenter")
        @Expose
        public String infocenter;//문의및안내
        @SerializedName("opendate")
        @Expose
        public String opendate;//개장일
        @SerializedName("parking")
        @Expose
        public String parking;//주차시설
        @SerializedName("restdate")
        @Expose
        public String restdate;//쉬는날
        @SerializedName("useseason")
        @Expose
        public String useseason;//이용시기
        @SerializedName("usetime")
        @Expose
        public String usetime;//이용시간

        /**
         * 문화시설
         */
        @SerializedName("accomcountculture")
        @Expose
        public String accomcountculture;//수용인원
        @SerializedName("chkbabycarriageculture")
        @Expose
        public String chkbabycarriageculture;//유모차대여여부
        @SerializedName("chkcreditcardculture")
        @Expose
        public String chkcreditcardculture;//신용카드가능여부
        @SerializedName("chkpetculture")
        @Expose
        public String chkpetculture;//애완동물가능여부
        @SerializedName("discountinfo")
        @Expose
        public String discountinfo;//할인정보
        @SerializedName("infocenterculture")
        @Expose
        public String infocenterculture;//문의및안내
        @SerializedName("parkingculture")
        @Expose
        public String parkingculture;//주차시설
        @SerializedName("parkingfee")
        @Expose
        public String parkingfee;//주차요금
        @SerializedName("restdateculture")
        @Expose
        public String restdateculture;//쉬는날
        @SerializedName("scale")
        @Expose
        public String scale;//규모
        @SerializedName("usefee")
        @Expose
        public String usefee;//이용요금
        @SerializedName("usetimeculture")
        @Expose
        public String usetimeculture;//이용시간
        @SerializedName("spendtime")
        @Expose
        public String spendtime;//관람소요시간

        /**
         * 축제/공연/행사
         */
        @SerializedName("agelimit")
        @Expose
        public String agelimit; //관람가능연령
        @SerializedName("bookingplace")
        @Expose
        public String bookingplace;//예매처
        @SerializedName("discountinfofestival")
        @Expose
        public String discountinfofestival; //할인정보
        @SerializedName("eventenddate")
        @Expose
        public Integer eventenddate; //행사종료일
        @SerializedName("eventhomepage")
        @Expose
        public String eventhomepage; //행사홈페이지
        @SerializedName("eventplace")
        @Expose
        public String eventplace; //행사장소
        @SerializedName("eventstartdate")
        @Expose
        public Integer eventstartdate; //행사시작일
        @SerializedName("placeinfo")
        @Expose
        public String placeinfo; //행사장위치안내
        @SerializedName("playtime")
        @Expose
        public String playtime; //공연시간
        @SerializedName("program")
        @Expose
        public String program; //행사프로그램
        @SerializedName("spendtimefestival")
        @Expose
        public String spendtimefestival; //관람소요시간
        @SerializedName("sponsor1")
        @Expose
        public String sponsor1; //주최자정보
        @SerializedName("sponsor1tel")
        @Expose
        public String sponsor1tel; //주최자연락처
        @SerializedName("sponsor2")
        @Expose
        public String sponsor2;//주관사정보
        @SerializedName("sponsor2tel")
        @Expose
        public String sponsor2tel; //주관사연락처
        @SerializedName("subevent")
        @Expose
        public String subevent; //부대행사
        @SerializedName("usetimefestival")
        @Expose
        public String usetimefestival; //이용요금


        /**
         * 음식점
         */
        @SerializedName("chkcreditcardfood")
        @Expose
        public String chkcreditcardfood; //신용카드가능여부
        @SerializedName("discountinfofood")
        @Expose
        public String discountinfofood; //할인정보
        @SerializedName("firstmenu")
        @Expose
        public String firstmenu; //대표메뉴
        @SerializedName("infocenterfood")
        @Expose
        public String infocenterfood; //문의및안내
        @SerializedName("kidsfacility")
        @Expose
        public Integer kidsfacility; //어린이놀이방
        @SerializedName("opendatefood")
        @Expose
        public String opendatefood;//개업일
        @SerializedName("opentimefood")
        @Expose
        public String opentimefood;//영업시간
        @SerializedName("packing")
        @Expose
        public String packing; //포장가능여부
        @SerializedName("parkingfood")
        @Expose
        public String parkingfood; //주차시설
        @SerializedName("reservationfood")
        @Expose
        public String reservationfood;//예약안내
        @SerializedName("restdatefood")
        @Expose
        public String restdatefood; //쉬는날
        @SerializedName("scalefood")
        @Expose
        public String scalefood; //규모
        @SerializedName("seat")
        @Expose
        public String seat; //좌석수
        @SerializedName("smoking")
        @Expose
        public String smoking; //금연/흡연여부
        @SerializedName("treatmenu")
        @Expose
        public String treatmenu; //취급메뉴

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

        public String getChkbabycarriage() {
            return chkbabycarriage;
        }

        public void setChkbabycarriage(String chkbabycarriage) {
            this.chkbabycarriage = chkbabycarriage;
        }

        public String getChkcreditcard() {
            return chkcreditcard;
        }

        public void setChkcreditcard(String chkcreditcard) {
            this.chkcreditcard = chkcreditcard;
        }

        public String getChkpet() {
            return chkpet;
        }

        public void setChkpet(String chkpet) {
            this.chkpet = chkpet;
        }

        public String getExpguide() {
            return expguide;
        }

        public void setExpguide(String expguide) {
            this.expguide = expguide;
        }

        public Integer getHeritage1() {
            return heritage1;
        }

        public void setHeritage1(Integer heritage1) {
            this.heritage1 = heritage1;
        }

        public Integer getHeritage2() {
            return heritage2;
        }

        public void setHeritage2(Integer heritage2) {
            this.heritage2 = heritage2;
        }

        public Integer getHeritage3() {
            return heritage3;
        }

        public void setHeritage3(Integer heritage3) {
            this.heritage3 = heritage3;
        }

        public String getInfocenter() {
            return infocenter;
        }

        public void setInfocenter(String infocenter) {
            this.infocenter = infocenter;
        }

        public String getParking() {
            return parking;
        }

        public void setParking(String parking) {
            this.parking = parking;
        }

        public String getRestdate() {
            return restdate;
        }

        public void setRestdate(String restdate) {
            this.restdate = restdate;
        }

        public String getUsetime() {
            return usetime;
        }

        public void setUsetime(String usetime) {
            this.usetime = usetime;
        }

        public String getAccomcountculture() {
            return accomcountculture;
        }

        public void setAccomcountculture(String accomcountculture) {
            this.accomcountculture = accomcountculture;
        }

        public String getChkbabycarriageculture() {
            return chkbabycarriageculture;
        }

        public void setChkbabycarriageculture(String chkbabycarriageculture) {
            this.chkbabycarriageculture = chkbabycarriageculture;
        }

        public String getChkcreditcardculture() {
            return chkcreditcardculture;
        }

        public void setChkcreditcardculture(String chkcreditcardculture) {
            this.chkcreditcardculture = chkcreditcardculture;
        }

        public String getChkpetculture() {
            return chkpetculture;
        }

        public void setChkpetculture(String chkpetculture) {
            this.chkpetculture = chkpetculture;
        }

        public String getInfocenterculture() {
            return infocenterculture;
        }

        public void setInfocenterculture(String infocenterculture) {
            this.infocenterculture = infocenterculture;
        }

        public String getParkingculture() {
            return parkingculture;
        }

        public void setParkingculture(String parkingculture) {
            this.parkingculture = parkingculture;
        }

        public String getParkingfee() {
            return parkingfee;
        }

        public void setParkingfee(String parkingfee) {
            this.parkingfee = parkingfee;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getUsefee() {
            return usefee;
        }

        public void setUsefee(String usefee) {
            this.usefee = usefee;
        }

        public String getUsetimeculture() {
            return usetimeculture;
        }

        public void setUsetimeculture(String usetimeculture) {
            this.usetimeculture = usetimeculture;
        }

        public String getAgelimit() {
            return agelimit;
        }

        public void setAgelimit(String agelimit) {
            this.agelimit = agelimit;
        }

        public String getBookingplace() {
            return bookingplace;
        }

        public void setBookingplace(String bookingplace) {
            this.bookingplace = bookingplace;
        }

        public String getDiscountinfofestival() {
            return discountinfofestival;
        }

        public void setDiscountinfofestival(String discountinfofestival) {
            this.discountinfofestival = discountinfofestival;
        }

        public Integer getEventenddate() {
            return eventenddate;
        }

        public void setEventenddate(Integer eventenddate) {
            this.eventenddate = eventenddate;
        }

        public String getEventplace() {
            return eventplace;
        }

        public void setEventplace(String eventplace) {
            this.eventplace = eventplace;
        }

        public Integer getEventstartdate() {
            return eventstartdate;
        }

        public void setEventstartdate(Integer eventstartdate) {
            this.eventstartdate = eventstartdate;
        }

        public String getPlaceinfo() {
            return placeinfo;
        }

        public void setPlaceinfo(String placeinfo) {
            this.placeinfo = placeinfo;
        }

        public String getPlaytime() {
            return playtime;
        }

        public void setPlaytime(String playtime) {
            this.playtime = playtime;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public String getSpendtimefestival() {
            return spendtimefestival;
        }

        public void setSpendtimefestival(String spendtimefestival) {
            this.spendtimefestival = spendtimefestival;
        }

        public String getSponsor1() {
            return sponsor1;
        }

        public void setSponsor1(String sponsor1) {
            this.sponsor1 = sponsor1;
        }

        public String getSponsor1tel() {
            return sponsor1tel;
        }

        public void setSponsor1tel(String sponsor1tel) {
            this.sponsor1tel = sponsor1tel;
        }

        public String getSponsor2() {
            return sponsor2;
        }

        public void setSponsor2(String sponsor2) {
            this.sponsor2 = sponsor2;
        }

        public String getSponsor2tel() {
            return sponsor2tel;
        }

        public void setSponsor2tel(String sponsor2tel) {
            this.sponsor2tel = sponsor2tel;
        }

        public String getSubevent() {
            return subevent;
        }

        public void setSubevent(String subevent) {
            this.subevent = subevent;
        }

        public String getUsetimefestival() {
            return usetimefestival;
        }

        public void setUsetimefestival(String usetimefestival) {
            this.usetimefestival = usetimefestival;
        }

        public String getChkcreditcardfood() {
            return chkcreditcardfood;
        }

        public void setChkcreditcardfood(String chkcreditcardfood) {
            this.chkcreditcardfood = chkcreditcardfood;
        }

        public String getDiscountinfofood() {
            return discountinfofood;
        }

        public void setDiscountinfofood(String discountinfofood) {
            this.discountinfofood = discountinfofood;
        }

        public String getFirstmenu() {
            return firstmenu;
        }

        public void setFirstmenu(String firstmenu) {
            this.firstmenu = firstmenu;
        }

        public String getInfocenterfood() {
            return infocenterfood;
        }

        public void setInfocenterfood(String infocenterfood) {
            this.infocenterfood = infocenterfood;
        }

        public Integer getKidsfacility() {
            return kidsfacility;
        }

        public void setKidsfacility(Integer kidsfacility) {
            this.kidsfacility = kidsfacility;
        }

        public String getOpendatefood() {
            return opendatefood;
        }

        public void setOpendatefood(String opendatefood) {
            this.opendatefood = opendatefood;
        }

        public String getOpentimefood() {
            return opentimefood;
        }

        public void setOpentimefood(String opentimefood) {
            this.opentimefood = opentimefood;
        }

        public String getPacking() {
            return packing;
        }

        public void setPacking(String packing) {
            this.packing = packing;
        }

        public String getReservationfood() {
            return reservationfood;
        }

        public void setReservationfood(String reservationfood) {
            this.reservationfood = reservationfood;
        }

        public String getRestdatefood() {
            return restdatefood;
        }

        public void setRestdatefood(String restdatefood) {
            this.restdatefood = restdatefood;
        }

        public String getSmoking() {
            return smoking;
        }

        public void setSmoking(String smoking) {
            this.smoking = smoking;
        }

        public String getTreatmenu() {
            return treatmenu;
        }

        public void setTreatmenu(String treatmenu) {
            this.treatmenu = treatmenu;
        }

        public String getOpendate() {
            return opendate;
        }

        public void setOpendate(String opendate) {
            this.opendate = opendate;
        }

        public String getUseseason() {
            return useseason;
        }

        public void setUseseason(String useseason) {
            this.useseason = useseason;
        }

        public String getDiscountinfo() {
            return discountinfo;
        }

        public void setDiscountinfo(String discountinfo) {
            this.discountinfo = discountinfo;
        }

        public String getRestdateculture() {
            return restdateculture;
        }

        public void setRestdateculture(String restdateculture) {
            this.restdateculture = restdateculture;
        }

        public String getSpendtime() {
            return spendtime;
        }

        public void setSpendtime(String spendtime) {
            this.spendtime = spendtime;
        }

        public String getEventhomepage() {
            return eventhomepage;
        }

        public void setEventhomepage(String eventhomepage) {
            this.eventhomepage = eventhomepage;
        }

        public String getParkingfood() {
            return parkingfood;
        }

        public void setParkingfood(String parkingfood) {
            this.parkingfood = parkingfood;
        }

        public String getScalefood() {
            return scalefood;
        }

        public void setScalefood(String scalefood) {
            this.scalefood = scalefood;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }


    }
}