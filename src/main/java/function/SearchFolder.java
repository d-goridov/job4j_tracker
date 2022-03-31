package function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFolder {
    public static List<Folder> filterSize(List<Folder> list) {
        return filter(list, (folder) -> folder.getSize() > 100);
    }

    public static List<Folder> filterName(List<Folder> list) {
        return filter(list, (folder) -> folder.getName().contains("bug"));
    }

    public static List<Folder> filter(List<Folder> list, Predicate<Folder> pred) {
        List<Folder> rsl = new ArrayList<>();
        for (Folder f: list) {
            if (pred.test(f)) {
                rsl.add(f);
            }
        }
        return rsl;
    }
}
