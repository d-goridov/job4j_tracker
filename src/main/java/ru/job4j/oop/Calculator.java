package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int y) {
        return x * y;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int divide(int y) {
        return y / x;
    }

    public int sumAllOperation(int y) {
        return sum(y) + multiply(y) + minus(y) + divide(y);
    }

    public static void main(String[] args) {
        int result1 = sum(10);
        System.out.println(result1);
        Calculator calculator = new Calculator();
        int result2 = calculator.multiply(7);
        System.out.println(result2);
        int result3 = minus(2);
        System.out.println(result3);
        int result4 = calculator.divide(5);
        System.out.println(result4);
        int result5 = calculator.sumAllOperation(3);
        System.out.println(result5);
    }
}
