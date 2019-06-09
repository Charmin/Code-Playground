package multi_threading;

/**
 * Created by chaitra.kr on 18/05/16.
 */
public class Threads implements Runnable {


    @Override
    public void run() {
        System.out.println("new thread " + Thread.currentThread());
    }


    public static void main(String[] args) {
        System.out.println("I m main");
        System.out.println("No of processors" + Runtime.getRuntime().availableProcessors());
    }
}
