package ru.job4j.inheritance;

public class Surgeon extends Doctor {

    private int numberCabinet;

    public Surgeon(String name, String surname, String education, String birthday, int experience, int numberCabinet) {
        super(name, surname, education, birthday, experience);
        this.numberCabinet = numberCabinet;
    }

    public int getNumberCabinet() {
        return numberCabinet;
    }
}
