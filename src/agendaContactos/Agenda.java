package agendaContactos;

import java.util.ArrayList;

public class Agenda implements InterfazProtocolo{
	ArrayList<Contacto> contactos;

	public Agenda() {
		contactos = new ArrayList<Contacto>();
	}

	public String insertarContacto(String nombre, String telefono, String direccion) {
		Contacto nuevo = new Contacto(nombre, telefono, direccion);

		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).equals(nuevo)) { // Método equals de Contacto
				return "El contacto ya exite"; // Si el nombre o el teléfono ya existen
			}
		}
		contactos.add(nuevo);
		return "Nuevo Contacto Añadido";
	}

	public String modificarContacto(String nombre, String telefono) {
		boolean repetido = false;
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getTelefono().equals(telefono)) {
				repetido = true;
				return "El teléfono ya existe";
			}		
		}		
		for (int i = 0; i < contactos.size(); i++) {			
			if (contactos.get(i).getNombre().equals(nombre) && !repetido) {
				contactos.get(i).setTelefono(telefono); //Se modifica el teléfono por uno nuevo, si no está repetido
				return "Contacto Modificado";
			}
		}
		return "El contacto no se ha encontrado"; //Si el nombre introducido no existe
	}

	public String verContacto(String nombre) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().equals(nombre)) {
				return "Nombre: " + contactos.get(i).getNombre() + ", Teléfono: " + contactos.get(i).getTelefono()
						+ ", Dirección: " + contactos.get(i).getDireccion();
			}
		}
		return "El contacto no se ha encontrado"; //Si el nombre introducido no existe
	}
}