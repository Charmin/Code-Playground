package java_stuff;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypeErasure {

    public static void main(String[] args) {
        Set<Integer> s1 = Stream.of(1, 2, 3, 4).collect(Collectors.toSet());
        Set<Integer> s2 = Stream.of(3, 9, 7, 8).collect(Collectors.toSet());
        System.out.println(checkIfPresent(s1, s2));
    }

    private static boolean checkIfPresent(Set<?> s1, Set<?> s2) {
        for (Object o1 : s2) {
            if (s1.contains(o1)) {
                return true;
            }
        }
        return false;
    }
}
