import java.net.*;
import java.io.*;
class Lab1_echoclient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Server online");
		try{
		    ServerSocket socketListener = new ServerSocket(9876);
		    Socket socket = socketListener.accept();
		    System.out.println("got socket on port");
		    String message;
		    String serverMessage;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));		
		    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
		    message = "connected to Zach Saltzman's echo client\r\n";
		    writer.writeBytes(message);
		    while(true){
			if(reader.ready())
			    {
				message = reader.readLine();
				serverMessage = message + "\r\n";
				if(message.equalsIgnoreCase("quit"))
				    System.out.println("client closed connection");
				else
				    {
					System.out.println("sending: " + message);
					writer.writeBytes(serverMessage);
				    }
			    }	
		    }
		}catch(Exception e){e.getStackTrace();}
	}
}
