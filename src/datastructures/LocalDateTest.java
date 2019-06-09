package datastructures;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chaitra.kr on 06/10/17.
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        LocalDateTime atElevenFiftyFive = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.minusMinutes(5));
        System.out.println(atElevenFiftyFive);
        System.out.println(LocalDateTime.now());
        LocalDateTime now =  LocalDateTime.now();
        String l = "rrm,gg,dd";
        String g = "f";
        String[] splits = l.split(",");
        ArrayList<String> a1 = new ArrayList<String>(){{add("a");add("b");add("c");add("d");}};
        ArrayList<String> a2 = new ArrayList<String>(){{add("a");add("b");add("c");add("x");add("y");add("z");}};
        boolean t = a1.removeAll(a2);
        boolean d = true;

        System.out.println(t);
        if(now.equals(atElevenFiftyFive)) {
            System.out.println("Now is the time");
        } else {
            int hours = atElevenFiftyFive.getHour();
            int minutes = hours*60 ; //679
            int atElMinutes = minutes + (atElevenFiftyFive.getMinute());
            int nowMinutes = (LocalDateTime.now().getHour()*60) + LocalDateTime.now().getMinute();
            System.out.println("Diff: "+(atElMinutes-nowMinutes));
        }
    }
}
