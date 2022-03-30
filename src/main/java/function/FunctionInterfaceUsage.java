package function;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;

public class FunctionInterfaceUsage {
    public static void main(String[] args) {
        Supplier<String> sup = () -> "New String For Interface";
        System.out.println(sup.get());

        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept(sup.get());

        BiConsumer<String, String> consumer1 = (s, s1) -> System.out.println(s + s1);
        consumer1.accept(sup.get(), " and Second String");

        List<String> list = List.of("one", "three", "two", "one", "four", "three");
        Supplier<Set<String>> sup1 = () -> new HashSet<String>(list);
        Set<String> strings = sup1.get();
        for (String s: strings) {
            System.out.println(s);
        }

        List<String> list1 = List.of("one", "two", "three", "one", "two", "three");
        Supplier<Set<String>> sup2 = () -> new HashSet<String>(list1);
        BiConsumer<Integer, String> consumer2 = (s2, s3) -> System.out.println(s2 + s3);
        Set<String> strings1 = sup2.get();
        int i = 1;
        for (String s : strings1) {
            consumer2.accept(i++, " is " + s);
        }

        Predicate<String> pred = (s) -> s.isEmpty();
        System.out.println(pred.test(""));
        System.out.println(pred.test("12345"));

        BiPredicate<String, Integer> cond = (s, j) -> s.contains(j.toString());
        System.out.println("Строка содержит подстроку: " + cond.test("Name123", 123));
        System.out.println("Строка содержит подстроку: " + cond.test("Name", 123));

        Function<String, Character> func = s -> s.charAt(2);
        System.out.println("Третий символ в строке: " + func.apply("first"));
        System.out.println("Третий символ в строке: " + func.apply("second"));

        UnaryOperator<StringBuilder> builder = b -> b.reverse();
        System.out.println("Строка после реверса: " + builder.apply(new StringBuilder("String for test")));
        System.out.println("Строка после реверса: " + builder.apply(new StringBuilder("tset rof gnirtS")));

        BinaryOperator<StringBuilder> builder1 = (b1, b2) -> b1.append(" ").append(b2);
        System.out.println(
                "Строка после объединения: " + builder1.apply(
                        new StringBuilder("First string"),
                        new StringBuilder("Second string")
                )
        );
    }
}
