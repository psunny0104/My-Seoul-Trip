package com.myseoultrip.service.findroute;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.myseoultrip.MakingCourseActivity.makingItems;
import static com.myseoultrip.SplashActivity.STATE;
import static com.myseoultrip.SplashActivity.adjData;
import static com.myseoultrip.SplashActivity.subwayCoords;

public class DijkStraCourse {
    public static int m = 5000;
    public static int numStation = 705;


    public static int initDijkTwo(int s, int e, int flag, int order) throws Exception{
        int k = 0;
        int min;
        // 출발점이나 도착점의 입력값을 받을때도 알파벳을 숫자로 대체한 노드 값을 받음
        k = 0;
        int [] v = new int[numStation];
        int [] distance = new int[numStation];
        int [] via = new int[numStation];

        for(int j=0; j<numStation; j++){
            v[j] = 0;			//상태를 초기화함
            distance[j] = m;	//거리를 최대값으로 설정
        }

        distance[s] = 0; //출발점을 0으로 초기화

        for(int i=0; i<numStation; i++){
            min = m;
            for(int j=0; j<numStation;j++){
                if( v[j] == 0 && distance[j] < min){ //연결 되어있는 인접한 노드들 중에 최단거리를 찾음
                    k=j;
                    min = distance[j];
                }
            }
            v[k]=1;					//최단거리를 설정
            if(min==m) break;		//최단거리가 아닐 경우

            for(int j=0; j<numStation; j++){
                if(distance[j]> distance[k]+adjData[k][j]){ 	// s에서 k를 지나 j까지 가는 거리
                    distance[j] = distance[k] + adjData[k][j]; // 그 거리를 최단거리로 설정하고 저장함
                    via[j] = k;
                }
            }
        }
//        System.out.println(subwayCoords.get(STATE[s]).getEngName()+"에서 "+subwayCoords.get(STATE[e]).getEngName()+"까지 소요된 시간은 "+distance[e]+"분 입니다.");				//출발할 노드의 입력값을 받음
        int path[] = new int[numStation];
        int path_cnt =0;
        k=e;

        while(true){
            path[path_cnt++] = k;
            if(k== s)break;
            k = via[k];
        }
//        System.out.print("경유역 :: ");

        StringBuilder result = new StringBuilder();
        //result.append("Subway station order: ");
        for(int i=path_cnt -1; i>=0; i--){
            if(i==0){
              //  System.out.println(subwayCoords.get(STATE[path[i]]).getEngName());
                result.append(subwayCoords.get(STATE[path[i]]).getEngName());
                break;
            }
            //System.out.print(subwayCoords.get(STATE[path[i]]).getEngName()+" -> ");
            result.append(subwayCoords.get(STATE[path[i]]).getEngName()+" -> ");
        }
        if(flag == 1){
            makingItems.get(order).setTime("From "+subwayCoords.get(STATE[s]).getEngName()+" to "+subwayCoords.get(STATE[e]).getEngName()+" : "+(distance[e]*2)+"minutes. (estimated)");
            makingItems.get(order).setDetailCourse(result.toString());
        }
      //  System.out.println();

        return distance[e];
    }
}
