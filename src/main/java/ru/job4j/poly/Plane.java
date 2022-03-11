package ru.job4j.poly;

public class Plane implements Vehicle {
    @Override
    public void movie() {
        System.out.print(" летает по воздуху");
    }

    @Override
    public void motorType() {
        System.out.println(", имеет реактивный двигатель");
    }
}
