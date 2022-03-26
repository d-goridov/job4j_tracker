package ru.job4j.tracker;

import java.util.Comparator;

public class ItemDescByName implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        int result = 0;
        if (o1.getName().compareTo(o2.getName()) > 0) {
            result = -1;
        }
        if (o1.getName().compareTo(o2.getName()) < 0) {
            result = 1;
        }
        return result;
    }
}
