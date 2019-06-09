package multi_threading;

import java.util.LinkedList;

public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {

        PC pc = new PC();

        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            pc.produce();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            pc.consume();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        //start both threads
        t1.start();
        t2.start();

        //t1 finishes before t2;
        t1.join();
        t2.join();

    }

    static class PC {

        LinkedList<Integer> buffer = new LinkedList<>();
        int capacity = 2;

        public void produce() throws InterruptedException {
            while (true) {
                int val = 0;
                synchronized (this) {
                    while (buffer.size() == capacity) {
                        wait();
                    }
                    System.out.println("Producer produced value" + val);
                    buffer.add(val);
                    // notifies the consumer thread that
                    // now it can start consuming
                    notify();
                    //for pause and readability
                    Thread.sleep(1000);
                }
            }
        }

        public void consume() throws InterruptedException {
            while (true) {
                synchronized (this) {
                    while (buffer.isEmpty()) {
                        wait();
                    }
                    int val = buffer.remove();
                    System.out.println("Consumer consumed " + val);
                    notify();
                    Thread.sleep(1000);
                }
            }
        }


    }
}
