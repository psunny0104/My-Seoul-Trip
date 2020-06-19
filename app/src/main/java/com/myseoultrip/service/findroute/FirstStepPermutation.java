package com.myseoultrip.service.findroute;

import java.util.ArrayList;

public class FirstStepPermutation {
    public static ArrayList<ArrayList<Integer>> permList = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<Integer> itemList = null;

    public static void permutation(int[] arr, int depth, int n, int r) {//배열의 첫 번째 원소부터 swap
        if (depth == r) {
            print(arr, r);
            return;
        }

        for (int i = depth; i < n; i++) { //depth를 기준 인덱스로 하여 depth보다 인덱스가 작은 값들은 고정
            swap(arr, depth, i);          //index가 크면 다시 swap
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }

    public static void swap(int[] arr, int depth, int i) { //순열의 값을 직접 바꾸기 위한 swap
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    public static void print(int[] arr, int r) {
        itemList = new ArrayList<Integer>();
        for (int i = 0; i < r; i++){
            System.out.print(arr[i] + " ");
            itemList.add(arr[i]);
        }
        permList.add(itemList);
        System.out.println();
    }
}
