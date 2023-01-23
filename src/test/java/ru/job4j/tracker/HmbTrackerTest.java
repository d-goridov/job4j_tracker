package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class HmbTrackerTest {

    @Before
    public void clearDB() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            for (Item item : session.createQuery("From Item", Item.class).list()) {
                session.delete(item);
            }
            session.getTransaction().commit();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (var tracker = new HmbTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenReplaceItemThenTrueResult() {
        try (var tracker = new HmbTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            boolean result = tracker.replace(item.getId(), new Item(item.getId(), "test2"));
            assertThat(result, is(true));
        }
    }

    @Test
    public void whenDeleteItemThenTrueResult() {
        try (var tracker = new HmbTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            boolean result = tracker.delete(item.getId());
            assertThat(tracker.findById(item.getId()), is(nullValue()));
            assertThat(result, is(true));
        }
    }

    @Test
    public void whenFindAllItems() {
        try (var tracker = new HmbTracker()) {
            Item item1 = new Item();
            item1.setName("item1");
            Item item2 = new Item();
            item1.setName("item2");
            Item item3 = new Item();
            item1.setName("item3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertThat(tracker.findAll().size(), is(3));
        }
    }

    @Test
    public void whenFindByNameItem() {
        try (var tracker = new HmbTracker()) {
            Item item1 = new Item();
            item1.setName("test1");
            Item item2 = new Item();
            item2.setName("test1");
            tracker.add(item1);
            tracker.add(item1);
            List<Item> items = tracker.findByName(item1.getName());
            assertThat(items.size(), is(2));
        }
    }

    @Test
    public void whenFindByIdItem() {
        try (var tracker = new HmbTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item item1 = tracker.findById(item.getId());
            Assert.assertEquals(item.getName(), item1.getName());
        }
    }
}