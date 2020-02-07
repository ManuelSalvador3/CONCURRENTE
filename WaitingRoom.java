package exercise;

import java.util.concurrent.ArrayBlockingQueue;

public class WaitingRoom {

    private final ArrayBlockingQueue<Customer> waitingCustomers; //Cola implementada mediante un array

    public WaitingRoom(int capacity) { //Se crea la cola (asientos de espera) de la barbería.
        waitingCustomers = new ArrayBlockingQueue<>(capacity);
    }
    
    // Funcion que hace que el cliente se siente en una silla a esperar.
    // En caso de no tener sitio, waitingCustomers devuelve block así bloqueando el hilo.
    public void takeASeat(Customer customer) throws InterruptedException {
        waitingCustomers.put(customer);
    }
     // Funcion que devuelve el siguiente cliente esperando
    public Customer nextCustomer() throws InterruptedException {
        return waitingCustomers.take();
    }

}
