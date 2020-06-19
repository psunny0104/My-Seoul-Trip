package com.myseoultrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.myseoultrip.model.SubwayCoord;
import com.myseoultrip.model.SubwayTime;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static ArrayList<SubwayCoord> subwayCoords;
    public static int m = 5000;
    public static int numStation = 705;
    public static Integer[] STATE = new Integer[numStation];
    //public static ArrayList<SubwayTime> subwayTimes;
    public static List<List<String>> subwayTimes = new ArrayList<List<String>>();
    public static int[][] adjData = new int[numStation][numStation];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(),1000);
    }

    class splashHandler implements Runnable{

        @Override
        public void run() {
            //TODO
            StationInit();
            try {
                SubwayInit();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        private void StationInit() {
            InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.subway_info_final));
            BufferedReader reader = new BufferedReader(is);
            CSVReader read = new CSVReader(reader);
            String[] record = null;

            //String stationCd, String stationName, String lineNum, String frCode, Double wgsY, Double wgsX
            int idx = 1;
            subwayCoords = new ArrayList<>();
            try{
                while ((record = read.readNext()) != null){
                    if(idx == 1) {
                        record[0] = "0";
                        idx++;
                    }
                    SubwayCoord subwayCoord = new SubwayCoord(record[0],record[1],record[2],record[3],record[4],Double.parseDouble(record[7]),Double.parseDouble(record[8]));
                    subwayCoords.add(subwayCoord);
                    //Log.i("myTag",subwayCoords.get(idx++).getStationName());
                };
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
        }

        public void SubwayInit() throws IOException {
            for(int i = 0; i< numStation; i++) {
                STATE[i] = i;
            }


            for(int i = 0; i<numStation; i++) {
                for(int j = 0; j<numStation; j++) {
                    if(i == j) {
                        adjData[i][j] = 0;
                        adjData[j][i] = 0;
                    }
                    else {
                        adjData[i][j] = m;
                        adjData[j][i] = m;
                    }
                }
            }

            InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.subway_time_final),"UTF-8");
            BufferedReader reader = new BufferedReader(is);
            CSVReader read = new CSVReader(reader);
            String[] record = null;

            List<List<String>> ret = new ArrayList<List<String>>();
            //Charset.forName("UTF-8");

            int idx = 1;
            while ((record = read.readNext()) != null){
                //CSV 1행을 저장하는 리스트
                if(idx == 1) {
                    record[0] = "0";
                    idx++;
                }
                //System.out.println(tmpList);

                adjData[Integer.parseInt(record[0])][Integer.parseInt(record[1])] = Integer.parseInt(record[2]);
                adjData[Integer.parseInt(record[1])][Integer.parseInt(record[0])] = Integer.parseInt(record[2]);
                subwayTimes.add(Arrays.asList(record));
            }
        }
    }
}
