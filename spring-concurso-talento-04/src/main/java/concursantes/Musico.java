package concursantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("jasonPiano")
public class Musico implements Concursante { 
	@Value("Noche de Paz")
	private String cancion;
	@Autowired
	@Qualifier("piano")
	private Instrumento instrumento;

	public Musico() {
	}

	public void ejecutar() throws EjecucionException {
		System.out.println("Tocando " + cancion + ": ");
		instrumento.tocar();
	}

	public String getCancion() {
		return cancion;
	}

	public void setCancion(String cancion) {
		this.cancion = cancion;
	}

	public Instrumento getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}
}
