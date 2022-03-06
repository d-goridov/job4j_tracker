package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Head First", 720);
        Book book2 = new Book("Java8", 700);
        Book book3 = new Book("Clean Code", 250);
        Book book4 = new Book("Witcher", 620);
        Book[] collection = new Book[4];
        collection[0] = book1;
        collection[1] = book2;
        collection[2] = book3;
        collection[3] = book4;
        System.out.println("Show collection");
        for (Book book: collection) {
            System.out.println(book.getName() + " " + book.getCount());
        }
        System.out.println("Show changed collection");
        Book book5 = collection[0];
        collection[0] = collection[3];
        collection[3] = book5;
        for (Book book: collection) {
            System.out.println(book.getName() + " " + book.getCount());
        }
        System.out.println("Show book with name Clean Code");
        for (Book book:collection) {
            if ("Clean Code".equals(book.getName())) {
                System.out.println(book.getName() + " " + book.getCount());
            }
        }
    }
}
