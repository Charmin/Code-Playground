package java_stuff;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chaitra.kr on 11/07/17.
 */
public class StreamsOne {

    static Set<Integer> pesoUnits = Stream.of(20,50,100,200,500,1000).collect(Collectors.toSet());
    public static void main(String[] args) {

        List<String> stringsSample = Arrays.asList("soup","butter","jam","potato","sprouts");
        System.out.println(howMuchToAsk(40, 81));
    }

    public static int howMuchToAsk(int c, int p) {
        int diff = p-c;
        if(pesoUnits.contains(diff)) {
            return 0;
        }
        Optional<Integer> result = pesoUnits.stream().filter(pe -> ((pe - diff)>0)).filter(n -> ((n-diff) <= 9)).findAny();
        if(result.isPresent()) {
            return result.get()-diff;
        } else {
            return -1;
        }
    }
}
