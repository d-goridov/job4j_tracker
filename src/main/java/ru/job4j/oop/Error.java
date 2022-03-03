package ru.job4j.oop;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void showInfo() {
        System.out.print("Активность: " + active + ", ");
        System.out.print("Статус: " + status + ", ");
        System.out.println("Сообщение: " + message + ".");
    }

    public static void main(String[] args) {
        Error error1 = new Error();
        Error error2 = new Error(true, 12, "Сбой системы");
        Error error3 = new Error(false, 35, "Не хватает памяти");
        Error error4 = new Error(true, 82, "Требуется перезагрузка системы");
        error1.showInfo();
        error2.showInfo();
        error3.showInfo();
        error4.showInfo();
    }
}
