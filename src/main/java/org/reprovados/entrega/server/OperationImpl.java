package org.reprovados.entrega.server;

import org.reprovados.entrega.file_lock.FileLockInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OperationImpl extends UnicastRemoteObject implements OperationInterface {

    private static final long serialVersionUID = -4350924532429707767L;

    private FileLockInterface fileServer;

    public OperationImpl(String address) throws RemoteException {
        try {
            String uri = "rmi://" + address + "/file";
            System.out.println("Connecting to: " + uri);
            fileServer = (FileLockInterface) Naming.lookup(uri);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }
    }

    @Override
    public int write(int sourceId){

        try {
            return fileServer.write(sourceId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(int sourceId) {
        try {
            return fileServer.delete(sourceId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int read(int sourceId) {
        try {
            return fileServer.read(sourceId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
