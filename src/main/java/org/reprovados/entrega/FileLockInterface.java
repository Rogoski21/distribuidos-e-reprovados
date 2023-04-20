package org.reprovados.entrega;

import java.io.IOException;
import java.rmi.Remote;

public interface FileLockInterface extends Remote {
    int write(String source) throws IOException;
    int delete(String source) throws IOException;
    int read(String source) throws IOException;
}
