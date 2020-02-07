package edu.distrital.log.main;

public class cliente extends java.lang.Thread {

	private barberia laBarberia; //Atributo que representa laBarberia
	private int clienteId; //Atributo que representa el id del cliente
	private boolean cortePelo = false; //Atributo que representa si el corte de pelo se ha realizado o no.

	public cliente(barberia laBarberia, int clienteId) { //Inicializamos los atributos
		this.laBarberia = laBarberia;
		this.clienteId = clienteId;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
				cortePelo = laBarberia.entrar(clienteId);
				// Cortar pelo
				if (cortePelo) {
					// Espero hasta que me crezca el pelo
					Thread.sleep(25000);
				} else {
					// Espero y lo vuelvo a intentar
					Thread.sleep(4000);
				}
			} catch (InterruptedException e) {
			}
			;
		}
	}
}
