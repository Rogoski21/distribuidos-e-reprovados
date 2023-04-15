package org.reprovados.entrega;

import java.io.IOException;
import java.rmi.Naming;

public class Client2 {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Client <machine>");
            System.exit(1);
        }

        String remoteHostName = args[0];
        String connectLocation = "rmi://" + remoteHostName + ":1105/operation";

        OperationInterface operation = null;
        try {
            System.out.println("Connecting to: " + connectLocation);
            operation = (OperationInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        try {
            operation.delete();

            System.out.println("Client successfully written");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
