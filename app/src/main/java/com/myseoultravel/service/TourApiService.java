package com.myseoultravel.service;

import com.myseoultravel.model.place.tour.AreaListModel;
import com.myseoultravel.model.place.tour.ComInfoModel;
import com.myseoultravel.model.place.tour.IntroInfoModel;
import com.myseoultravel.model.place.tour.LocalListModelItems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TourApiService {

    String TOUR_BASE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/EngService/";
    String SERVICE_KEY = "1boNxPdiShW6HBhSZFTlvQJ9kCLvQmO0W91%2FGGzcbErYT7kmjaNo1kHZ3YyKLd%2FYUkOLXLXHMNEkfHFEfopGVQ%3D%3D";
    //여분
    //String SERVICE_KEY = "VLwUMfI4BldngseHRsQUCK5KDb466d%2BQNjPsnzuAlcEL7sjhjxHrgDLAzIMkRo%2Ba7H6JxuGan2AxVZ%2FKitkpUA%3D%3D";

    String MOBILE_OS = "ETC";
    String MOBILE_App = "myGenieTour";
    String MY_TYPE = "json";
    /**
     * 1. 위치기반 관광정보 조회 (리스트)
     * @param ServiceKey (필수)인증키 (encoded=true 해야 %->%25(utf-8)로 안 바뀜)
     * @param MobileOs (필수)모바일 운영체제 종류: ETC
     * @param MobileApp (필수)앱 이름: GenieTour
     * @param _type 수신 타입: XML, JSON
     * @param numOfRows 한 페이지의 결과 개수: 5
     * @param pageNo 페이지번호: 1
     * @param arrange 정렬구분: P(인기순), S(거리순)
     * @param contentTypeId 관광타입 ID
     * @param mapX (필수)x좌표
     * @param mapY (필수)y좌표
     * @param radius (필수)거리반경
     * @return
     */

    @GET("locationBasedList")
    Call<LocalListModelItems> getLocationList(@Query(value = "ServiceKey", encoded = true) String ServiceKey,
                                              @Query("MobileOS") String MobileOs,
                                              @Query("MobileApp") String MobileApp,
                                              @Query("_type") String _type,
                                              @Query("numOfRows") int numOfRows,
                                              @Query("pageNo") int pageNo,
                                              @Query("arrange") String arrange,
                                              @Query("contentTypeId") int contentTypeId,
                                              @Query("mapX") double mapX,
                                              @Query("mapY") double mapY,
                                              @Query("radius") int radius

    );

    /**
     * 2. 지역기반 관광정보 조회 (리스트)
     * @param ServiceKey (필수)인증키 (encoded=true 해야 %->%25(utf-8)로 안 바뀜)
     * @param MobileOs (필수)모바일 운영체제 종류: ETC
     * @param MobileApp (필수)앱 이름: GenieTour
     * @param _type 수신 타입: XML, JSON
     * @param numOfRows 한 페이지의 결과 개수: 5
     * @param pageNo 페이지번호: 1
     * @param arrange 정렬구분: P(인기순), S(거리순)
     * @param contentTypeId 관광타입 ID
     * @param areaCode 지역코드
     * @param sigunguCode 시군구코드
     * @return
     */

    @GET("areaBasedList")
    Call<AreaListModel> getAreaList(@Query(value = "ServiceKey", encoded = true) String ServiceKey,
                                    @Query("MobileOS") String MobileOs,
                                    @Query("MobileApp") String MobileApp,
                                    @Query("_type") String _type,
                                    @Query("numOfRows") int numOfRows,
                                    @Query("pageNo") int pageNo,
                                    @Query("arrange") String arrange,
                                    @Query("contentTypeId") int contentTypeId,
                                    @Query("areaCode") int areaCode,
                                    @Query("sigunguCode") String sigunguCode
    );

    /**
     * 3. 행사/공연/축제 날짜기반 조회 (리스트)
     * @param ServiceKey (필수)인증키 (encoded=true 해야 %->%25(utf-8)로 안 바뀜)
     * @param MobileOs (필수)모바일 운영체제 종류: ETC
     * @param MobileApp (필수)앱 이름: GenieTour
     * @param _type 수신 타입: XML, JSON
     * @param numOfRows 한 페이지의 결과 개수: 5
     * @param pageNo 페이지번호: 1
     * @param arrange 정렬구분: P(인기순), Q(수정일순)
     * @param eventStartDate (필수)행사시작일: YYYYMMDD
     * @param eventEndDate 행사종료일: YYYYMMDD
     * @param areaCode 지역코드
     * @param sigunguCode 시군구코드
     * @return
     */

    /**
     * 3-1. 상세정보1_공통정보 조회
     * @param ServiceKey (필수)인증키 (encoded=true 해야 %->%25(utf-8)로 안 바뀜)
     * @param MobileOs (필수)모바일 운영체제 종류: ETC
     * @param MobileApp (필수)앱 이름: GenieTour
     * @param _type 수신 타입: XML, JSON
    //     * @param contentTypeId 관광타입 ID
     * @param contentId (필수)콘텐츠 ID
     * @param defaultYN 기본정보 (제목, 등록일, 수정일, 전화번호 등)
     * @param firstImageYN 대표이미지 (원본, 썸네일이미지)
     * @param areaCodeYN 지역코드 (지역코드, 시군구코드)
     * @param catCodeYN 서비스분류코드 (대, 중, 소분류코드)
     * @param addrInfoYN 주소 (주소, 상세주소)
     * @param mapInfoYN 좌표 (X, Y)
     * @param overviewYN 개요 (콘텐츠개요)
     * @return
     */

    @GET("detailCommon")
    Call<ComInfoModel> getCommonInfo(@Query(value = "ServiceKey", encoded = true) String ServiceKey,
                                     @Query("MobileOS") String MobileOs,
                                     @Query("MobileApp") String MobileApp,
                                     @Query("_type") String _type,
                                     //@Query("contentTypeId") int contentTypeId,
                                     @Query("contentId") int contentId,
                                     @Query("defaultYN") String defaultYN,
                                     @Query("firstImageYN") String firstImageYN,
                                     @Query("areacodeYN") String areaCodeYN,
                                     @Query("catcodeYN") String catCodeYN,
                                     @Query("addrinfoYN") String addrInfoYN,
                                     @Query("mapinfoYN") String mapInfoYN,
                                     @Query("overviewYN") String overviewYN
    );


    /**
     * 3-2. 상세정보2_소개정보 조회
     * @param ServiceKey (필수)인증키 (encoded=true 해야 %->%25(utf-8)로 안 바뀜)
     * @param MobileOs (필수)모바일 운영체제 종류: ETC
     * @param MobileApp (필수)앱 이름: GenieTour
     * @param _type 수신 타입: XML, JSON
     * @param contentTypeId 관광타입 ID
     * @param contentId (필수)콘텐츠 ID
     * @param introYN 소개정보
     * @return
     */

    @GET("detailIntro")
    Call<IntroInfoModel> getIntroInfo(@Query(value = "ServiceKey", encoded = true) String ServiceKey,
                                      @Query("MobileOS") String MobileOs,
                                      @Query("MobileApp") String MobileApp,
                                      @Query("_type") String _type,
                                      @Query("contentId") int contentId,
                                      @Query("contentTypeId") int contentTypeId,
                                      @Query("introYN") String introYN
    );
}
