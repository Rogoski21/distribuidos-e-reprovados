package org.reprovados.exemplos.add_rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class AdditionClient {
	public static void main(String[] args) {
		int result;

		if (args.length != 1) {
			System.out.println("Usage: java AdditionClient <machine>");
			System.exit(1);
		}

		String connectLocation = "rmi://" + args[0] + ":52369/Hello";

		AdditionInterface hello = null;
		try {
			System.out.println("Connecting to client at : " + connectLocation);
			hello = (AdditionInterface) Naming.lookup(connectLocation);
		} catch (Exception e) {
			System.out.println ("AdditionClient failed: ");
			e.printStackTrace();
		}

		try {
			result = hello.Add(9, 10);
			System.out.println("Result is: " + result);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
