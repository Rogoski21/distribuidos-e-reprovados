package org.reprovados.entrega;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) throws RemoteException {
        if (args.length != 1) {
            System.out.println("Usage: java Server <machine address ex: 'localhost'>");
            System.exit(1);
        }
        boolean serverCreated = false;
        int serverPort = 1099;

        while (!serverCreated) {
            try {
                System.setProperty("java.rmi.server.hostname", args[0]);
                LocateRegistry.createRegistry(serverPort);
                serverCreated = true;
                System.out.println("java RMI registry created.");
            } catch (RemoteException e) {
                serverPort++;
                System.out.println("java RMI registry already exists.");
            }
        }

        try {
            String server = "rmi://" + args[0] + ":" + serverPort + "/operation";
            Naming.rebind(server, new Operation());
            System.out.println("Server is ready on the port: " + serverPort);
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }
}
