package ru.job4j.tracker;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private Timestamp created = Timestamp.valueOf(LocalDateTime.now());

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, Timestamp created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }
}