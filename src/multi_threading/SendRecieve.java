package multi_threading;

public class SendRecieve {

    public static void main(String[] args) throws InterruptedException {
        DataSync sync = new DataSync();
        new Thread(new Sender(sync)).start();
        new Thread(new Receiver(sync)).start();
    }
}

class Sender implements Runnable {
    private DataSync sync;

    Sender(DataSync s) {
        this.sync = s;
    }

    String[] messages = {
            "First",
            "Second",
            "Third",
            "Fourth",
            "Fifth",
            "Sixth",
            "Seventh"
    };

    @Override
    public void run() {
        for (String s : messages) {
            sync.send(s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Receiver implements Runnable {
    private DataSync sync;

    Receiver(DataSync s) {
        this.sync = s;
    }

    @Override
    public void run() {
        try {
            String message;
            do {
                Thread.sleep(1000);
                message = sync.receive();
            } while (message != null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//Guarded locks
class DataSync {

    private boolean empty = true;
    private String message;

    void send(String a) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.message = a;
        System.out.println("Send :" + a);
        empty = false;
        notifyAll();
    }

    String receive() throws InterruptedException {
        while (empty) {
            wait();
        }
        System.out.println("Consumed " + message);
        empty = false;
        notifyAll();
        return message;
    }
}