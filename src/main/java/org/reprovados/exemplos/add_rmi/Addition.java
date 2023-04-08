import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Addition extends UnicastRemoteObject implements AdditionInterface {
	private static final long serialVersionUID = 1L;

	public Addition() throws RemoteException {
	}

	public int Add(int a, int b) {
		return a + b;
	}
}
