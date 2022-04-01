package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> names = (Person p) -> p.getName().equals(key);
        Predicate<Person> phones = (Person p) -> p.getPhone().equals(key);
        Predicate<Person> surnames = (Person p) -> p.getSurname().equals(key);
        Predicate<Person> address = (Person p) -> p.getAddress().equals(key);
        Predicate<Person> combine = names.or(phones).or(surnames.or(address));
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
