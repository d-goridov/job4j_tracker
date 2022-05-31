package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemTest {

    @Test
    public void whenSortedByAsc() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Pavel"));
        items.add(new Item("Dmitriy"));
        items.add(new Item("Darya"));
        items.add(new Item("Maria"));
        items.sort(new ItemAscByName());
        List<Item> expected = List.of(new Item("Darya"), new Item("Dmitriy"),
                new Item("Maria"), new Item("Pavel"));
        assertThat(expected, is(items));

    }

    @Test
    public void whenSortedByDesc() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Darya"));
        items.add(new Item("Pavel"));
        items.add(new Item("Dmitriy"));
        items.add(new Item("Maria"));
        items.sort(new ItemDescByName());
        List<Item> expected = List.of(new Item("Pavel"), new Item("Maria"),
                new Item("Dmitriy"), new Item("Darya"));
        assertThat(expected, is(items));

    }
}