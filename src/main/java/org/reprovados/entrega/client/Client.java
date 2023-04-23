package org.reprovados.entrega.client;

import org.reprovados.entrega.server.OperationInterface;

import java.io.IOException;
import java.rmi.Naming;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Client {

    public static List<String> serverAddress = Arrays.asList(
            "192.168.15.59:1100",
            "192.168.15.59:1101",
            "192.168.15.59:1102");

    public static Random random = new Random();

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Usage: java Client <number of request> <delete rate> <write rate>. EX: '20 10 30' for 20 requests, 10% deletion, 30% write, 40% read");
            System.exit(1);
        }

        int clientId = random.nextInt(100);
        System.out.println("Started client with id: " + clientId);

        try {
            int counter = 0;

            while (counter < Integer.parseInt(args[0])) {
                int randomNumber = random.nextInt(100);

                if (randomNumber <= Integer.parseInt(args[1])) {
                    int operationResult = getNextOperationLocation().delete(clientId);
                    if (operationResult == 1) {
                        System.out.println("Client successfully deleted");
                    } else {
                        System.out.println("Error to Delete");
                    }
                } else if (randomNumber <= Integer.parseInt(args[2])) {
                    int operationResult = getNextOperationLocation().write(clientId);
                    if (operationResult == 1) {
                        System.out.println("Client successfully written");
                    } else {
                        System.out.println("Error to Write");
                    }
                } else {
                    int operationResult = getNextOperationLocation().read(clientId);
                    if (operationResult == 1) {
                        System.out.println("Client successfully read");
                    } else {
                        System.out.println("Error to Read");
                    }
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished client with id: " + clientId);
    }

    private static OperationInterface getNextOperationLocation() {

        String randomElement = serverAddress.get(random.nextInt(serverAddress.size()));

        String connectLocation = "rmi://" + randomElement + "/operation";

        try {
            System.out.println("Connecting to: " + randomElement);
            return (OperationInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        return null;
    }
}
