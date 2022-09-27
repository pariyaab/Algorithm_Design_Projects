//package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
// pariya abadee _ binabaji
public class Question2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        double temp =0.0;
        int temp2 = 0;
        ArrayList<Double> carLength = new ArrayList<>(size);
        ArrayList<Double> angerCoefficients = new ArrayList<>(size);
        ArrayList<Integer> output = new ArrayList<>(size);
        ArrayList<Double> Ratio = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            carLength.add(in.nextDouble());
            output.add(i+1);
        }
        for (int i = 0; i < size; i++) {
            angerCoefficients.add(in.nextDouble());
        }
        for (int i = 0; i < size; i++) {
            Ratio.add(carLength.get(i) / angerCoefficients.get(i));
        }
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (Ratio.get(i) > Ratio.get(j)) {
                    temp = Ratio.get(i);
                    Ratio.set(i, Ratio.get(j));
                    Ratio.set(j, temp);
                    temp2 = output.get(i);
                    output.set(i, output.get(j));
                    output.set(j, temp2);

                }
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.print(output.get(i) + " ");;
        }
    }
}
