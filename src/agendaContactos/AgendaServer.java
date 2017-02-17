package agendaContactos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class AgendaServer extends Thread {
	private Socket clientSocket;
	private Agenda agenda;

	public AgendaServer(Socket socket) {
		clientSocket = socket;
		agenda = new Agenda();
	}

	public void run() {
		boolean salir = false;
		
		try {
			System.out.println("Arrancando hilo SERVIDOR");
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			
			while(!salir) {
				System.out.println("Esperando mensaje de operación...");
				byte[] buffer = new byte[25];
				is.read(buffer);
				String operacion = new String(buffer);
				operacion = operacion.trim();				
				System.err.println("Operación Recibida: " + operacion);
	
				// Comprobar estado de operación
				if (operacion.equals("nuevo")) {
					buffer = new byte[25];
					is.read(buffer);
					String nombre = new String(buffer);
					nombre = nombre.trim();
	
					buffer = new byte[25];
					is.read(buffer);
					String telefono = new String(buffer);
					telefono = telefono.trim();
	
					buffer = new byte[25];
					is.read(buffer);
					String direccion = new String(buffer);
					direccion = direccion.trim();
	
					// Envío de respuesta nuevo contacto a cliente
					System.out.println("Enviando respuesta de NUEVO CONTACTO");
					String msg = agenda.insertarContacto(nombre, telefono, direccion); //msg será lo que devuelva el método
					os.write(msg.getBytes());
	
				} else if (operacion.equals("modificar")) {
					buffer = new byte[25];
					is.read(buffer);
					String nombre = new String(buffer);
					nombre = nombre.trim();
	
					buffer = new byte[25];
					is.read(buffer);
					String telefono = new String(buffer);
					telefono = telefono.trim();
					
					//Envío de respuesta modificar contacto a cliente
					System.out.println("Enviando respuesta de MODIFICAR CONTACTO");
					String msg = agenda.modificarContacto(nombre, telefono);					
					os.write(msg.getBytes());
	
				} else if (operacion.equals("visualizar")) {
					buffer = new byte[25];
					is.read(buffer);
					String nombre = new String(buffer);
					nombre = nombre.trim();
					
					//Envío de respuesta ver contacto a cliente
					System.out.println("Enviando respuesta de VISUALIZAR CONTACTO");
					String msg = agenda.verContacto(nombre);
					os.write(msg.getBytes());
					
				// Si operacion = "salir", salimos del bucle y se termina el hilo
				} else if (operacion.equals("salir")) {
					salir = true;
				
				// Si no introduce ninguna de las palabras mostradas
				} else {
					String msg = "ERROR: Introduzca una de las palabras mostradas por pantalla.";
					os.write(msg.getBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Hilo terminado");
	}

	public static void main(String[] args) {
		System.out.println("Creando socket servidor");
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket();
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Aceptando conexiones...");

		while (serverSocket != null) {
			try {
				Socket newSocket = serverSocket.accept();
				System.out.println("Conexión recibida");

				AgendaServer hilo = new AgendaServer(newSocket);
				hilo.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Terminado");
	}
}