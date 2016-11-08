package concursantes;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class Audiencia {
	public void monitorearShow(ProceedingJoinPoint joinpoint) {
		try {
			System.out.println("El show está por comenzar, favor de tomar asiento...");
			System.out.println("Favor de apagar celulares...");
			long horaInicio = System.currentTimeMillis();
			joinpoint.proceed();
			Thread.sleep(1000);
			long horaTermino = System.currentTimeMillis();
			System.out.println("El show ha termiado, aplausos");
			System.out.println("El show tuvo una duración:" + (horaTermino - horaInicio));
		} catch (Throwable t) {
			System.out.println("El show fue terrible, se devolverán las entradas");
		}
	}
}