package org.reprovados.exemplos.hello_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface remota para o exemplo "Hello, world!"
public interface HelloInterface extends Remote {
	// Metodo invocavel remotamente que retorna a mensagem do objeto remoto
	String say() throws RemoteException;
}

