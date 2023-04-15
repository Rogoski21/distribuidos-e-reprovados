package org.reprovados.entrega;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class FileServer {
    public static void main(String[] args) throws RemoteException {
        if (args.length != 1) {
            System.out.println("Usage: java FileServer <machine address ex: 'localhost'>");
            System.exit(1);
        }

        try {
            System.setProperty("java.rmi.server.hostname", args[0]);
            LocateRegistry.createRegistry(8000);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        try {
            String server = "rmi://" + args[0] + ":8000/file";
            Naming.rebind(server, new FileLock());
            System.out.println("File Server is ready.");
        } catch (Exception e) {
            System.out.println("File Server failed: " + e);
        }
    }
}
