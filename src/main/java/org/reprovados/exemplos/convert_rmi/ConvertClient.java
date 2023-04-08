import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;

public class ConvertClient {
	public static void main(String[] args) {
		String result_str;
		int result_int;

		if (args.length != 1) {
			System.out.println("Usage: java ConvertClient <machine>");
			System.exit(1);
		}

		String remoteHostName = args[0];
		String connectLocation = "rmi://" + remoteHostName + ":1099/convert";

		ConvertInterface convert = null;
		try {
			System.out.println("Connecting to: " + connectLocation);
			convert = (ConvertInterface) Naming.lookup(connectLocation);
		} catch (Exception e) {
			System.out.println ("ConvertClient failed: ");
			e.printStackTrace();
		}

		try {
			Random rnd = new Random();
			int val;
			
			for (int i = 0; i < 10000; i++) {
				val = rnd.nextInt() & 0x7fffffff;
				result_str = convert.IntToHexString(val);
				result_int = convert.HexStringToInt(result_str);
				System.out.println("Val (Int): " + val + ", Res (Hex Str): " + result_str + ", Res (Int): " + result_int);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
