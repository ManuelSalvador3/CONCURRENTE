package exercise;

import java.util.concurrent.ArrayBlockingQueue;

public class WaitingRoom {

    private final ArrayBlockingQueue<Customer> waitingCustomers; //Cola implementada mediante un array

    public WaitingRoom(int capacity) { //Inicializamos los atributos
        waitingCustomers = new ArrayBlockingQueue<>(capacity); 
    }

    // Funcion que hace que el cliente se siente en una silla a esperar.
    public void takeASeat(Customer customer) throws InterruptedException {
        waitingCustomers.put(customer);
    }
    // Funcion que devuelve el siguiente cliente esperando
    public Customer nextCustomer() throws InterruptedException {
        return waitingCustomers.take();
    }

}
