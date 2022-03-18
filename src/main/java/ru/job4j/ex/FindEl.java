package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int result = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                result = i;
                break;
            }
        }
        if (result == -1) {
            throw new ElementNotFoundException("Элемент не найден");
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            int index = indexOf(new String[]{"Ivan", "Petr", "Oleg", "Maria"}, "Olga");
            System.out.println(index);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

    }
}
