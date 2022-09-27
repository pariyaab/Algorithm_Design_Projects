//package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class networkFlow {

    private static int[] parent;

    private static int fordFulkerson(int graph[][], int s, int t) {

        int u, v;
        int residualGraph[][] = new int[500][500]; // create residential graph

        for (u = 0; u < 500; u++)
            for (v = 0; v < 500; v++)
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
        boolean[] visited = new boolean[500];
        boolean pathFound = false;
        Queue<Integer> queue = new LinkedList<Integer>();
        int destination, element;
        queue.add(source);
        parent[source] = -1;
        visited[source] = true;

        while (!queue.isEmpty() && !visited[goal]) {
            element = queue.poll();
            for( destination = 0 ; destination < 500 ; destination++) {
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
        int source = in.nextInt() - 1;
        int sink = in.nextInt() - 1;
        int citySize = in.nextInt();
        parent = new int[500];
        int graph[][] = new int[500][500];

        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                graph[i][j] = 0;
            }
        }
        int numberOfVertices = 0;
        for (int i = 0; i < citySize; i++) {
            int row = in.nextInt();
            int col = in.nextInt();
            int capacity =  in.nextInt();
            graph[row - 1][col - 1] += capacity;
            graph[col - 1][row - 1] += capacity;
            numberOfVertices = Math.max(Math.max(numberOfVertices,col),row);
        }

//        for (int i = 0; i < citySize; i++) {
//            for (int j = 0 ; j < citySize; j++) {
//                System.out.print(graph[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(fordFulkerson(graph, source, sink));
    }
}
