package org.reprovados.exemplos.add_rmi_callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdditionInterfaceServer extends Remote {
	int Add(int a, int b, int port) throws RemoteException;
}
