package mx.edu.utng.servicio;

import java.util.List;
import mx.com.gm.jdbc.Persona;
import mx.com.gm.jdbc.PersonaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	private PersonaDao personaDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Persona> listarPersonas() {
		return personaDao.findAllPersonas();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Persona recuperarPersona(Persona persona) {
		return personaDao.findPersonaById(persona.getIdPersona());
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void agregarPersona(Persona persona) {
		personaDao.insertPersona(persona);
		personaDao.insertPersona(persona);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void modificarPersona(Persona persona) {
		personaDao.updatePersona(persona)
		personaDao.updatePersona(persona);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void eliminarPersona(Persona persona) {
		personaDao.deletePersona(persona);
		personaDao.deletePersona(persona);
	}
}