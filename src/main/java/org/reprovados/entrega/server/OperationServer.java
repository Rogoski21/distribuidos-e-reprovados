package org.reprovados.entrega.server;


import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class OperationServer {

    public static void main(String[] args) throws RemoteException {
        if (args.length != 1) {
            System.out.println("Usage: java Server <file lock machine address:port ex: '127.0.0.1:8000'>");
            System.exit(1);
        }

        // Getting local ip address; Not localhost
        String ip;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }

        boolean serverCreated = false;
        int port = 1100;
        String address = ip + ":" + port;

        while (!serverCreated) {
            try {
                address = ip + ":" + port;
                System.setProperty("java.rmi.server.hostname", ip);
                LocateRegistry.createRegistry(port);
                serverCreated = true;
                System.out.println("Operation Server java RMI registry created on address: " + address);
            } catch (RemoteException e) {
                port++;
                System.out.println("java RMI registry already exists.");
            }
        }

        try {
            String server = "rmi://" + address + "/operation";
            Naming.rebind(server, new OperationImpl(args[0]));
            System.out.println("Server started successfully.");
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }
}
