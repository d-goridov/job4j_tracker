package ru.job4j.poly;

public class Bus implements Transport {

    @Override
    public void drive() {
        System.out.println("Автобус отправляется про маршруту");
    }

    @Override
    public void passengers(int count) {
        System.out.println("В автобусе можно перевезти " + count + " пассажиров");
    }

    @Override
    public double refuel(double fuel) {
        double price = 53.5;
        return price * fuel;
    }
}
