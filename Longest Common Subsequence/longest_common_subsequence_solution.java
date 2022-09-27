//package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaximizeSubArray {

    private static long  maximizeSumEvenIndex(ArrayList<Long> arrayList) {
        long sumSubArray = 0;
        long evenDifference = 0;
        long oddDifference = 0;
        for (int i = 0 ; i < arrayList.size() -1 ; i++){
            if (i % 2 == 0){
                evenDifference += (arrayList.get(i + 1) - arrayList.get(i));
                sumSubArray = Math.max(evenDifference, sumSubArray);
                if (evenDifference < 0) evenDifference = 0;
            }
            else {
                oddDifference += arrayList.get(i) - arrayList.get(i + 1);
                sumSubArray = Math.max(sumSubArray, oddDifference);
                if (oddDifference < 0)
                    oddDifference = 0;
            }
        }
        return sumSubArray;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        long sum = 0;
        ArrayList<Long> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            long temp = in.nextLong();
            if (i % 2 == 0){
                sum += temp;
            }
            arrayList.add(temp);
        }
        System.out.println((maximizeSumEvenIndex(arrayList) + sum));
    }
}
