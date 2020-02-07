package exercise;

import java.util.concurrent.ArrayBlockingQueue;

public class WaitingRoom {

    private final ArrayBlockingQueue<Customer> waitingCustomers; //Cola implementada mediante un array

    public WaitingRoom(int capacity) { //Inicializamos los atributos
        waitingCustomers = new ArrayBlockingQueue<>(capacity);
    }
    
    // Funcion que hace que el cliente se siente en una silla a esperar.
    public void takeASeat(Customer customer) throws InterruptedException {
        if (waitingCustomers.remainingCapacity() == 0)
            //System.out.println("El cliente "+ customer + "ha llegado pero no tiene donde esperar.");
        waitingCustomers.put(customer);
    }
     // Funcion que devuelve el siguiente cliente esperando
    public Customer nextCustomer() throws InterruptedException {
        return waitingCustomers.take();
    }

}
