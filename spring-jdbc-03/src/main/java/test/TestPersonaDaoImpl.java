package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;
import mx.com.gm.jdbc.Persona;
import mx.com.gm.jdbc.PersonaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-test.xml", "classpath:applicationContext.xml" })
public class TestPersonasDaoImpl {
	private static Log logger = LogFactory.getLog("TestPersonasDaoImpl");
	@Autowired
	private PersonaDao personaDao;

	@Test
	public void deberiaMostrarPersonas() {
		try {
			System.out.println();
			logger.info("Inicio del test deberiaMostrarPersonas");
			List<Persona> personas = personaDao.findAllPersonas();
			int contadorPersonas = 0;
			for (Persona persona : personas) {
				logger.info("Persona: " + persona);
				contadorPersonas++;
			}
			assertEquals(contadorPersonas, personaDao.contadorPersonas());
			logger.info("Fin del test deberiaMostrarPersonas");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	public void testContarPersonasPorNombre() {
		try {
			System.out.println();
			logger.info("Inicio del test Contar Personas por nombre");
			String nombre = "Juan";
			Persona personaEjemplo = new Persona();
			personaEjemplo.setNombre(nombre);
			int noPersonasEncontradas = personaDao.contadorPersonasPorNombre(personaEjemplo);
			logger.info("Numero de personas encontradas por nombre '" + nombre + "': " + noPersonasEncontradas);
			assertEquals(2, noPersonasEncontradas);
			logger.info("Fin del test Contar Personas por nombre");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	public void deberiaEncontrarPersonaPorId() {
		try {
			System.out.println();
			logger.info("Inicio del test deberiaEncontrarPersonaPorId");
			int idPersona = 1;
			Persona persona = personaDao.findPersonaById(idPersona);
			assertEquals("Admin", persona.getNombre());
			logger.info("Persona recuperada (id=" + idPersona + "): " + persona);
			logger.info("Fin del test deberiaEncontrarPersonaPorId");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	public void deberiaInsertarPersona() {
		try {
			System.out.println();
			logger.info("Inicio del test deberiaInsertarPersona");
			assertEquals(3, personaDao.contadorPersonas());
			Persona persona = new Persona();
			persona.setNombre("Carlos");
			persona.setApePaterno("Romero");
			persona.setApeMaterno("Esparza");
			persona.setEmail("carlos.romero@gmail.com");
			personaDao.insertPersona(persona);
			persona = personaDao.getPersonaByEmail(persona);
			logger.info("Persona insertada: " + persona);
			assertEquals(4, personaDao.contadorPersonas());
			logger.info("Fin del test deberiaInsertarPersona");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	public void deberiaActualizarPersona() {
		try {
			System.out.println();
			logger.info("Inicio del test deberiaActualizarPersona");
			int idPersona = 1;
			Persona persona = personaDao.findPersonaById(idPersona);
			logger.info("Persona a modificar (id=" + idPersona + "): " + persona);
			persona.setNombre("Administrador");
			persona.setApeMaterno("Sistemas");
			personaDao.updatePersona(persona);
			persona = personaDao.findPersonaById(idPersona);
			assertEquals("Administrador", persona.getNombre());
			logger.info("Persona modificada (id=" + idPersona + "): " + persona);
			logger.info("Fin del test deberiaActualizarPersona");
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	public void deberiaEliminarPersona() {
		try {
			System.out.println();
			logger.info("Inicio del test deberiaEliminarPersona");
			int idPersona = 2;
			Persona persona = personaDao.findPersonaById(idPersona);
			logger.info("Persona a eliminar (id=" + idPersona + "): " + persona);
			personaDao.deletePersona(persona);
			persona = personaDao.findPersonaById(idPersona);
			assertNull(persona);
			logger.info("Nuevo listado de personas:");
			List<Persona> personas = personaDao.findAllPersonas();
			int contadorPersonas = 0;
			for (Persona persona2 : personas) {
				logger.info("Persona: " + persona2);
				contadorPersonas++;
			}
			assertEquals(contadorPersonas, personaDao.contadorPersonas());
			logger.info("Fin del test deberiaEliminarPersona");
			System.out.println();
		} catch (Exception e) {
			logger.error("Error JBDC", e);
		}
	}

	@Test
	@Transactional
	public void deberiaMostrarPersonas() {
		try {
			System.out.println();
			logger.info("Inicio del test deberiaMostrarPersonas");
			int contadorPersonas = this.desplegarPersonas();
			assertEquals(contadorPersonas, personaDao.contadorPersonas());
			logger.info("Fin del test deberiaMostrarPersonas");
			System.out.println();
		} catch (Exception e) {
			logger.error("Error Servicio", e);
		}
	}

	private int desplegarPersonas() {
		List<Persona> personas = personaService.listarPersonas();
		int contadorPersonas = 0;
		for (Persona persona : personas) {
			logger.info("Persona: " + persona);
			contadorPersonas++;
		}
		return contadorPersonas;
	}

	@Test
	@Transactional
	public void testOperaciones() {
		try {
			System.out.println();
			logger.info("Inicio del test testOperaciones");
			Persona persona1 = new Persona();
			persona1.setNombre("Andrea");
			persona1.setApePaterno("Lara");
			persona1.setEmail("andrea.lara@mimail.com");
			personaService.agregarPersona(persona1);
			assertEquals(4, personaDao.contadorPersonas());
			Persona persona2 = personaService.recuperarPersona(new Persona(1));
			persona2.setNombre("Administrador");
			personaService.modificarPersona(persona2);
			this.desplegarPersonas();
			logger.info("Fin del test testOperaciones");
			System.out.println();
		} catch (Exception e) {
			logger.error("Error Servicio", e);
		}
	}

	@Test
	public void testCompruebaOperaciones() {
		try {
			System.out.println();
			logger.info("Fin del test testCompruebaOperaciones");
			assertEquals(3, personaDao.contadorPersonas());
			this.desplegarPersonas();
			logger.info("Fin del test testCompruebaOperaciones");
			System.out.println();
		} catch (Exception e) {
			logger.error("Error Servicio", e);
		}
	}
}