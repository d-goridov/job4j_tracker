package ru.job4j.oop;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TriangleTest {

    @Test
    public void when00and40and04Then8() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        assertThat(result, closeTo(8, 0.001));
    }

    @Test
    public void when10and30and50ThenMinus1() {
        Point a = new Point(1, 0);
        Point b = new Point(3, 0);
        Point c = new Point(5, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        assertThat(result, closeTo(-1, 0.001));
    }
}