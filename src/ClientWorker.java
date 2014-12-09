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
			byte[] b = new byte[500000];
			int count = connectionSocketConected.getInputStream().read(b);
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			DataInputStream inFromClient = new DataInputStream(
					connectionSocketConected.getInputStream());
			// Creates an object of the data which is to be send back to the
			// client, via the connectionSocket
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocketConected.getOutputStream());
			System.out.println("Outtoclient oprettet!");
			// Sets client sentence equals input from client
			// incomingJson = inFromClient.readLine();

			String ny = crypt.decrypt(b);

			// cryp.StringEncryption(inFromClient.readLine());
			System.out.println("Besked modtaget!");
			// Sysout recieved message
			System.out.println("Received: " + ny);
			String returnSvar = GS.GiantSwitchMethod(ny);
			// Sends the capitalized message back to client!!
			outToClient.writeBytes(returnSvar + "\n");
			System.out.println("svar sendt");
			
		} catch (Exception exception) {
			System.err.print(exception);
			exception.printStackTrace();
		}
	}

}
