package exercise;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {

    private static final AtomicInteger idGenerator = new AtomicInteger();

    private final int id;

    private final WaitingRoom waitingRoom;

    private final SynchronousQueue<Boolean> synchronousQueue;

    private volatile boolean shaved;

    public Customer(WaitingRoom waitingRoom) {
        this.id = idGenerator.incrementAndGet();
        this.waitingRoom = waitingRoom;
        this.synchronousQueue = new SynchronousQueue<>();
    }

    @Override
    public void run() {
        try {
            waitingRoom.takeASeat(this);

            System.out.println("El cliente " + this + " está esperando a que le corten el pelo.");
            waitToBeCalledAndShaved();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callAndShave() throws InterruptedException {
        synchronousQueue.put(true);
        shaved = true;
    }

    public void waitToBeCalledAndShaved() throws InterruptedException {
        synchronousQueue.take();
    }

    public boolean isShaved() {
        return shaved;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
