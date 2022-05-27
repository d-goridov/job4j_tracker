package ru.job4j.tracker;

public class SearchID implements UserAction {

    private final Output out;

    public SearchID(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by id";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== SearchID ===");
        int id = input.askInt("Enter id: ");
        Item item = store.findById(id);
        if (item != null) {
            out.println(item);
        } else {
            out.println("Заявка с введенным id: " + id + " не найдена.");
        }
        return true;
    }
}
