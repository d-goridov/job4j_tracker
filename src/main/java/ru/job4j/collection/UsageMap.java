package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("qwerty@gmail.com", "Ivanov Igor");
        map.put("case2352@gmail.com", "Sidorov Ivan");
        map.put("bambi9809@gmail.com", "Polykova Maria");
        for (String key: map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}
