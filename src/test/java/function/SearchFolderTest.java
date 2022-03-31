package function;

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchFolderTest {

    @Test
    public void whenFilterSize() {
        List<Folder> list = List.of(
                new Folder("fix", 110),
                new Folder("bug", 75),
                new Folder("bug", 90)
        );
        List<Folder> expected = List.of(new Folder("fix", 110));
        Predicate<Folder> pred = (folder) -> folder.getSize() > 100;
        List<Folder> rsl = SearchFolder.filter(list, pred);
        assertThat(expected, is(rsl));
    }

    @Test
    public void whenFilterName() {
        List<Folder> list = List.of(
                new Folder("fix", 110),
                new Folder("bug", 75),
                new Folder("bug", 90)
        );
        List<Folder> expected = List.of(
                new Folder("bug", 75),
                new Folder("bug", 90)
        );
        Predicate<Folder> pred = f -> f.getName().contains("bug");
        List<Folder> rsl = SearchFolder.filter(list, pred);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenFilterNameNoResult() {
        List<Folder> list = List.of(
                new Folder("fix", 110),
                new Folder("bug", 75),
                new Folder("bug", 90)
        );
        List<Folder> expected = List.of();
        Predicate<Folder> pred = f -> f.getName().contains("bud");
        List<Folder> rsl = SearchFolder.filter(list, pred);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenFilterSizeNoResult() {
        List<Folder> list = List.of(
                new Folder("fix", 110),
                new Folder("bug", 75),
                new Folder("bug", 90)
        );
        List<Folder> expected = List.of();
        Predicate<Folder> pred = (folder) -> folder.getSize() < 70;
        List<Folder> rsl = SearchFolder.filter(list, pred);
        assertThat(expected, is(rsl));
    }
}