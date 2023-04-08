package org.reprovados.exemplos.add_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdditionInterface extends Remote {
	int Add(int a, int b) throws RemoteException;
}
