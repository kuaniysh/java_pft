package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kuanysh on 28.02.16.
 */
public class PointTests {

    private double x = 5;
    private double y = 5;
    private double resultDistance = 7.0710678118654755;

    /**
     * Тест для вычисления расстояния между точками
     */
    @Test
    public void testPoint() {
        Point p = new Point();
        assertThat(p.distance(x, y), equalTo(resultDistance));
    }
}
