package problem_solving;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitra.kr on 17/05/16.
 */
public class Apple {

    double weight;
    int price;

    public Apple(double weight,int price){
        this.weight = weight;
        this.price = price;
    }

    public static void main(String[] args) {

        Apple ap1 = new Apple(2,20);
        Apple ap2 = new Apple(6,10);
        Apple ap3 = new Apple(7,32);
        Apple ap4 = new Apple(3,23);
        Apple ap5 = new Apple(1,27);

        List<Apple> apples = new ArrayList<>();
        apples.add(ap1);
        apples.add(ap2);
        apples.add(ap3);
        apples.add(ap4);
        apples.add(ap5);

        for(Apple a : apples)
            System.out.println("list :" + a.price);

//        apples.sort(new Comparator<problem_solving.Apple>() {
//            @Override
//            public int compare(problem_solving.Apple o1, problem_solving.Apple o2) {
//                return o2.price - o1.price +2;
//            }
//        });

        apples.sort((Apple a1, Apple a2)-> a1.price-a2.price);

        System.out.println("\n");
        for(Apple a : apples)
        System.out.println("list :" + a.price);

    }
}
