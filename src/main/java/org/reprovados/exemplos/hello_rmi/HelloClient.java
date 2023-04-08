package org.reprovados.exemplos.hello_rmi;

import java.rmi.Naming;

class HelloClient {
	// Programa cliente para o exemplo "Hello, world!"
	public static void main (String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java AdditionClient <machine>");
			System.exit(1);
		}

		String connectLocation = "rmi://" + args[0] + ":1099/Hello";

		try {
			HelloInterface hello = (HelloInterface) Naming.lookup (connectLocation);
			System.out.println (hello.say());
		} catch (Exception e) {
			System.out.println ("HelloClient failed:");
			e.printStackTrace();
		}
	}
}

