package org.reprovados.entrega;

import org.reprovados.exemplos.convert_rmi.Convert;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws RemoteException {
        if (args.length != 1) {
            System.out.println("Usage: java Server <machine address ex: 'localhost'>");
            System.exit(1);
        }

        try {
            System.setProperty("java.rmi.server.hostname", args[0]);
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        try {
            String server = "rmi://" + args[0] + ":1099/operation";
            Naming.rebind(server, new Operation());
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }
}
