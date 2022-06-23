package ru.job4j.inheritance.pizza;

public class PizzaExtraCheese extends Pizza {
    public String name() {
        return super.name() + " + extra cheese";
    }
}
