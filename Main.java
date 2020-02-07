package exercise;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        WaitingRoom waitingRoom = new WaitingRoom(10);

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        executorService.submit(new Barber(waitingRoom));
        executorService.submit(new Barber(waitingRoom));
        executorService.submit(new Barber(waitingRoom));

        //Se crea un nuevo cliente y se añade a la lista 
        List<Customer> customers = Stream.generate(() -> new Customer(waitingRoom))
                                         .limit(100)
                                         .peek(executorService::submit)
                                         .collect(toList());
        // Cuando no todos los clientes han sido afeitas se hace un sleep de 1 segundo y se continua 
        while (!customers.stream().allMatch(Customer::isShaved)) {
            TimeUnit.SECONDS.sleep(1);
        }
        // Cuando todos los cleintes han sido afeitados el barbero se va a dormir
        System.out.println("all customers have been shaved");
        executorService.shutdownNow();
        executorService.awaitTermination(1, MINUTES);
    }

}
