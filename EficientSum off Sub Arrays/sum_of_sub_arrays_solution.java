//package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class efficent {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        long size = in.nextLong();
        long size1 = size;
        ArrayList<Integer> numbers = new ArrayList<>();
        while (size1 > 0) {
            numbers.add(in.nextInt());
            size1--;
        }
        long result = 0;
        for (int i=0; i<size; i++){
            result += (numbers.get(i) * (i+1) * (size - i));
        }
        System.out.println(result);
    }
}
