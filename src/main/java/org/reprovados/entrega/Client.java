package org.reprovados.entrega;

import org.reprovados.entrega.OperationInterface;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;

public class Client {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Client <machine>");
            System.exit(1);
        }

        String remoteHostName = args[0];
        String connectLocation = "rmi://" + remoteHostName + ":1104/operation";

        OperationInterface operation = null;
        try {
            System.out.println("Connecting to: " + connectLocation);
            operation = (OperationInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        try {
            operation.read();

            System.out.println("Client successfully written");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
