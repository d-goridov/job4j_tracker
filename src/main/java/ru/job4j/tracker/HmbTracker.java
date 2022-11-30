package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HmbTracker implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("update Item SET name = :fName where id = :fId")
                   .setParameter("fName", item.getName())
                   .setParameter("fId", id).executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete Item where id = :fId")
                    .setParameter("fId", id).executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("FROM Item").getResultList();
            session.getTransaction().commit();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> rsl;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("SELECT it FROM Item AS it WHERE name = :fName")
                         .setParameter("fName", key)
                         .getResultList();
            session.getTransaction().commit();
        }
        return rsl;
    }

    @Override
    public Item findById(int id) {
        Item item;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session.createQuery("from Item as it where it.id = :fId", Item.class)
                                       .setParameter("fId", id);
            item = query.uniqueResult();
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
