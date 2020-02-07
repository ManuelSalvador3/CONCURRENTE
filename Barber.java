package exercise;

public class Barber implements Runnable {

    private final WaitingRoom waitingRoom;

    public Barber(WaitingRoom waitingRoom) {
        this.waitingRoom = waitingRoom;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Customer customer = waitingRoom.nextCustomer();

                System.out.println("---- El barbero llama al cliente " + customer + " y le empieza a cortar el pelo. ----");
                Thread.sleep(2000);
                customer.callAndShave();
            }

        } catch (InterruptedException e) {
            System.out.println("El barbero cierra la barbería.");
        }
    }
}
