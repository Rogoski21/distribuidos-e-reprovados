package org.reprovados.entrega;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class FileLock extends UnicastRemoteObject implements FileLockInterface {
    private boolean isDeleting;
    private boolean isWriting;
    private boolean isReading;

    public FileLock() throws RemoteException {
        System.out.println("File Lock created successfully");
    }

    @Override
    public synchronized void write() throws IOException {
        try {
            while(isDeleting) {
                wait();
            }
            isWriting = true;
            Thread.sleep(100);
            saveLog("Write Initiated");
            Thread.sleep(200);
            saveLog("Write Finished");
            isWriting = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void delete() throws IOException {
        try {
            while(isWriting || isReading) {
                wait();
            }
            isDeleting = true;
            Thread.sleep(100);
            saveLog("Delete Initiated");
            Thread.sleep(10000);
            saveLog("Delete Finished");
            notifyAll();
            isDeleting = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void read() throws IOException {
        try {
            while(isDeleting) {
                wait();
            }
            isReading = true;
            Thread.sleep(100);
            saveLog("Read Initiated");
            Thread.sleep(15000);
            saveLog("Read Finished");
            notifyAll();
            isReading = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
