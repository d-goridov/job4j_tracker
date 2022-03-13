package ru.job4j.strategy;

public class Paint {
    public void draw(Shape shape) {
        System.out.print(shape.draw());
    }

    public static void main(String[] args) {
        Paint desk = new Paint();
        desk.draw(new Triangle());
        desk.draw(new Square());
    }
}
