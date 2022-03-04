package ru.job4j.inheritance;

public class Pacient {

    private int age;
    private String name;

    public Pacient(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
