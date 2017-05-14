import java.net.*;
import java.io.*;
import java.util.*;

class Lab4_ARQClient{

    private static int acknum = 1;
 
    public static void main(String[] args) throws UnknownHostException, IOException
    {
	System.out.println("Starting ARQ Client.");
	System.out.println("Attempting to connect to server...");
	Socket socket = new Socket("localhost",9876);
	System.out.println("Success!");

	Scanner scan = new Scanner(System.in);
	System.out.println("Please enter a number of packets to send.");
	int NoPackets = scan.nextInt();

	DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
	writer.write(NoPackets);

	//TCP Control Variables
	int cwnd = 1;
	int ssthresh = 10;
	long timeout_period = 1400; //milliseconds
	long start_timeout;
	long end_timeout;
	int expected_acknum;
	Thread thread = new Thread(new Listener(socket,NoPackets));
	thread.start();

	while(acknum<=NoPackets)
	    {
		int packets_out = 0;
		start_timeout = System.currentTimeMillis();
		for (int i = 0; i<cwnd; i++)
			writer.write(acknum+i); //put out the next packet

		expected_acknum = acknum+cwnd;
		end_timeout = System.currentTimeMillis();
		while (end_timeout-start_timeout < timeout_period*cwnd && acknum < expected_acknum)
		    {
			end_timeout = System.currentTimeMillis();
		    } //wait for reply from server
		
		packets_out = 0;
		//we timed out
		if (end_timeout-start_timeout >= timeout_period)
		    {
			System.out.println("timeout reached, lowering threshold and setting cwnd");
			ssthresh = cwnd/2;
			cwnd = 1;
		    }
		else //we good
		    {
			System.out.println("received " + cwnd + " packets successfully.");
			if (cwnd < ssthresh)
			    {
				System.out.println("doubling cwnd");
				cwnd = cwnd*2;
			    }
			else
			    {
				System.out.println("increasing cwnd by 1");
				cwnd += 1;
			    }
		    }
	    }
	System.out.println("transfer complete. closing connection");
	socket.close();
	
    }

    static int getAckNum()
    {
	return acknum;
    }
    static void setAckNum(int set_val)
    {
	if(set_val == acknum)
	    {
		System.out.println("Acknowledgement " + set_val + " received.");
		acknum = set_val+1;
	    }
    }

}

class Listener implements Runnable{
    public Socket socket;
    public int curPacket;
    public int NoPackets;
    
    public Listener(Socket sock, int numPackets){
	socket = sock;
	NoPackets = numPackets;
    }

    public void run()
    {
	System.out.println("Listener running");
	try{
	    DataInputStream din = new DataInputStream(socket.getInputStream());
	    while(Lab4_ARQClient.getAckNum()<=NoPackets)
		{
		    curPacket = din.readByte();
		    Lab4_ARQClient.setAckNum(curPacket);
		}
	}
	catch(IOException e){
	    e.printStackTrace();
	}

    }
}
