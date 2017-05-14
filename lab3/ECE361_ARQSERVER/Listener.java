import java.lang.Thread;
import java.io.*;
import java.net.*;

public class Listener implements Runnable{
    public Socket socket;
    public int curPacket;
    
    public Listener(Socket sock){
	socket = sock;
    }

    public void run()
    {
        System.out.println("Listener running");
	try{
	    DataInputStream din = new DataInputStream(socket.getInputStream());
	while(true)
	    {
		curPacket = din.readInt();
		GBNClient.setAckNum(curPacket);
	    }
	}
	catch(IOException e){
	    e.printStackTrace();
	}
						   
    }
}
