import java.net.*;
import java.io.*;
class GBNServer {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Server online");
		try{
		    int lastAck = 0;
		    int numPackets;
		    int curPacket;
		    ServerSocket socketListener = new ServerSocket(9876);
		    Socket socket = socketListener.accept();
		    System.out.println("got socket on port");
		    String message;
		    String serverMessage;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    
		    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
		    DataInputStream din = new DataInputStream(socket.getInputStream());
		    numPackets = din.readInt();
		    System.out.println(numPackets);
		    while(lastAck<=numPackets){
			curPacket = din.readInt();
			if (curPacket == lastAck+1)
			    {
				System.out.println("Acknowledging packet " + curPacket);
				lastAck++;
			    }
			writer.writeInt(curPacket);
		    }
		}catch(Exception e){e.getStackTrace();}
	}
}
