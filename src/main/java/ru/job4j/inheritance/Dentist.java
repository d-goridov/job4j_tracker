package ru.job4j.inheritance;

public class Dentist extends Doctor {

    private int price;

    public Dentist(String name, String surname, String education, String birthday, int experience, int price) {
        super(name, surname, education, birthday, experience);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
