package org.reprovados.entrega.file_lock;

import java.io.IOException;
import java.rmi.Remote;

public interface FileLockInterface extends Remote {
    int write(int sourceId) throws IOException;
    int delete(int sourceId) throws IOException;
    int read(int sourceId) throws IOException;
}
