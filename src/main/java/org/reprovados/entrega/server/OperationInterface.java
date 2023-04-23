package org.reprovados.entrega.server;

import java.io.IOException;
import java.rmi.Remote;

// Cada operação deve ter um delay de 100ms
public interface OperationInterface extends Remote {

    // Escritas deverão são mutualmente exclusivas
    // Podem ocorrer em paralelo com leituras
    int write(int source) throws IOException;

    // Deleções devem ser mutualmente exclusivas
    // Não podem ocorrer em paralelo com nenhuma outra operação
    int delete(int source) throws IOException;

    // Podem ocorrer diversas leituras concorrentemente
    int read(int source) throws IOException;
}
