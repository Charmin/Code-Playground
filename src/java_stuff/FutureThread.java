package java_stuff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureThread {

    public static void main(String[] args) throws InterruptedException {

        //cached thread pool
        final ExecutorService executor = Executors.newCachedThreadPool();
//        executor.execute(new ThreadPrinter());
//        executor.execute(new ThreadPrinter());
//        executor.execute(new ThreadPrinter());

        SimpleCounter counter = new SimpleCounter();
        executor.execute(new CounterSetter(counter));
        counter.setCounter(200);
        Thread.sleep(500);
        System.out.println("In main thread: counter again => " + (counter.counter == 200));
    }
}

class ThreadPrinter implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(": " + i);
            System.out.println("In thread " + Thread.currentThread().getName());
        }
    }
}

class CounterSetter implements Runnable {

    SimpleCounter simpleCounter;

    public CounterSetter(SimpleCounter counter) {
        this.simpleCounter = counter;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (i < 10) {
            System.out.println("In " + Thread.currentThread().getName() + " " + simpleCounter.counter);
            simpleCounter.setCounter(500);
            i++;
        }
    }

    public void setCounter(int c) {
        simpleCounter.setCounter(c);
    }
}

//Shared resource
class SimpleCounter {
    int counter;

    public void setCounter(int c) {
        this.counter = c;
    }
}
