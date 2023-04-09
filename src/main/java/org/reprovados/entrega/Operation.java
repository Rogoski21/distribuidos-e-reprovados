package org.reprovados.entrega;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Operation extends UnicastRemoteObject implements OperationInterface {

    private static final long serialVersionUID = -4350924532429707767L;

    public Operation() throws RemoteException {
    }

    @Override
    public void write() throws RemoteException {

    }

    @Override
    public void delete() throws RemoteException {

    }

    @Override
    public void read() throws RemoteException {

    }
}
