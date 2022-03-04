package ru.job4j.inheritance;

public class Builder extends Engineer {

    private int numberAuto;

    public Builder(String name, String surname, String education, String birthday, int id, int numberAuto) {
        super(name, surname, education, birthday, id);
        this.numberAuto = numberAuto;
    }

    public int getNumberAuto() {
        return numberAuto;
    }
}
