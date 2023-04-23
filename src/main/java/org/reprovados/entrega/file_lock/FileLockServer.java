package org.reprovados.entrega.file_lock;

import java.net.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class FileLockServer {
    public static void main(String[] args) throws RemoteException, UnknownHostException {

        // Getting local ip address; Not localhost
        String ip;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        var port = 8000;
        var address = ip + ":" + port;

        try {
            System.setProperty("java.rmi.server.hostname", ip);
            LocateRegistry.createRegistry(port);
            System.out.println("FileLock Server RMI registry created on " + address);
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        try {
            String server = "rmi://" + address + "/file";
            Naming.rebind(server, new FileLockImpl());
            System.out.println("FileLock Server is ready.");
        } catch (Exception e) {
            System.out.println("FileLock Server failed: " + e);
        }
    }
}
