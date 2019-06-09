package multi_threading;

/**
 * Created by chaitra.kr on 30/09/18.
 */
public class SyncOne {

    /*
    * Send message using threads. Only one message can be sent by a thread at a time
    * */

    public static void main(String[] args) throws InterruptedException {
        MessageSender messageSender = new MessageSender();
        SendMessageThread thread1 = new SendMessageThread(messageSender);
        SendMessageThread thread2 = new SendMessageThread(messageSender);
        SendMessageThread thread3 = new SendMessageThread(messageSender);
        SendMessageThread thread4 = new SendMessageThread(messageSender);
        SendMessageThread thread5 = new SendMessageThread(messageSender);
        SendMessageThread thread6 = new SendMessageThread(messageSender);
        System.out.println("Main running " + Thread.currentThread());
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}

class SendMessageThread extends Thread {

    MessageSender messageSender;

    public SendMessageThread(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void run() {
        System.out.println("Started running "+ this.getName());
        try {
            messageSender.send();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MessageSender {

    private int counter;

   public void send() throws InterruptedException {
        synchronized (this) {
            counter++;
            System.out.println("Message sent by " + this.toString() + ", count: " + counter);
            //Thread.sleep(2000);
        }
    }
}
