package ru.stqa.pft.sandbox;

/**
 * Created by kuanysh on 22.02.16.
 * Класс для представления точек на двумерной плоскости
 */
public class Point {

    /**
     * Координаты x, y
     */
    public double x;
    public double y;

    /**
     * Конструктор объекта Point
     *
     * @param x коодинат
     * @param y координат
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод который вычисляет расстояние между двумя точками
     *
     * @param x координат
     * @param y координат
     * @return расстояние между двумя точками
     */
    public double distance(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }
}