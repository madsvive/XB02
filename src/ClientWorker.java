import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientWorker implements Runnable {
	private Socket connectionSocketConected;
	private GiantSwitch GS = new GiantSwitch();
	private encryption crypt = new encryption();

	ClientWorker(Socket connectionSocket) {
		this.connectionSocketConected = connectionSocket;
	}

	public void run() {
		try {
			System.out.println("forbindelse Oprettet!");
			// BufferedReader inFromClient = new BufferedReader(new
			// InputStreamReader(connectionSocket.getInputStream()));

			byte[] b = new byte[500000];
			int count = connectionSocketConected.getInputStream().read(b);
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			ObjectInputStream inFromClient = new ObjectInputStream(connectionSocketConected.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocketConected.getOutputStream());

//			String ny = crypt.decrypt(b);
			System.out.println(b);
			String ny = b.toString();
			System.out.println("Received: " + ny);
			String returnSvar = GS.GiantSwitchMethod(ny);
			// Sends the capitalized message back to client!!
			outToClient.writeBytes(returnSvar + "\n");
			System.out.println("svar sendt");
			// BufferedWriter writer = new BufferedWriter(arg0)
		} catch (Exception exception) {
			System.err.print(exception);
			exception.printStackTrace();
		}
	}

}
