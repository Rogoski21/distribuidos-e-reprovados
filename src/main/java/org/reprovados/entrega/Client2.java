package org.reprovados.entrega;

import java.io.IOException;
import java.rmi.Naming;
import java.util.Random;

public class Client2 {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Client <machine>");
            System.exit(1);
        }

        Random random = new Random();
        String remoteHostName = args[0];
        String connectLocation = "rmi://" + remoteHostName + ":1099/operation";

        OperationInterface operation = null;
        try {
            System.out.println("Connecting to: " + connectLocation);
            operation = (OperationInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        try {
            int counter = 0;

            while (counter < 20) {
                int randomNumber = random.nextInt(100);

                if (randomNumber <= 10) {
                    operation.delete("Client 2");
                    System.out.println("Client successfully deleted");
                } else if (randomNumber <= 50) {
                    operation.write("Client 2");
                    System.out.println("Client successfully writed");
                } else {
                    operation.read("Client 2");
                    System.out.println("Client successfully readed");
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
