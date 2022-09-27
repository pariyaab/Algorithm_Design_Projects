//package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class addRoad2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(nodes);
        for (int i = 0; i < nodes ; i++) {
            adj.add(new ArrayList<>());
        }
        ArrayList<Integer> distanceS = new ArrayList<>(nodes);
        ArrayList<Integer> distanceT = new ArrayList<>(nodes);
        int edges = in.nextInt();
        int nodeS = in.nextInt();
        int nodeT = in.nextInt();
        for (int i = 0 ; i <edges; i++){
            addEdge(adj,in.nextInt()-1,in.nextInt()-1 );
        }

        for (int i = 0 ; i < nodes ; i++){
            distanceS.add(i,ShortestDistance(adj,nodeS-1,i,nodes));
        }
        for (int i = 0 ; i < nodes ; i++){
            distanceT.add(i ,ShortestDistance(adj,nodeT-1 ,i ,nodes));
        }
//        distanceS.set(nodeS-1,100);
//        distanceT.set(nodeT-1,100);
//        System.out.println("distance S: ");
//        for (int i = 0 ; i < nodes ; i++){
//            System.out.print(distanceS.get(i)+ " ");
//        }
//        System.out.println();
//        System.out.println("distance T: ");
//        for (int i = 0 ; i < nodes ; i++){
//            System.out.print(distanceT.get(i)+ " ");
//        }
//        System.out.println();
        int count = 0 ;
        int counter = 0;
        for (int i =0 ; i < nodes ;i++){
            if (adj.get(i).size() == 0){
                count += nodes - 1;
                distanceS.set(i,-100);
                distanceT.set(i,-100);
                counter++;
            }
        }
//        if (counter > 1 ) {
//            count = count - counter;
//        }
        count = count - counter*(counter-1)/2;
        for (int i =0 ; i< nodes ;i++){
                for (int j = i + 1; j < nodes; j++) {
                    if ((distanceS.get(i) + distanceT.get(j) + 1 >= distanceS.get(nodeT - 1)) &&
                            (distanceS.get(j) + distanceT.get(i) + 1 >= distanceS.get(nodeT - 1))) {
//                        System.out.println("2 from: " + (i + 1) + " to: " + (j + 1));
                        count++;
                    }
            }
        }
        System.out.println(count - edges);
    }
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }
    private static int ShortestDistance( ArrayList<ArrayList<Integer>> adj, int s, int dest, int v)
    {
        int pred[] = new int[v];
        int dist[] = new int[v];

        if (BFS(adj, s, dest, v, pred, dist) == false) {
            return 0;
        }
        return dist[dest];
    }

    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                               int dest, int v, int pred[], int dist[])
    {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}
