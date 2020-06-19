package com.myseoultrip.model.place.tour;

import java.util.HashMap;

public class CategoryCodeModel {
    //지역 검색을 위한 HashMap
    private HashMap<String, String> catTwoMap = new HashMap<>();
    private HashMap<String, String> catThreeMap = new HashMap<>();

    private String[][] catTwoKeySet;

    private final String[] catTwoKey12 = {"A0101", "A0102", "A0201", "A0202", "A0203", "A0204", "A0205"};
    private final String[] catTwoValue12 = {"자연관광지", "관광자원", "역사관광지", "휴양관광지", "체험관광지", "산업관광지", "건축/조형물"};

    private final String[] catTwoKey14 = {"A0206"};
    private final String[] catTwoValue14 = {"문화시설"};

    private final String[] catTwoKey15 = {"A0207", "A0208"};
    private final String[] catTwoValue15 = {"축제", "공연/행사"};

    private final String[] catTwoKey39 = {"A0502"};
    private final String[] catTwoValue39= {"음식점"};

    private final String[] catThreeKey12 = {"A01010100", "A01010200", "A01010300", "A01010400", "A01010500", "A01010600", "A01010700", "A01010800", "A01010900", "A01011000",
                                            "A01011000", "A01011200", "A01011300", "A01011400", "A01011500", "A01011600", "A01011700", "A01011800", "A01011900","A01020100",
                                            "A01020200", "A02010100", "A02010200", "A02010300", "A02010400", "A02010500", "A02010600", "A02010700", "A02010800","A02010900",
                                            "A02011000", "A02020100", "A02020200", "A02020300", "A02020400", "A02020500", "A02020600", "A02020700", "A02020800","A02030100",
                                            "A02030200", "A02030300", "A02030400", "A02030500", "A02030600", "A02040100", "A02040200", "A02040300", "A02040400","A02040500",
                                            "A02040600", "A02040700", "A02040800", "A02040900", "A02041000", "A02050100", "A02050200", "A02050300", "A02050400","A02050500",
                                            "A02050600"};
    private final String[] catThreeValue12 = {"국립공원", "도립공원", "군립공원", "산", "자연생태관광지", "자연휴양림", "수목원", "폭포", "계곡", "약수터",
                                                "해안절경", "해수욕장", "섬", "항구/포구", "어촌", "등대", "호수", "강", "동굴", "희귀동.식물",
                                                "기암괴석", "고궁", "성", "문", "고택", "생가", "민속마을", "유적지/사적지", "사찰", "종교성지",
                                                "안보관광", "유원지", "관광단지", "온천/욕장/스파", "이색찜질방", "헬스투어", "테마공원", "공원", "유람선/잠수함관광", "농.산.어촌 체험",
                                                "전통체험", "산사체험", "이색체험", "관광농원", "이색거리", "제철소", "조선소", "공단", "발전소", "광산",
                                                "식음료", "화학/금속", "기타", "전자/반도체", "자동차", "다리/대교", "기념탑/기념비/전망대", "분수", "동상", "터널",
                                                "유명건물"};

    private final String[] catThreeKey14 = {"A02060100", "A02060200", "A02060300", "A02060400", "A02060500", "A02060600", "A02060700", "A02060800", "A02060900", "A02061000", "A02061100", "A02061200", "A02061300", "A02061400"};
    private final String[] catThreeValue14 = {"박물관", "기념관", "전시관", "컨벤션센터", "미술관/화랑", "공연장", "문화원", "외국문화원", "도서관", "대형서점", "문화전수시설", "영화관", "어학당", "학교"};

    private final String[] catThreeKey15 = {"A02070100", "A02070200", "A02080100", "A02080200", "A02080300", "A02080400", "A02080500", "A02080600", "A02080700", "A02080800", "A02080900", "A02081000", "A02081100", "A02081200", "A02081300"};
    private final String[] catThreeValue15 = {"문화관광축제", "일반축제", "전통공연", "연극", "뮤지컬", "오페라", "전시회", "박람회", "컨벤션", "무용", "클래식음악회", "대중콘서트", "영화", "스포츠경기", "기타행사"};

    private final String[] catThreeKey39 = {"A05020100", "A05020200", "A05020300", "A05020400", "A05020500", "A05020600", "A05020700", "A05020800", "A05020900", "A05021000"};
    private final String[] catThreeValue39 = {"한식", "서양식", "일식", "중식", "아시아식", "패밀리레스토랑", "이색음식점", "채식전문점", "바/까페", "클럽"};


    public void init(){
        for (int i = 0; i<catTwoKey12.length; i++) {
            catTwoMap.put(catTwoKey12[i],catTwoValue12[i]);
        }
        for(int i = 0; i<catTwoKey14.length; i++){
            catTwoMap.put(catTwoKey14[i], catTwoValue14[i]);
        }
        for(int i = 0; i<catTwoKey15.length; i++){
            catTwoMap.put(catTwoKey15[i], catTwoValue15[i]);
        }
        for(int i = 0; i<catTwoKey39.length; i++){
            catTwoMap.put(catTwoKey39[i], catTwoValue39[i]);
        }
        for (int i = 0; i<catThreeKey12.length; i++) {
            catThreeMap.put(catThreeKey12[i],catThreeValue12[i]);
        }
        for(int i = 0; i<catThreeKey14.length; i++){
            catThreeMap.put(catThreeKey14[i], catThreeValue14[i]);
        }
        for(int i = 0; i<catThreeKey15.length; i++){
            catThreeMap.put(catThreeKey15[i], catThreeValue15[i]);
        }
        for(int i = 0; i<catThreeKey39.length; i++){
            catThreeMap.put(catThreeKey39[i], catThreeValue39[i]);
        }
    }

    public HashMap<String, String> getCatTwoMap() {
        return catTwoMap;
    }

    public void setCatTwoMap(HashMap<String, String> catTwoMap) {
        this.catTwoMap = catTwoMap;
    }

    public HashMap<String, String> getCatThreeMap() {
        return catThreeMap;
    }

    public void setCatThreeMap(HashMap<String, String> catThreeMap) {
        this.catThreeMap = catThreeMap;
    }
}
