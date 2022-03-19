package ru.job4j.fabric;

import java.util.Scanner;

public class Canvas {

   private ShapeOperator shapeOperator;

    public Canvas(ShapeOperator shapeOperator) {
        this.shapeOperator = shapeOperator;
    }

    public void demonstrate() {
        this.shapeOperator.showInfo();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShapeOperator shapeOperator;
        System.out.print("Какую фигуру вы хотите построить: ");
        if ("прямоугольник".equals(scanner.nextLine())) {
            shapeOperator = new RectangleOperator();
        } else {
            shapeOperator = new TriangleOperator();
        }

        Canvas canvas = new Canvas(shapeOperator);
        canvas.demonstrate();

    }
}
