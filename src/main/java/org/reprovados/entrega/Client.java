package org.reprovados.entrega;

import org.reprovados.entrega.OperationInterface;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Client {

    public static List<String> serverAddress = Arrays.asList("localhost:1099", "localhost:1100", "localhost:1101", "localhost:1102", "localhost:1103");
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Client <machine>");
            System.exit(1);
        }

        Random random = new Random();
        String randomElement = serverAddress.get(random.nextInt(serverAddress.size()));
        System.out.println(randomElement);

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
                    operation.delete("Client 1");
                    System.out.println("Client successfully deleted");
                } else if (randomNumber <= 50) {
                    operation.write("Client 1");
                    System.out.println("Client successfully writed");
                } else {
                    operation.read("Client 1");
                    System.out.println("Client successfully readed");
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
