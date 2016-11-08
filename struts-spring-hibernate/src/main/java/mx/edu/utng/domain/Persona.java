package mx.edu.utng.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona")
	private long idPersona;
	private String nombre;
	@Column(name = "ape_paterno")
	private String apePaterno;
	@Column(name = "ape_materno")
	private String apeMaterno;
	private String email;

	public Persona() {
	}

	public Persona(long idPersona) {
		this.idPersona = idPersona;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
	return "Persona [idPersona=" + idPersona + ",
	nombre=" + nombre
	+ ", apePaterno=" + apePaterno + ",
	apeMaterno=" + apeMaterno
	+ ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apeMaterno == null) ? 0 : apeMaterno.hashCode());
		result = prime * result + ((apePaterno == null) ? 0 : apePaterno.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (idPersona ^ (idPersona >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Persona other = (Persona) obj;
		if (apeMaterno == null) {
			if (other.apeMaterno != null) {
				return false;
			}
		} else if (!apeMaterno.equals(other.apeMaterno)) {
			return false;
		}
		if (apePaterno == null) {
			if (other.apePaterno != null) {
				return false;
			}
		} else if (!apePaterno.equals(other.apePaterno)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (idPersona != other.idPersona) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		return true;
	}
}

}
