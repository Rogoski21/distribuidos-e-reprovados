import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConvertInterface extends Remote {
	public String IntToHexString(int val) throws RemoteException;
	public int HexStringToInt(String val) throws RemoteException;
}
