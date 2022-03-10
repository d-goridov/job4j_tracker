package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            System.out.println("На столе " + count + " спичек");
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.print(player + " введите число от 1 до 3: ");
            int matches = Integer.parseInt(input.nextLine());
            if (matches >= 1 && matches <= Math.min(3, count)) {
                count -= matches;
                turn = !turn;
            } else {
                System.out.println("Ошибка!!!");
                System.out.println("Вы выбрали неверное количество спичек");
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
