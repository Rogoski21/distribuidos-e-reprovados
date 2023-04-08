package org.reprovados.exemplos.convert_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Convert extends UnicastRemoteObject implements ConvertInterface {
	public Convert() throws RemoteException {
		System.out.println("Convert object created.");
	}

	public String IntToHexString(int val) {
		return Integer.toHexString(val);
	}
	
	public int HexStringToInt(String val) {
		return Integer.parseInt(val, 16);
	}
}
