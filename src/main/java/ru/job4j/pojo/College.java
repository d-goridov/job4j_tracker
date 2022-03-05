package ru.job4j.pojo;

import java.time.LocalDate;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Dmitriy Goridov");
        student.setGroup(504);
        student.setDate(LocalDate.of(2020, 2, 13));
        System.out.println(student.getName());
        System.out.println(student.getGroup());
        System.out.println(student.getDate());
    }
}
