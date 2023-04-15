package org.reprovados.entrega;

import java.io.IOException;
import java.rmi.Remote;

public interface FileLockInterface extends Remote {
    void write() throws IOException;
    void delete() throws IOException;
    void read() throws IOException;
}
