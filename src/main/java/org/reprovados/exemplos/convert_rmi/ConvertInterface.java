package org.reprovados.exemplos.convert_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConvertInterface extends Remote {
	String IntToHexString(int val) throws RemoteException;
	int HexStringToInt(String val) throws RemoteException;
}
