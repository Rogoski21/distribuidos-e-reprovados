package org.reprovados.exemplos.notas_rmi;

import java.rmi.Naming;

public class NotasClient {
	public static void main (String[] args) {
		double n;

		if	(args.length != 2)  {
			System.out.println("Uso: java NotasClient <maquina> <nome>");
			System.exit(1);
		}
		
		String connectLocation = "rmi://" + args[0] + ":1099/Notas";
		
		try {
			NotasInterface nota = (NotasInterface) Naming.lookup(connectLocation);
			n = nota.obtemNota(args[1]);
			System.out.println ("Nome: "+args[1]);
			if	(n<-1.0)
				System.out.println ("Resultado: nome nao encontrado!\n");
			else
				System.out.println ("Nota: "+n);
		} catch (Exception e) {
			System.out.println ("NotasClient failed.");
			e.printStackTrace();
		}
	}
}

