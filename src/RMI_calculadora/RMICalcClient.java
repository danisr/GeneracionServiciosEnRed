package RMI_calculadora;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICalcClient {
	public static void main(String[] args) {
		RMICalcInterface calc = null;
		try {
			System.out.println("Localizando el registro de objetos remitos");
			Registry registry = LocateRegistry.getRegistry("localhost", 5555);
			System.out.println("Obteniendo el stab del objeto remoto");
			calc = (RMICalcInterface) registry.lookup("Calculadora");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (calc != null) {
			System.out.println("Realizando operaciones con el objeto remoto");
			try {
				System.out.println("2 + 2 = " + calc.suma(2, 2));
				System.out.println("99 - 45 = " + calc.resta(99, 45));
				System.out.println("125 * 3 = " + calc.multip(125, 3));
				System.out.println("1250 / 5 = " + calc.div(1250, 5));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}