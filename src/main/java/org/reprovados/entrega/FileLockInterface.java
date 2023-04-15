package org.reprovados.entrega;

import java.io.IOException;
import java.rmi.Remote;

public interface FileLockInterface extends Remote {
    void write(String source) throws IOException;
    void delete(String source) throws IOException;
    void read(String source) throws IOException;
}
