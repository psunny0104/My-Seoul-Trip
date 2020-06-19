package com.myseoultrip.service.findroute;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.myseoultrip.SplashActivity.subwayCoords;

public class SecondStepDijkstra {
    static int[][] ad;
    static int[] dist;
    static boolean[] visit;
    static int nE, nV;
    static final int inf = 1000000;
    public static int[] subwayIdx = new int[705];

    public void init(){
        for(int i = 0;i <705; i++){
            subwayIdx[i] = i;
        }
    }

    public static Map<Integer,Integer> codeToIdx = new HashMap<Integer, Integer>();

    public static int ssp(int start, int end){
        dist[start] = 0;    // 최초 시작 값 distance 초기화
        String s = "";
        for( int j = 0; j < nV; j++) // dist 값을 한번 초기화 했으므로 n-1번만 진행
        {
            int min = inf+1;    // dist 최소값 찾기 위한 임시 값 초기화
            int index = -1;
            for(int k = 1; k <= nV; k++){
                if(visit[k] == false && min > dist[k]){
                    min = dist[k];
                    index = k;
                }
            }
            visit[index] = true;
            s += index + " ";  // 경로를 체크하는 방법

            for(int l = 1; l <= nV; l++){
                if(ad[index][l] != 0 && dist[l] > dist[index] + ad[index][l]){ //인접 행렬을 검사하여 최적의 값을 찾아
                    dist[l] = dist[index] + ad[index][l];
                }

            }
        }

//        for(int i = 1; i <= nV; i++){
//            System.out.print(dist[i]+" ");
//        }
//        System.out.println();
//        System.out.println(s);
//        System.out.println(start+"에서 "+end+"까지 걸리는 시간: "+dist[end]);
        return dist[end];
    }

    public static int initDijk(int start, int end) throws UnsupportedEncodingException {
        nV = 705;
        nE = 21;

        ad = new int[nV+1][nV+1];
        dist = new int[nV+1];
        visit = new boolean[nV+1];

        for(int i = 1; i <= nV; i++){
            dist[i] = inf;   //임의의 무한한 값으로 거리값 초기화
        }

        int tmp = 0;
        int tmp2 = 0;
        byte[] bytes = new byte[0];
        for(int i = 0; i<subwayIdx.length-1; i++) {
            if(subwayCoords.get(i).getLineNum().equals(subwayCoords.get(i+1).getLineNum())){
                //Log.i("myTag","tmp: "+subwayCoords.get(i).getStationCd()+" / tmp2: "+(subwayCoords.get(i+1).getStationCd()));

                tmp = subwayIdx[i];
                tmp2 = subwayIdx[i+1];
                ad[tmp][tmp2] = ad[tmp2][tmp] = 2;
            }
        }


        //TODO 수정 필요
        for(int i = 0; i<subwayIdx.length-1; i++){
            codeToIdx.put(subwayIdx[i],i);
        }

        int time = ssp(start, end);
        return time;
    }
}
