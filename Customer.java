package exercise;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {

    private static final AtomicInteger idGenerator = new AtomicInteger();

    private final int id;

    private final WaitingRoom waitingRoom;

    private final SynchronousQueue<Boolean> synchronousQueue;

    private volatile boolean shaved;

    public Customer(WaitingRoom waitingRoom) { // Inicializamos los atributos de la clase
        //El cliente se genera así mismo el siguiente id disponible.
        this.id = idGenerator.incrementAndGet();
        this.waitingRoom = waitingRoom;
        this.synchronousQueue = new SynchronousQueue<>();
    }

    @Override
    public void run() {
        try {
            // El cliente se sienta a esperar. En caso de que no haya sitio se queda en estado "block".
            waitingRoom.takeASeat(this);

            System.out.println("El cliente " + this + " está esperando a que le corten el pelo.");
            waitToBeCalledAndShaved(); // Espera a que le llamen para cortarse el pelo

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Se llama al cliente y se le afeita, poniendo el atributo de shaved a true
    public void callAndShave() throws InterruptedException {
        synchronousQueue.put(true);
        shaved = true;
    }

    // Funcion que implementa la espera que hace el cliente para que le llamen a cortarse 
    // el pelo
    public void waitToBeCalledAndShaved() throws InterruptedException {
        synchronousQueue.take();
    }
    
    // Funcion que devuelve el valor del atributo shaved que puede ser true si se ha afeitado y 
    // false si no
    public boolean isShaved() {
        return shaved;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
