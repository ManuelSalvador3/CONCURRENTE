package edu.distrital.log.main;

// Suposiciones:
// - El barbero corta el pelo fuera del objeto protegido
//   Si lo cortara dentro, seria menos realista la simulacion
//   del tiempo en que se tarda en hacer esta operacion. Si
//   no se hace este retardo, es decir si el tiempo de corte
//   de pelo fuera practicamente 0, no habria casi nunca
//   procesos esperando.
// - Se simula la silla del barbero y las sillas de la sala
//   de espera.
public class barberia {

	private int nSillasEspera; //Atributo que representa las sillas de espera totales
	private int nSillasEsperaOcupadas = 0; // Atributo que representa las silla de espera ocupadas
	private boolean sillaBarberoOcupada = false; //Atributo que dice si la silla del barbero esta ocupada o no
	private boolean finCorte = false; // Atributo que dice si el corte de pelo al cliente ha acabado o no
	private boolean barberoDormido = false; // Atributo que dice si el barbero esta dormido o no

	// JAVA: sólo puede haber N_Sillas_Espera_max hebras
	// esperando dentro del monitor a que le toque.

	public barberia(int nSillasEspera) { //Inicicializamos las variables
		this.nSillasEspera = nSillasEspera;
	}

	public synchronized boolean entrar(int clienteId)
			throws InterruptedException {
		if (nSillasEsperaOcupadas == nSillasEspera) { 
			// Si las sillas ocupadas igual a las totales me voy sin cortar el pelo
			System.out.println("---- El cliente " + clienteId
					+ " se va sin cortarse el pelo");
			return false;
		} else {
			// Si no estan todas ocupadas me siento a esperar
			nSillasEsperaOcupadas++; //Aumenta en 1 las sillas ocupadas
			System.out.println("---- El cliente " + clienteId
					+ " se sienta en la silla de espera");
			while (sillaBarberoOcupada) {
			// Mientras la silla del barbero esta ocupada esperamos
				wait();
			}

			// Desocupo la silla de espera
			nSillasEsperaOcupadas--;

			// Me siento en la silla del barbero
			sillaBarberoOcupada = true;
			finCorte = false;

			// Si el barbero está dormido le despierto
			if (barberoDormido) {
				System.out.println("---- El cliente " + clienteId
						+ " despierta al barbero");
				notifyAll();
			}

			// Espero a que me corte el pelo
			System.out.println("---- El cliente " + clienteId
					+ " en la silla de barbero");
			while (!finCorte) {
			// Mientras el corte de pelo no haya acabado, esperamos
				wait();
			}
			// Queda libre la silla del barbero
			sillaBarberoOcupada = false;

			// Que pase el siguiente
			notifyAll();

			System.out.println("---- El cliente " + clienteId
					+ " se va con el pelo cortado");
			return true;
		}
	}

	// El barbero espera a que llegue un cliente. Mientras la silla del barbero no este
	// ocupada el barbero espera durmiendo.
	public synchronized void esperarCliente() throws InterruptedException {
		barberoDormido = true;
		while (!sillaBarberoOcupada) {
			System.out.println("++++ Barbero esperando cliente");
			wait();
		}
		barberoDormido = false;
		System.out.println("++++ Barbero cortando el pelo");
	}
	
	// Si ha acabado el corte de pelo finCorte= true y se notifica a todos los 
	// que estan esperando de que ha acabado
	public synchronized void acabarCorte() {
		finCorte = true;
		System.out.println("++++ Barbero termina de cortar el pelo");
		notifyAll();
	}

}
