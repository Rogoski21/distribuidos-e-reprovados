package org.reprovados.exemplos.add_rmi_callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdditionInterfaceClient extends Remote {
	int Result(int val) throws RemoteException;
}
