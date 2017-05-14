import java.net.*;
import java.io.*;
import java.util.Scanner;
class SWClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Client online");
		try{
		    long pingStart;
		    long pingEnd;
		    Socket socket = new Socket("localhost",9876);
		    String message;
		    String serverMessage;
		    int numPackets = 0;
		    int sent = 1;
		    int curPacket;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));		
		    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
		    DataInputStream din = new DataInputStream(socket.getInputStream());
		    Scanner scan = new Scanner(System.in);
		    System.out.println("Please enter the number of packets to send to the server:");
		    numPackets = scan.nextInt();
		    writer.writeInt(numPackets);
		    
		    while(sent<=numPackets){
			writer.writeInt(sent);
			curPacket = din.readInt();
			System.out.println("Receiving packet " + curPacket);
			if(curPacket == sent)
			    {
				System.out.println("Packet " + sent + " acknowledged");
				sent++;
			    }
		    }
		    System.out.println("Transfer complete.");
		    socket.close();
		}catch(Exception e){e.getStackTrace();}
	}
}
