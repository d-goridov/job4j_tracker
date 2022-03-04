package ru.job4j.inheritance;

public class Doctor extends Profession {

    private int experience;

    public Doctor(String name, String surname, String education, String birthday, int experience) {
        super(name, surname, education, birthday);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public Diagnosis heal(Pacient pacient) {
        return new Diagnosis();
    }
}
