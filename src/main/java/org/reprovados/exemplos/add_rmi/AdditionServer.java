import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AdditionServer {
	public static void main(String[] args) throws RemoteException {
		if (args.length != 1) {
			System.out.println("Usage: java AdditionServer <machine>");
			System.exit(1);
		}

		try {
			System.setProperty("java.rmi.server.hostname", args[0]);
			LocateRegistry.createRegistry(52369);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}

		try {
			String server = "rmi://" + args[0] + ":52369/Hello";
			Naming.rebind(server, new Addition());
			System.out.println("Addition Server is ready.");
		} catch (Exception e) {
			System.out.println("Addition Serverfailed: " + e);
		}
	}
}
