package ru.job4j.collection;

import org.junit.Test;
import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class JobTest {

    @Test
    public void whenComparatorAscByName() {
        Comparator<Job> comparator = new JobAscByName();
        int result = comparator.compare(
                new Job("Building", 3),
                new Job("Draw", 2)
        );
        assertThat(result, lessThan(0));
    }

    @Test
    public void whenComparatorAscByPriority() {
        Comparator<Job> comparator = new JobAscByPriority();
        int result = comparator.compare(
                new Job("Building", 3),
                new Job("Draw", 2)
        );
        assertThat(result, greaterThan(0));
    }

    @Test
    public void whenComparatorDescByPriority() {
        Comparator<Job> comparator = new JobDescByPriority();
        int result = comparator.compare(
                new Job("Draw", 2),
                new Job("Building", 3)
        );
        assertThat(result, greaterThan(0));
    }

    @Test
    public void whenComparatorDescByName() {
        Comparator<Job> comparator = new JobDescByName();
        int result = comparator.compare(
                new Job("Building", 3),
                new Job("Draw", 2)
        );
        assertThat(result, greaterThan(0));
    }

    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> comparator = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = comparator.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByEqualsPriorityAndName() {
        Comparator<Job> comparator = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = comparator.compare(
                new Job("Impl task", 1),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorEqualsNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bug", 5),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, greaterThan(0));
    }
}