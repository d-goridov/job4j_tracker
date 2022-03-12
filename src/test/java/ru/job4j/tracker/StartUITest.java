package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void whenAddItem() {
        String[] answers = {"Task1"};
        Input input = new StabInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Task1");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenEditItem() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        tracker.add(item);
        String[] answers = {String.valueOf(item.getId()), "edited item"};
        StartUI.editItem(new StabInput(answers), tracker);
        Item edited = tracker.findById(item.getId());
        assertThat(edited.getName(), is("edited item"));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("deleted item"));
        String[] answers = {String.valueOf(item.getId()), "deleted item"};
        StartUI.deleteItem(new StabInput(answers), tracker);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }
}