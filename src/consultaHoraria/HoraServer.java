package consultaHoraria;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Date;

public class HoraServer {
	public static void main(String[] args) {
		System.out.println("Arrancando servidor de hora.");

		DatagramSocket datagramSocket = null;

		try {
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			datagramSocket = new DatagramSocket(addr);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		while (datagramSocket != null) {
			try {
				System.out.println("Esperando mensaje");
				byte[] buffer = new byte[4];
				DatagramPacket datagrama1 = new DatagramPacket(buffer, 4);
				datagramSocket.receive(datagrama1);

				String mensaje = new String(datagrama1.getData());

				InetAddress clientAddr = datagrama1.getAddress();
				int clientPort = datagrama1.getPort();

				System.out.println("Mensaje recibido: desde " + clientAddr + ", puerto " + clientPort);
				System.out.println("Contenido del mensaje: " + mensaje);

				if (mensaje.equals("hora")) {
					System.out.println("Enviando respuesta");

					Date d = new Date(System.currentTimeMillis());
					byte[] respuesta = d.toString().getBytes();
					DatagramPacket datagrama2 = new DatagramPacket(respuesta, respuesta.length, clientAddr, clientPort);
					datagramSocket.send(datagrama2);

					System.out.println("Mensaje enviado");
				} else {
					System.out.println("Mensaje recibido no reconocido");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Terminado");
	}
}