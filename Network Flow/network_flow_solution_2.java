//package com.company;

import java.io.*;
import java.util.*;

public class GrandDinner {

    private static int[] parent;
    static int N,M;
    private static int fordFulkerson(int graph[][], int s, int t) {

        int u, v;
        int residualGraph[][] = new int[N + M + 2][N + M + 2]; // create residential graph

        for (u = 0; u < N + M + 2; u++)
            for (v = 0; v < N + M + 2; v++)
                residualGraph[u][v] = graph[u][v];


        int max_flow = 0;

        while (bfs(s, t, residualGraph)) {
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, residualGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                residualGraph[u][v] -= path_flow;
                residualGraph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }
        return max_flow;
    }

    public static boolean bfs(int source, int goal, int graph[][]) {
        boolean[] visited = new boolean[N + M + 2];
        boolean pathFound = false;
        Queue<Integer> queue = new LinkedList<Integer>();
        int destination, element;
        queue.add(source);
        parent[source] = -1;
        visited[source] = true;

        while (!queue.isEmpty() && !visited[goal]) {
            element = queue.poll();
            for( destination = 0 ; destination < N + M + 2 ; destination++) {
                if (graph[element][destination] > 0 && !visited[destination]) {
                    parent[destination] = element;
                    queue.add(destination);
                    visited[destination] = true;
                }
            }
        }

        if (visited[goal]) {
            pathFound = true;
        }
        return pathFound;
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
         M = in.nextInt();
         N = in.nextInt();
         int sum = 0;
        int graph[][] = new int[N + M + 2][N + M + 2];//0 for source and n+m-1 for sink
        parent = new int[N + M + 2];
        for (int i = 0; i < N + M+2 ; i++) {
            for (int j = 0; j < N + M+2; j++) {
                graph[i][j] = 0;
            }
        }
        for (int i = 1 ; i < M + 1; i++){
            for (int j = M + 1 ; j < M + N +1 ; j++){
                graph[i][j] = 1;
            }
        }
        for (int i = 0; i < M; i++) {
            int capacity = in.nextInt();
            sum += capacity;
            graph[0][i + 1] = capacity;
        }
        for (int i = M+1 ; i < M+N+1 ; i++){
            int capacity = in.nextInt();
            graph[i][M+N+1]= capacity;
        }
//        for (int i = 0; i < N + M + 2; i++) {
//            for (int j = 0; j < N + M + 2; j++) {
//                System.out.print(graph[i][j] + " ");
//            }
//            System.out.println();
//        }
        int maxFlow = fordFulkerson(graph,0,N+M+1);
//        System.out.println(maxFlow);
        if (maxFlow == sum)
            System.out.println("1");
        else System.out.println("0");
    }
}
