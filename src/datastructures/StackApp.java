package datastructures;

/**
 * Created by chaitra.kr on 16/10/18.
 */
public class StackApp {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(7);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        System.out.println(stack.getMax());
        stack.pop();
        stack.pop();
        System.out.println(stack.getMax());


        ISBNCache cache = new ISBNCache(10);
        cache.insert(2,"chai");
        cache.insert(3,"hai");
        cache.insert(4,"bye");
        cache.insert(5,"kai");
        cache.insert(6,"rai");
        cache.insert(7,"pie");
        cache.lookUp(4);
        cache.lookUp(5);
        cache.insert(8, "jai");
        cache.insert(9, "pai");
        cache.insert(10, "tie");
        cache.insert(11, "ol");
        cache.lookUp(4);

    }
}
