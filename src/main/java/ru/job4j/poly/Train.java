package ru.job4j.poly;

public class Train implements Vehicle {

    @Override
    public void movie() {
        System.out.print(" передвигается по рельсам");
    }

    @Override
    public void motorType() {
        System.out.println(", имеет электрический двигатель");
    }
}
