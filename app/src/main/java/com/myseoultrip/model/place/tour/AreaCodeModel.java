package com.myseoultrip.model.place.tour;

import java.util.HashMap;

public class AreaCodeModel {
    //지역 검색을 위한 HashMap
    private HashMap<String, Integer> areaMap = new HashMap<>();
    private HashMap<String, int[]> sigunguMap = new HashMap<>();

    //HashMap 초기화를 위한 데이터
    private final String[] areaKeys = new String[]{"서울", "인천", "대전", "대구", "광주", "부산", "울산", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "경상북도", "경상남도", "전라북도", "전라남도", "제주도"};
    private final int[] areaCodes = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 31, 32, 33, 34, 35, 36, 37, 38, 39};
    private final String[][] sigunguKeys = {{"가평", "고양", "과천", "광명", "광주", "구리", "군포", "김포", "남양주", "동두천", "부천", "성남", "수원", "시흥", "안산", "안성", "안양", "양주", "양평", "여주", "연천", "오산", "용인", "의왕", "의정부", "이천", "파주", "평택", "포천", "하남", "화성"},
        {"강릉", "고성", "동해", "삼척", "속초", "양구", "양양", "영월", "원주", "인제", "정선", "철원", "춘천", "태백", "평창", "홍천", "화천", "횡성"},
            {"괴산", "단양", "보은", "영동", "옥천", "음성", "제천", "진천", "청원", "청주", "충주", "증평"},
            {"공주", "금산", "논산", "당진", "보령", "부여", "서산", "서천", "아산", "예산", "천안", "청양", "태안", "홍성", "계룡"},
            {"경산", "경주", "고령", "구미", "군위", "김천", "문경", "봉화", "상주", "성주", "안동", "영덕", "영양", "영주", "영천", "예천", "울릉", "울진", "의성", "청도", "청송", "칠곡", "포항"},
            {"거제", "거창", "고성", "김해", "남해", "마산", "밀양", "사천", "산청", "양산", "의령", "진주", "진해", "창년", "창원", "통영", "하동", "함안", "함양", "합천"},
            {"고창", "군산", "김제", "남원", "무주", "부안", "순창", "완주", "익산", "임실", "장수", "전주", "정읍", "진안"},
            {"강진", "고흥", "곡성", "광양", "구례", "나주", "담양", "목포", "무안", "보성", "순천", "신안", "여수", "영광", "영암", "완도", "장성", "장흥", "진도", "함평", "해남", "화순"},
            {"남제주", "북제주", "서귀포", "제주"}};

    private final String[] sigunguKeysSeoul = {"강남", "강동", "강북", "강서", "관악", "광진", "구로", "금천", "노원", "도봉", "동대문", "동작", "마포", "서대문", "서초", "성동", "성북", "송파", "양천", "영등포", "용산", "은평", "종로", "중구", "중랑구"};
    //HashMap 초기화
    public void init(){
        for(int i = 0; i<areaKeys.length; i++){
            areaMap.put(areaKeys[i], areaCodes[i]);
        }

        for(int i =0; i<sigunguKeysSeoul.length; i++){
            sigunguMap.put(sigunguKeysSeoul[i],new int[]{1,i+1});
        }
        int idx = 31;//31~39
        for(int i = 0; i < 9; i++){
            for(int j = 0; j<sigunguKeys[i].length; j++){
                sigunguMap.put(sigunguKeys[i][j], new int[]{idx, j+1});
            }
            idx++;
        }
    }

    public HashMap<String, Integer> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(HashMap<String, Integer> areaMap) {
        this.areaMap = areaMap;
    }

    public HashMap<String, int[]> getSigunguMap() {
        return sigunguMap;
    }

    public void setSigunguMap(HashMap<String, int[]> sigunguMap) {
        this.sigunguMap = sigunguMap;
    }

    public String[] getAreaKeys() {
        return areaKeys;
    }

    public int[] getAreaCodes() {
        return areaCodes;
    }

    public String[][] getSigunguKeys() {
        return sigunguKeys;
    }
}
