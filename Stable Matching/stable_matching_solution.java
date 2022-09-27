//package com.company;

import java.util.*;

public class Main {
    static int[][] rank ;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TreeMap<Integer, Integer> hmap = new TreeMap<Integer, Integer>();
        int numberOfCouples = input.nextInt();
        for (int i = 0; i < numberOfCouples; i++) {

            int n = input.nextInt();
            rank = new int[n][n];

            int[][] womanPref = new int[n][n];
            int[][] manPref = new int[n][n];


            for (int b = 0;b < n; b++) {
                for (int a = 0; a < n; a++) {
                    manPref[b][a] = input.nextInt()-1;
                }
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    womanPref[j][k] = input.nextInt()-1;
                }
            }



            rank = initilizeRank(womanPref,n);
            hmap = stableMarriage(manPref,n,i);
            System.out.println("#"+(i+1));
            for (Map.Entry<Integer, Integer> entry : hmap.entrySet()) {
                System.out.print(entry.getValue()+1 + " ");
            }
            System.out.println("\n");

        }
    }

    private static int[][] initilizeRank(int[][] womanPref,int n) {
        int[][] womanRank = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0; j<n;j++){
                womanRank[i][womanPref[i][j]] = j;
            }
        }
//        for (int j = 0; j < n; j++) {
//            for (int k = 0; k < n; k++) {
//                System.out.print(womanRank[j][k]);
//            }
//            System.out.println();
//        }
        return womanRank;
    }

    private static TreeMap<Integer, Integer> stableMarriage(int[][] manPref, int n, int number) {
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        LinkedList<Integer> freeMan = new LinkedList<>();
        ArrayList<Integer> engWoman = new ArrayList<>();
        int[] nextWoman = new int[n];//az akhrin nafar k pishnhd dde
        for(int i =0;i <n;i++)
        {
            freeMan.add(i);
            engWoman.add(i,-1);
        }
        while (!freeMan.isEmpty()) {
            int man = freeMan.getFirst();
            if (engWoman.get(manPref[man][nextWoman[man]]) == -1) {
                hmap.put(manPref[man][nextWoman[man]],man);
                freeMan.removeFirst();
                engWoman.set(manPref[man][nextWoman[man]] , 1);
                nextWoman[man] ++;
            }
            else {
                if (rank[manPref[man][nextWoman[man]]][man] < rank[manPref[man][nextWoman[man]]][hmap.get(manPref[man][nextWoman[man]])]) {
                    freeMan.removeFirst();
                    freeMan.add(hmap.get(manPref[man][nextWoman[man]]));
                    hmap.replace(manPref[man][nextWoman[man]], man);
                    engWoman.set(manPref[man][nextWoman[man]] , 1);
                    nextWoman[man]++;

                }
                else {
                    nextWoman[man]++;
                }
            }

        }


        TreeMap<Integer, Integer> sorted = new TreeMap<>();
        sorted.putAll(hmap);

        return sorted;
    }
}

