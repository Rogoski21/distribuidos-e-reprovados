package org.reprovados.entrega;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class Operation extends UnicastRemoteObject implements OperationInterface {

    private static final long serialVersionUID = -4350924532429707767L;

    public Operation() throws RemoteException {
        System.out.println("Operation created successfully");
    }

    @Override
    public int write(String source) throws IOException {
        String connectLocation = "rmi://" + "localhost" + ":8000/file";
        FileLockInterface fileLockInterface = null;
        try {
            System.out.println("Connecting to: " + connectLocation);
            fileLockInterface = (FileLockInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        try {
            return fileLockInterface.write(source);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(String source) throws IOException {
        String connectLocation = "rmi://" + "localhost" + ":8000/file";
        FileLockInterface fileLockInterface = null;
        try {
            System.out.println("Connecting to: " + connectLocation);
            fileLockInterface = (FileLockInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        try {
            return fileLockInterface.delete(source);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int read(String source) throws IOException {
        String connectLocation = "rmi://" + "localhost" + ":8000/file";
        FileLockInterface fileLockInterface = null;
        try {
            System.out.println("Connecting to: " + connectLocation);
            fileLockInterface = (FileLockInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        try {
            return fileLockInterface.read(source);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
