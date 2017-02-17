package agendaContactos;

public interface InterfazProtocolo {

	String insertarContacto(String nombre, String telefono, String direccion);

	String modificarContacto(String nombre, String telefono);

	String verContacto(String nombre);

}