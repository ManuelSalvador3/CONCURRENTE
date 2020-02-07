package exercise;

import java.util.concurrent.ArrayBlockingQueue;

public class WaitingRoom {

    private final ArrayBlockingQueue<Customer> waitingCustomers;

    public WaitingRoom(int capacity) {
        waitingCustomers = new ArrayBlockingQueue<>(capacity);
    }

    public void takeASeat(Customer customer) throws InterruptedException {
        if (waitingCustomers.remainingCapacity() == 0)
            //System.out.println("El cliente "+ customer + "ha llegado pero no tiene donde esperar.");
        waitingCustomers.put(customer);
    }

    public Customer nextCustomer() throws InterruptedException {
        return waitingCustomers.take();
    }

}
