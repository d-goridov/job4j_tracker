package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void whenEditItemIsSuccessfully() {
        Output out = new StabOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        EditAction rep = new EditAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Заявка изменена успешно." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenEditItemIsError() {
        Output out = new StabOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        EditAction rep = new EditAction(out);

        Input input = mock(Input.class);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Ошибка замены заявки" + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced item"));
    }

    @Test
    public void whenRemoveItemIsSuccessfully() {
        Output out = new StabOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Removed Item"));
        DeleteAction del = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        del.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Заявка успешно удалена" + ln));
        assertThat(tracker.findAll(), is(List.of()));
    }

    @Test
    public void whenRemoveItemIsError() {
        Output out = new StabOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Removed Item"));
        DeleteAction del = new DeleteAction(out);

        Input input = mock(Input.class);

        del.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Ошибка удаления заявки" + ln));
        assertEquals(tracker.findAll().get(0), new Item("Removed Item"));
    }

    @Test
    public void whenFindItemByIdIsSuccessfully() {
        Output out = new StabOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Detected Item"));
        SearchID searchID = new SearchID(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        searchID.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== SearchID ===" + ln + item + ln));
        assertEquals(tracker.findAll().get(0), item);
    }

    @Test
    public void whenFindItemByNameIsSuccessfully() {
        Output out = new StabOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Detected Item"));
        SearchName searchName = new SearchName(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(item.getName());

        searchName.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== SearchName ===" + ln + item + ln));
        assertEquals(tracker.findAll().get(0), item);
    }
}