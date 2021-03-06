package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StabOutput();
        Input in = new StabInput(new String[]{"0", "Item name", "1"});
        Store tracker = new MemTracker();
        List<UserAction> actions = List.of(new CreateAction(out), new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StabOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Test1"));
        String replacedName = "New Test1 name";
        Input in = new StabInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = List.of(new EditAction(out), new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                              + "0. Edit item" + ln
                              + "1. Exit Program" + ln
                              + "=== Edit item ===" + ln
                              + "Заявка изменена успешно." + ln
                              + "Menu." + ln
                              + "0. Edit item" + ln
                              + "1. Exit Program" + ln
                              + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StabOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StabInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = List.of(new DeleteAction(out), new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StabOutput();
        Input in = new StabInput(
                new String[] {"0"}
        );
        Store tracker = new MemTracker();
        List<UserAction> actions = List.of(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                              + "0. Exit Program" + System.lineSeparator()
                              + "=== Exit Program ===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenShowAllTestOutputIsSuccessfully() {
        Output out = new StabOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Show Item"));
        Input in = new StabInput(new String[]{"0", "1"});
        List<UserAction> actions = List.of(new ShowAll(out), new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ===" + ln
                        + item + ln
                        + "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenFindByNameActionTestOutputIsSuccessfully() {
        Output out = new StabOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("For search item"));
        Input in = new StabInput(new String[]{"0", item.getName(), "1"});
        List<UserAction> actions = List.of(new SearchName(out), new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== SearchName ===" + ln
                        + item + ln
                        + "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenFindByIDActionTestOutputIsSuccessfully() {
        Output out = new StabOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("For search id item"));
        Input in = new StabInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = List.of(new SearchID(out), new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== SearchID ===" + ln
                        + item + ln
                        + "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StabOutput();
        Input in = new StabInput(new String[]{"5", "0"});
        Store tracker = new MemTracker();
        List<UserAction> actions = List.of(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                        "Menu." + ln
                                + "0. Exit Program" + ln
                                + "Wrong input, you can select: 0 .. 0" + ln
                                + "Menu." + ln
                                + "0. Exit Program" + ln
                                + "=== Exit Program ===" + ln
                )
        );
    }

}