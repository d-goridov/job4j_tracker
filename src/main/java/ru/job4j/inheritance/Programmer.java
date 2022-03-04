package ru.job4j.inheritance;

public class Programmer extends Engineer {

    private String department;

    public Programmer(String name, String surname, String education, String birthday, int id, String department) {
        super(name, surname, education, birthday, id);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
