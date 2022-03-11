package ru.job4j.poly;

public class PolyUsage {
    public static void main(String[] args) {
        Plane plane = new Plane();
        Plane plane1 = new Plane();
        Autobus bus = new Autobus();
        Autobus bus1 = new Autobus();
        Train train = new Train();
        Train train1 = new Train();
        Vehicle vehicle1 = plane;
        Vehicle vehicle2 = plane1;
        Vehicle vehicle3 = bus;
        Vehicle vehicle4 = bus1;
        Vehicle vehicle5 = train;
        Vehicle vehicle6 = train1;
        Vehicle[] array = {plane, plane1, bus, bus1, train, train1};
        for (Vehicle vehicle: array) {
            System.out.print(vehicle.getClass().getSimpleName());
            vehicle.movie();
            vehicle.motorType();
        }
    }
}
