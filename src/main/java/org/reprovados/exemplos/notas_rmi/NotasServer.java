package org.reprovados.exemplos.notas_rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class NotasServer {

	public static void main (String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java NotasServer <machine>");
			System.exit(1);
		}

		try {
			System.setProperty("java.rmi.server.hostname", args[0]);
			LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (RemoteException e) {
			System.out.println("RMI registry already running.");
		}
		try {
			String server = "rmi://" + args[0] + ":1099/Notas";
			
			Naming.rebind(server, new Notas());
			System.out.println ("NotasServer is ready.");
		} catch (Exception e) {
			System.out.println ("NotasServer failed:");
			e.printStackTrace();
		}
	}

}
