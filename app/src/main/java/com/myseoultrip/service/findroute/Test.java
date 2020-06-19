package com.myseoultrip.service.findroute;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import static com.myseoultrip.service.findroute.FirstStepPermutation.permList;
import static com.myseoultrip.service.findroute.FirstStepPermutation.permutation;
import static com.myseoultrip.service.findroute.SecondStepDijkstra.initDijk;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //서울역,  명동, 동대문역사문화공원, 을지로 3가 -> ok
        //int[] arrStation = {6, 8, 5, 3};

        //삼각지, 녹사평 -> ok
        //int[] arrStation = {12,13};

        //시청, 충무로 -> ok
        //int[] arrStation = {1, 9};

        /**
         * 서울역, 버티고개, 청구 -> ok
         * 서울역->청구->버티고개 혹은 버티고개->청구->서울역
         * 노선도상의 거리와 실제 시간이 다른 경우 검증 ok
         * 노선도: 서울역 - 회현 - 명동 - 충무로 - 동대입구 - 약수 - 버티고개 - 약수 - 청구: 11
         * 답:    서울역 - 회현 - 명동 - 충무로 - 동대문역사문화공원 - 청구 - 약수 - 버티고개: 9
         */
        //int[] arrStation = {6, 16, 10};

        int[] arrStation = {4201, 6, 10};
        //int[] arrStation = {6, 5, 16};
        /* TODO
         *  경로 추적 추가 필요
         */


        int sizeString = arrStation.length;
        boolean[] visited = new boolean[sizeString];

        //firstStep
        permutation(arrStation, 0, sizeString, sizeString);

        //secondStep
        ArrayList<Integer> permTime = new ArrayList<Integer>();
        int itemTime = 0;

        //thirdStep: 최소 시간 찾기, 중복되는 순열에 대한 가지치기 필요
        for (int i = 0; i < permList.size(); i++) {
            //System.out.println(permList.get(i).size());
            for (int j = 0; j < permList.get(i).size() - 1; j++) {
                int tmp = initDijk(permList.get(i).get(j), permList.get(i).get(j + 1));
                System.out.println(i + 1 + "번째 순열의 " + j + 1 + "번째 구간의 코스트: " + tmp);
                itemTime += tmp;
            }
            System.out.println(i + "번째 순열의 총 cost: " + itemTime);
            permTime.add(itemTime);
            itemTime = 0;
        }

        int min = Collections.min(permTime);
        System.out.println("최소 시간: " + min);
    }
}
