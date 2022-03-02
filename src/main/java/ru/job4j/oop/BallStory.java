package ru.job4j.oop;

public class BallStory {
    public static void main(String[] args) {
        Ball colobok = new Ball();
        Hare valera = new Hare();
        valera.tryEat(colobok);
        Wolf boris = new Wolf();
        boris.tryEat(colobok);
        Fox alisa = new Fox();
        alisa.tryEat(colobok);
    }
}
