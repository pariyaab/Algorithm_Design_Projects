//package com.company;

import java.util.*;

public class Cracken3 {

    public static class Node {
        char ch;
        int freq;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
        Node() {
        }
        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    '}';
        }
    }

    public static class NodeComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.freq == o2.freq){
                return o2.ch - o1.ch;}
            else if (o1.freq > o2.freq){
                return -1;
            }
            else
                return 1;
        }
    }
    public static String huffmanCodeConcat(int n){
        String result = "";
        for (int i=0 ; i<n ; i++){
            result += "1";
        }
        return result;
    }
    private static void setHuffmanCode(Map<Character, Integer> freq, int characterCount, String text){

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new NodeComparator());
        Map<Character, String> huffmanCode = new TreeMap<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            priorityQueue.offer(new Cracken3.Node(entry.getKey(), entry.getValue()));
        }
        huffmanCode.put(priorityQueue.poll().ch,huffmanCodeConcat(characterCount-1));
        int count = characterCount-1;
        while (priorityQueue.size() >= 1){
            huffmanCode.put(priorityQueue.poll().ch, (huffmanCodeConcat(count-1) + "0"));
            count--;
        }
        String strArray[] = text.split("");
        String temp = "";
        String result = "";
        count = 0;
        boolean isFind = false;
        while (strArray.length > count){
            temp += strArray[count];
            for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
                if (temp.equals(entry.getValue())){
                    result += entry.getKey();
                    count++;
                    isFind = true;
                    temp = "";
                    break;
                }
                else {
                    isFind = false;
                }
            }
            if (!isFind){
                count++;
            }
        }
        System.out.println(result);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int size = in.nextInt();
        for (int i = 0; i < size; i++) {

            Map<Character, Integer> freq = new TreeMap<>(Collections.reverseOrder());
            int characterCount = in.nextInt();
            int test = characterCount;
            while (characterCount > 0) {

                char character = in.next().charAt(0);
                int frequency = in.nextInt();
                freq.put(character, frequency);
                characterCount--;
            }
            String text = in.next();
            setHuffmanCode(freq,test,text);
            System.out.println();
        }
    }
}
