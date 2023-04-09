package org.reprovados.entrega;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class Operation extends UnicastRemoteObject implements OperationInterface {

    private static final long serialVersionUID = -4350924532429707767L;

    public Operation() throws RemoteException {
        System.out.println("Operation created successfully");
    }

    @Override
    public void write() throws IOException {
        saveLog("write");
    }

    @Override
    public void delete() throws IOException {
        saveLog("delete");
    }

    @Override
    public void read() throws IOException {
        saveLog("read");
    }

    private void saveLog(String operation) throws IOException {
        //Salva um log descrevendo a operação com linha em arquivo
        BufferedWriter outStream = new BufferedWriter(new FileWriter("src/main/java/org/reprovados/entrega/logs.txt", true));

        LocalDateTime localDateTime = LocalDateTime.now();

        outStream.write("(" + localDateTime + ") " + "Operation " + operation + " occurred successfully\n ");

        outStream.close();

        System.out.println("Successfully saved");
    }

}
