//package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ColorSquare3 {

    public static class Point {
        int x;
        int y;
        int color;

        public Point(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + (x + 1) +
                    ", y=" + (y + 1) +
                    ", color=" + color +
                    '}';
        }
    }

    public static class PointCmpX implements Comparator<Point> {

        @Override
        public int compare(Point a, Point b) {
            return (a.x < b.x) ? -1 : (a.x > b.x) ? 1 : 0;
        }
    }

    public static class PointCmpY implements Comparator<Point> {


        @Override
        public int compare(Point a, Point b) {
            return (a.y < b.y) ? -1 : (a.y > b.y) ? 1 : 0;
        }
    }

    public static double closestPair(ArrayList<Point> px, ArrayList<Point> py, int size) {

        if (size <= 3) {
            return bruteForce(px, size);
        } else {
            int mid = px.size() / 2;
            Point midPoint = px.get(mid);
            ArrayList<Point> pyl = new ArrayList<>();
            ArrayList<Point> pyr = new ArrayList<>();
            ArrayList<Point> pxl = new ArrayList<>();
            ArrayList<Point> pxr = new ArrayList<>();
            int li = 0, ri = 0;
            for (int i = 0 ; i < mid +1 ; i++) {
                pxl.add(px.get(i));

            }
            for (int i = mid+1 ; i < px.size() ; i++) {
                pxr.add(px.get(i));

            }
            for (int i = 0; i < py.size(); i++) {
                if (py.get(i).x <= midPoint.x) {
                    pyl.add(py.get(i));
                } else
                    pyr.add(py.get(i));
            }
            double dl = closestPair(pxl, pyl, pxl.size());
            double dr = closestPair(pxr, pyr, pxr.size());
            double d = Math.min(dl, dr);
            ArrayList<Point> strip = new ArrayList<>();
            for (int i = 0; i < py.size(); i++) {
                if (Math.abs(py.get(i).x - midPoint.x) < d) {
                    strip.add(py.get(i));
                }
            }
            double d1 = stripCloset(strip);

            return Math.min(d, d1);
        }
    }

    private static double stripCloset(ArrayList<Point> strip) {
        int size = strip.size();
        double d1 = Double.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < Math.min(i + 7, size); j++) {
                if (dist(strip.get(i), strip.get(j)) < d1 && (strip.get(i).color != strip.get(j).color)) {
                    d1 = dist(strip.get(i), strip.get(j));
                }
            }
        }
        return d1;
    }

    private static double bruteForce(ArrayList<Point> points, int n) {
        double min_value = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (dist(points.get(i), points.get(j)) < min_value && (points.get(i).color != points.get(j).color)) {
                    min_value = dist(points.get(i), points.get(j));
                }
            }
        }
        return min_value;
    }

    private static double dist(Point point, Point point1) {
        return Math.sqrt(Math.pow(point.x - point1.x, 2) +
                Math.pow(point1.y - point.y, 2));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            points.add(new Point(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }
        ArrayList<Point> points_copy = new ArrayList<>(points);
        points.sort(new PointCmpX());
        points_copy.sort(new PointCmpY());
        System.out.printf("%.3f", closestPair(points,points_copy,points.size()));
    }
}
