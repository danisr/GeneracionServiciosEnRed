package agendaContactos;

public class Contacto {
	private String nombre;
	private String telefono;
	private String direccion;

	public Contacto(String nombre, String telefono, String direccion) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// Se sobreescribe método equal() para comprobar por nombre y teléfono;
	@Override
	public boolean equals(Object o) {
		Contacto c = (Contacto) o;
		if (nombre.equals(c.nombre)) { // Comprobar que el nombre sea igual
			return true;
		}
		if (telefono.equals(c.telefono)) { // Comprobar que el teléfono sea igual
			return true;
		}
		return false;
	}
}