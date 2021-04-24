package designpatterns.decorator;


import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class Driver {

    @Test
    public void driveChristmasTree() {
        ChristmasTree tree = new Garland(new ChristmasTreeImpl());
        System.out.println(tree.decorate());
        TreeDecorator tree2 = new BubbleLights(new Garland(new BubbleLights(new ChristmasTreeImpl())));
        System.out.println(tree2.decorate());
        Set<Integer> in = new HashSet<>();
    }
}
