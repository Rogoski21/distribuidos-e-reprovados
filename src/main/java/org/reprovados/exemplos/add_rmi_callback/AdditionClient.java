package org.reprovados.exemplos.add_rmi_callback;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class AdditionClient extends UnicastRemoteObject implements AdditionInterfaceClient {
	private static volatile int i, j;

	public AdditionClient() throws RemoteException {
	}

	public static void main(String[] args) {
		int result;

		if (args.length != 3) {
			System.out.println("Usage: java AdditionClient <server ip> <client ip> <client port>");
			System.exit(1);
		}
	
		try {
			System.setProperty("java.rmi.server.hostname", args[1]);
			LocateRegistry.createRegistry(Integer.parseInt(args[2]));
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}

		try {
			String client = "rmi://" + args[1] + ":" + args[2] + "/Hello2";
			Naming.rebind(client, new AdditionClient());
			System.out.println("Addition Server is ready.");
		} catch (Exception e) {
			System.out.println("Addition Serverfailed: " + e);
		}

		String remoteHostName = args[0];
		String connectLocation = "rmi://" + remoteHostName + ":52369/Hello";

		AdditionInterfaceServer hello = null;
		try {
			System.out.println("Connecting to server at : " + connectLocation);
			hello = (AdditionInterfaceServer) Naming.lookup(connectLocation);
		} catch (Exception e) {
			System.out.println ("AdditionClient failed: ");
			e.printStackTrace();
		}

		i = 1; j = 2;

		while (true) {
			try {
				hello.Add(i++, j++, Integer.parseInt(args[2]));
				System.out.println("Call to server..." );
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {}
		}
	}
	
	public int Result(int val) {
		System.out.println("Called back, result is: " + val);
		
		return val;
	}
}
