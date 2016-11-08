package concursantes;

import org.springframework.stereotype.Component;

@Component
public class Audiencia {
	public void sentarse() {
		System.out.println("El show está por comenzar, favor de tomar asiento...");
	}

	public void apagarCelulares() {
		System.out.println("Favor de apagar celulares...");
	}

	public void aplaudir() {
		System.out.println("El show ha termiado, clap clap clap");
	}

	public void devolucion() {
		System.out.println("El show fue terrible, se devolverán las entradas");
	}
}