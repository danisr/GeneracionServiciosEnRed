package agendaContactos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class AgendaClient {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean salir = false;
		String operacion, nombre, telefono, direccion;
		
		try {
			System.out.println("Creando socket CLIENTE");
			Socket clientSocket = new Socket();

			System.out.println("Estableciendo la conexión...");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);

			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();

			while(!salir) {
				System.out.println("Enviando petición a realizar");
				System.out.print("Operación: ('nuevo'; 'modificar'; 'visualizar'; 'salir'): ");
				operacion = scan.nextLine();
				os.write(operacion.getBytes());
	
				// Si el usuario quiere un nuevo contacto
				if (operacion.equals("nuevo")) {
					System.out.print("Introduzca el nombre: ");
					nombre = scan.nextLine();
					os.write(nombre.getBytes());
	
					System.out.print("Introduzca el teléfono: ");
					telefono = scan.nextLine();
					os.write(telefono.getBytes());
	
					System.out.print("Introduzca la dirección: ");
					direccion = scan.nextLine();
					os.write(direccion.getBytes());
	
				// Si el usuario quiere modificar un contacto
				} else if (operacion.equals("modificar")) {
					System.out.print("Introduzca el nombre: ");
					nombre = scan.nextLine();
					os.write(nombre.getBytes());
	
					System.out.print("Introduzca el nuevo teléfono: ");
					telefono = scan.nextLine();
					os.write(telefono.getBytes());
				
				// Si el usuario quiere visualizar un contacto
				} else if (operacion.equals("visualizar")) {
					System.out.print("Introduzca el nombre: ");
					nombre = scan.nextLine();
					os.write(nombre.getBytes());
					
				// Si el usuario quiere salir de la agenda
				} else if (operacion.equals("salir")) {
					salir = true;
				}
				// Recibe respuesta del servidor
				byte[] buffer = new byte[100];
				is.read(buffer);
				String respuesta = new String(buffer);
				respuesta = respuesta.trim();
				System.err.println(respuesta);
				System.out.println("---------------------------------------------------------------------------");
			}
			//Se cierra el socket
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("Terminado");			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}