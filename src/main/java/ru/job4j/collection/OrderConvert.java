package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderConvert {
    public static Map<String, Order> process(List<Order> orders) {
        Map<String, Order> map = new HashMap<>();
        for (Order element: orders) {
            map.put(element.getNumber(), element);
        }
        return map;
    }
}
