package java_stuff;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by chaitra.kr on 11/07/17.
 */
public class StreamsOne {

    static Set<Integer> pesoUnits = Stream.of(20, 50, 100, 200, 500, 1000).collect(Collectors.toSet());

    public static void main(String[] args) {

        List<String> stringsSample = Arrays.asList("soup", "butter", "jam", "potato", "sprouts");
        //System.out.println(howMuchToAsk(40, 81));
        AtomicLong counter = new AtomicLong(Long.MAX_VALUE);
        int in = 0;
        int jold = 13;
        int j = 1;
        while (in < 100) {
            long o = counter.getAndIncrement();
            //System.out.println(o);
            j = (int) (o % (long) 5);
            System.out.println(j);
            if (j == jold) {
                //System.out.println(o);
                break;
            }
            jold = j;
            in++;
        }

    }

    public static int howMuchToAsk(int c, int p) {
        int diff = p - c;
        if (pesoUnits.contains(diff)) {
            return 0;
        }
        Optional<Integer> result = pesoUnits.stream().filter(pe -> ((pe - diff) > 0)).filter(n -> ((n - diff) <= 9)).findAny();
        if (result.isPresent()) {
            return result.get() - diff;
        } else {
            return -1;
        }
    }
}
