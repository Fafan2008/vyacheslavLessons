package lesson1;


import java.util.*;

public class Lesson1 {
    public static void main(String[] args) {
        int i1 = 0;
        int i2 = 1;
        int ii1 = 5;
        Integer ii2 = null;

        ii1 = ii1 + 5;

        String s = "abc";


        //System.out.println(ii1 == ii2);

        Person p = new Person("A", "B");
        System.out.println(p);

        List<String> list = new LinkedList<>();
        list.add("123");
        list.add("123");
        list.add("123");

        Map<Integer, String> map = new HashMap<>();

        map.put(5, "5");

        System.out.println(map.containsKey(5));

        Set<Integer> set = new HashSet<>();


    }
}
