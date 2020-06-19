package com.myseoultrip.model.place.tour;

import java.util.HashMap;

public class ComInfoMap {
    private HashMap<String, String> comInfoMap = new HashMap<>();

    private final String[] comInfoKey = {  //관광지
            "chkbabycarriage", "chkcreditcard", "chkpet", "expguide", "heritage1",
            "heritage2", "heritage3", "infocenter", "opendate", "parking",
            "restdate", "useseason", "usetime",
            //문화시설
            "accomcountculture", "chkbabycarriageculture", "chkcreditcardculture", "chkpetculture", "discountinfo",
            "infocenterculture", "parkingculture", "parkingfee", "restdateculture", "scale",
            "usefee", "usetimeculture", "spendtime",
            //축제/공연/행사
            "agelimit", "bookingplace", "discountinfofestival", "eventenddate", "eventhomepage",
            "eventplace", "eventstartdate", "placeinfo", "playtime", "program",
            "spendtimefestival", "sponsor1", "sponsor1tel", "sponsor2", "sponsor2tel",
            "subevent", "usetimefestival",
            //음식점
            "chkcreditcardfood", "discountinfofood", "firstmenu", "infocenterfood", "kidsfacility",
            "opendatefood", "opentimefood", "packing", "parkingfood", "reservationfood",
            "restdatefood", "scalefood", "seat", "smoking", "treatmenu"};

    private final String[] comInfoValue = { //관광지
            " 유모차 대여 여부", "신용카드 가능 여부", "애완동물 가능 여부", "체험 안내", "세계문화유산 유무",
            "세가자연유산 유무", "세계기록유산 유무", "문의 및 안내", "개장일", "주차시설",
            "쉬는 날", "이용 시기", "이용 시간",
            //문화시설
            "수용 인원", "유모차 대여 여부", "신용카드 가능 여부", "애완동물 가능 여부", "할인정보",
            "문의 및 안내", "주차시설", "주차요금", "쉬는 날", "규모",
            "이용 요금", "이용 시간", "관람 소요 시간",
            //축제/공연/행사
            "관람 가능 연령", "예매처", "할인정보", "행사 종료일", "행사 홈페이지",
            "행사 장소", "행사 시작일", "행사장 위치 안내", "공연시간", "행사 프로그램",
            "관람 소요 시간", "주최자 정보", "주최자 연락처", "주관사 정보", " 주관사 연락처",
            "부대행사", "이용 요금",
            //음식점
            "신용카드 가능 여부", "할인 정보", "대표 메뉴", "문의 및 안내", "어린이 놀이방",
            "개업일", "영업시간", "포장가능 여부", "주차 시설", "예약 안내",
            "쉬는 날", "규모", "좌석 수", "금연/흡연 여부", "취급메뉴"};



    public void init()
    {
        for(int i = 0; i<comInfoKey.length; i++){
            comInfoMap.put(comInfoKey[i], comInfoValue[i]);
        }
    }

    public HashMap<String, String> getComInfoMap() {
        return comInfoMap;
    }

    public void setComInfoMap(HashMap<String, String> comInfoMap) {
        this.comInfoMap = comInfoMap;
    }

    public String[] getComInfoKey() {
        return comInfoKey;
    }

    public String[] getComInfoValue() {
        return comInfoValue;
    }
}