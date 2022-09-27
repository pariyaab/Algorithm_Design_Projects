//package com.company;

import java.util.*;

public class ConnectCountry {


    static ArrayList<Node> nodes;

    public static class Node {
        int src;
        int des;
        int type;
        int parent;

        public Node(int src, int des, int type) {
            this.src = src;
            this.des = des;
            this.type = type;
        }

        public Node(int parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "src=" + (src + 1) +
                    ", des=" + (des + 1) +
                    ", type=" + type +
                    ", parent=" + (parent + 1) +
                    '}';
        }
    }

    public static void addEdges(ArrayList<ArrayList<Node>> adj, int i, int j, int type) {
        adj.get(i).add(new Node(i, j, type));
        adj.get(j).add(new Node(j, i, type));
    }

    public static void initDisjointSets(int totalNodes) {
        nodes = new ArrayList<>(totalNodes);
        for (int i = 0; i < totalNodes; i++) {
            nodes.add(new Node(i));
        }
    }

    public static void detectForest(ArrayList<ArrayList<Node>> adj) {
        Queue<ArrayList<Node>> queue = new LinkedList<>();
        ArrayList<Node> arrayList = new ArrayList<>();
        for (int i = 0; i < adj.size(); i++) {
            queue.offer(adj.get(i));
            while (!queue.isEmpty()) {
                ArrayList<Node> temp = queue.poll();
                for (int j = 0; j < temp.size(); j++) {
                    if (temp.get(j).type == 3) {
                        queue.offer(adj.get(temp.get(j).des));
                        nodes.get(temp.get(j).des).parent = i;
                        adj.set(temp.get(j).des, arrayList);
                    }
                }
            }
        }
    }

    public static boolean detectSpanningMen(ArrayList<ArrayList<Node>> adj, int vertex) {
        Queue<ArrayList<Node>> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertex];
        queue.offer(adj.get(0));
        while (!queue.isEmpty()) {
            ArrayList<Node> temp = queue.poll();
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j).type == 3 || temp.get(j).type == 1) {
                    visited[temp.get(j).src] = true;
                    if (!visited[temp.get(j).des]) {
                        queue.offer(adj.get(temp.get(j).des));
                        visited[temp.get(j).des] = true;
                    }
                }
            }
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }

    public static boolean detectedSpanningWomen(ArrayList<ArrayList<Node>> adj, int vertex) {
        Queue<ArrayList<Node>> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertex];
        queue.offer(adj.get(0));
        while (!queue.isEmpty()) {
            ArrayList<Node> temp = queue.poll();
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j).type == 3 || temp.get(j).type == 2) {
                    visited[temp.get(j).src] = true;
                    if (!visited[temp.get(j).des]) {
                        queue.offer(adj.get(temp.get(j).des));
                        visited[temp.get(j).des] = true;
                    }
                }
            }
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }

    public static void countEnoughEdges(int edges) {
        int forestCount = 0;
        int forestEdges = 0;
        int singleNodes = 0;
        for (int i = 0; i < nodes.size(); i++) {
            int temp = 0;
            int parent = nodes.get(i).parent;
            if (parent != -1) {
                for (int j = i + 1; j < nodes.size(); j++) {
                    if (parent == nodes.get(j).parent) {
                        temp++;
                        nodes.get(j).parent = -1;
                    }
                }
                if (temp > 0) {
                    forestEdges += temp;
                    forestCount++;
                } else singleNodes++;
            }
        }
//        System.out.println("forest count: "+forestCount);
//        System.out.println("single nodes: "+singleNodes);
//        System.out.println("forest edges: "+forestEdges);
        System.out.println(edges - (forestEdges + (forestCount - 1) * 2 + (singleNodes * 2)));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int vertex = in.nextInt();
        int edges = in.nextInt();
        ArrayList<ArrayList<Node>> adj = new ArrayList<>(vertex);
        for (int i = 0; i < vertex; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            addEdges(adj, in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }

//        for (int i = 0; i < vertex; i++) {
//            System.out.println(adj.get(i));
//        }
        initDisjointSets(vertex);

//        System.out.println("spanning tree for men : " + detectSpanningMen(adj, vertex));
//        System.out.println("spanning tree for women : " + detectedSpanningWomen(adj, vertex));

        if (detectSpanningMen(adj, vertex) && detectedSpanningWomen(adj, vertex)) {
            detectForest(adj);
            countEnoughEdges(edges);
        } else System.out.println(-1);


//        for (int i = 0; i < vertex; i++) {
//            System.out.println(nodes.get(i));
//        }
    }

}
