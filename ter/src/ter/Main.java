package ter;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import static java.lang.Thread.*;

class threads extends Thread {
    public void run() {
        System.out.println("");
    }
}

class runnable implements Runnable {

    @Override
    public void run() {
        System.out.println("I sense a disturbance in the force..."+threads.currentThread().getName());
    }
}

class TER {
    static class Main {
        public static void main(String[] args) throws InterruptedException {
            threads thread1;
            thread1 = new threads();
            thread1.start();

            System.out.println("Creating runnable thread: Order 66");
            Thread runnable1 = new Thread(new runnable());
            runnable1.start();

            ExecutorService order66;
            order66 = Executors.newSingleThreadExecutor();
            order66.submit(() -> {
                String threadymcthreadthread;
                threadymcthreadthread = currentThread().getName();
                System.out.println("Thread and Pool name: " + threadymcthreadthread);
            });

            //thread join example
            threads thread2;
            thread2 = new threads();
            thread2.sleep(1500);

            threads thread3;
            thread3 = new threads();
            thread3.sleep(3000);

            System.out.println("Start taking over the Senate.");
            thread2.start();

            try {
                thread2.join(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            System.out.println("Corrupt the most powerful jedi and execute order 66.");
            thread3.start();


            //shutdown
            try {
                System.out.println("Shutting down 'order66' - process completed");
                order66.shutdown();

            } finally {
                if (order66.isTerminated()) {
                    System.err.println("The Jedi have evaded us. We will rebuild our strength!");
                }
                order66.shutdownNow();
                System.out.println("Exiting");
            }
        }
    }
}

