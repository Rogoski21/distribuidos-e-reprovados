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
    private int readingOperations = 0;

    public FileLock() throws RemoteException {
        System.out.println("File Lock created successfully");
    }

    @Override
    public void write(String source) throws IOException {
        try {
            while(isDeleting || isWriting) {
                Thread.sleep(100);
            }
            isWriting = true;
            Thread.sleep(100);
            saveLog("Write Initiated", source);
            Thread.sleep(3000);
            saveLog("Write Finished", source);
            isWriting = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String source) throws IOException {
        try {
            while(readingOperations > 0 || isDeleting || isWriting) {
                Thread.sleep(100);
            }
            isDeleting = true;
            Thread.sleep(100);
            saveLog("Delete Initiated", source);
            Thread.sleep(2000);
            saveLog("Delete Finished", source);
            isDeleting = false;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(String source) throws IOException {
        try {
            while(isDeleting) {
                Thread.sleep(100);
            }
            readingOperations++;
            Thread.sleep(100);
            saveLog("Read Initiated", source);
            Thread.sleep(100);
            saveLog("Read Finished", source);
            readingOperations--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveLog(String operation, String source) throws IOException {
        //Salva um log descrevendo a operação com linha em arquivo
        BufferedWriter outStream = new BufferedWriter(new FileWriter("src/main/java/org/reprovados/entrega/logs.txt", true));

        LocalDateTime localDateTime = LocalDateTime.now();

        outStream.write("["+ source + "]" +"(" + localDateTime + ") " + "Operation " + operation + " occurred successfully\n ");

        outStream.close();

        System.out.println("Successfully saved");
    }
}
