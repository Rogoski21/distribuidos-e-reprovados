package org.reprovados.entrega.file_lock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

public class FileLockImpl extends UnicastRemoteObject implements FileLockInterface {
    private boolean isDeleting;
    private boolean isWriting;
    private int readingOperations = 0;

    public FileLockImpl() throws RemoteException {
    }

    @Override
    public int write(int sourceId) throws IOException {

        try {
            int counter = 1;
            while(isDeleting || isWriting) {
                if (counter == 10) {
                    System.out.println("Exhausted tried. Giving up.");
                    return -1;
                }
                Thread.sleep(100);
                counter++;
            }

            isWriting = true;
            saveLog("Write Initiated", sourceId);
            Thread.sleep(100);
            saveLog("Write Finished", sourceId);
            isWriting = false;
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(int sourceId) throws IOException {
        try {
            int counter = 1;
            while(readingOperations > 0 || isDeleting || isWriting) {
                if(counter == 10) {
                    return -1;
                }
                counter ++;
                Thread.sleep(100);
            }
            isDeleting = true;
            saveLog("Delete Initiated", sourceId);
            Thread.sleep(100);
            saveLog("Delete Finished", sourceId);
            isDeleting = false;
            return 1;

        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int read(int sourceId) throws IOException {
        try {
            int counter = 1;
            while(isDeleting) {
                if(counter == 10) {
                    return -1;
                }
                counter++;
                Thread.sleep(100);
            }
            readingOperations++;
            saveLog("Read Initiated", sourceId);
            Thread.sleep(100);
            saveLog("Read Finished", sourceId);
            readingOperations--;
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void saveLog(String operation, int sourceId) throws IOException {
        //Salva um log descrevendo a operação com linha em arquivo
        BufferedWriter outStream = new BufferedWriter(new FileWriter("src/main/java/org/reprovados/entrega/file_lock/logs.txt", true));

        var localTime = LocalTime.now();

        outStream.write("Client "+ sourceId + " - " +"(" + localTime + ") " + "Operation " + operation + " occurred successfully\n ");

        outStream.close();
    }
}
