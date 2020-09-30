package lesson2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);

        List<String> stringList = list.stream()
                .filter(i -> i % 2 == 0)
                .map(integer -> "WOW: " + integer)
                .collect(Collectors.toList());

        stringList.forEach(System.out::println);

    }
}
