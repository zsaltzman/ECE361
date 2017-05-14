import java.net.*;
import java.io.*;
import java.util.Scanner;
class GBNClient {
    public static int lastAck = 0;
    public static void setAckNum(int ackNum){
	if(ackNum > lastAck)
	    lastAck = ackNum;
    }
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
		    int probError = 0;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));		
		    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
		    DataInputStream din = new DataInputStream(socket.getInputStream());
		    Scanner scan = new Scanner(System.in);
		    Thread thread = new Thread(new Listener(socket));
		    thread.start();
		    System.out.println("Please enter the number of packets to send to the server:");
		    numPackets = scan.nextInt();
		    writer.writeInt(numPackets);
		    
		    System.out.println("Please enter the probability of error (between 0-100)");
		    probError = scan.nextInt();
		    writer.writeInt(probError);

		    while(numPackets <= sent)
			{
			    writer.writeInt(sent);
			    sent++;
			}
		  
		    System.out.println("Transfer complete.");
		    socket.close();
		}catch(Exception e){e.getStackTrace();}
	}
}
