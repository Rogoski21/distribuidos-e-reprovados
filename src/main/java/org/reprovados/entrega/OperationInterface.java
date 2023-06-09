package org.reprovados.entrega;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

// Cada operação deve ter um delay de 100ms
public interface OperationInterface extends Remote {

    // Escritas deverão são mutualmente exclusivas
    // Podem ocorrer em paralelo com leituras
    void write() throws IOException;

    // Deleções devem ser mutualmente exclusivas
    // Não podem ocorrer em paralelo com nenhuma outra operação
    void delete() throws IOException;

    // Podem ocorrer diversas leituras concorrentemente
    void read() throws IOException;
}
