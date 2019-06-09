package java_stuff;

import java.util.concurrent.*;

/**
 * Created by chaitra.kr on 23/06/16.
 */
public class Concurreny {
    public static void main(String[] args) {
        WorkerClass c = new WorkerClass();


    }

}

class WorkerClass {
    public void meat() {
        Runnable myTask = () -> {
            try {
                String curentThread = Thread.currentThread().getName();
                System.out.println("Inside -> " + curentThread);
                System.out.println("Break ");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Break second ");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        Thread t = new Thread(myTask);
        System.out.println(Thread.currentThread().getName());
        System.out.println("After Runnable " + t.getName());
        t.start(); //first thread

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            String myThread = Thread.currentThread().getName();
            System.out.println("Executor service - RunnableTask1 " + myThread);
        });

        Callable<Integer> callableTask = () -> {
            String myThread = Thread.currentThread().getName();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Executor service - CallableTask " + myThread);
            return 124;
        };

        //Another task for the data
        service = Executors.newFixedThreadPool(1);
        Future<Integer> mine = service.submit(callableTask);

        try {
            System.out.println("Finally is it done ? :" + mine.isDone());
            Integer result = mine.get();
            System.out.println("Is it done ?: " + mine.isDone());
            System.out.println(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        shutDown(service);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable runnable = () -> {
            System.out.println("Scheduling task for :" + System.nanoTime());
        };

        int initialDelay = 10;
        int period = 5;
        executorService.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS);

        //shutDown(executorService);

    }


    public void shutDown(ExecutorService service) {

        try {
            System.out.println("Attempt to shutdown executor");
            service.shutdown();
            service.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            System.err.print("interrupted");
        } finally {
            if (!service.isTerminated()) {
                System.err.println("Forced shutdown");
                service.shutdownNow();
            }
            System.out.println("Complete shutdown");
        }


    }

}
