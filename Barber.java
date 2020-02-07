package exercise;

public class Barber implements Runnable {

    private final WaitingRoom waitingRoom;

    public Barber(WaitingRoom waitingRoom) { // Se inicializan los atributos de la clase
        this.waitingRoom = waitingRoom;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Se asigna a customer el siguiente en la cola de espera
                Customer customer = waitingRoom.nextCustomer();
                // Se le llama a cortarse el pelo
                System.out.println("---- El barbero llama al cliente " + customer + " y le empieza a cortar el pelo. ----");
                Thread.sleep(2000); // Duerme 2000ms y cuando pasa el tiempo ha acabado el corte de pelo
                customer.callAndShave(); // 
            }

        } catch (InterruptedException e) {
            System.out.println("El barbero cierra la barbería.");
        }
    }
}
