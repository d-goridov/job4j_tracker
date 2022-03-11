package ru.job4j.poly;

public class Autobus implements Vehicle {

    @Override
    public void movie() {
        System.out.print(" передвигается по дороге");
    }

    @Override
    public void motorType() {
        System.out.println(", имеет дизельный двигатель");
    }
}
