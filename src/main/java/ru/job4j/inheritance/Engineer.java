package ru.job4j.inheritance;

public class Engineer extends Profession {

    private int id;

    public Engineer(String name, String surname, String education, String birthday, int id) {
        super(name, surname, education, birthday);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean work(Task task) {
        return false;
    }
}
