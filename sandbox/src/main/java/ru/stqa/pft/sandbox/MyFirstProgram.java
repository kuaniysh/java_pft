package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        Point point = new Point(5, 5);
        System.out.println(distance(point, point));
        System.out.println(point.distance(5, 5));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(p1.x * p1.x + p2.y * p2.y);
    }
}