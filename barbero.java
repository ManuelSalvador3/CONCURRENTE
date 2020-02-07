package edu.distrital.log.main;

public class barbero extends Thread {

	private barberia laBarberia;

	public barbero(barberia laBarberia) { //Se inicializa la variable laBarberia
		this.laBarberia = laBarberia;
	}

	public void run() {
		while (true) {
			try {
				laBarberia.esperarCliente();
				// Cortar pelo
				Thread.sleep(5000);
				laBarberia.acabarCorte();
				// Decansa un poco
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			;
		}
	}
}
