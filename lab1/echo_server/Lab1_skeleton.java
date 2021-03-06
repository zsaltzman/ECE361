import java.net.*;
import java.io.*;
class Lab1_skeleton {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Client online");
		try{
		    long pingStart;
		    long pingEnd;
		    Socket socket = new Socket("localhost",9876);
		    String message;
		    String serverMessage;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    message = reader.readLine();
		    System.out.println(message);
		    BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));			
		    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
		    while(true){
			
			message = reader2.readLine();
			pingStart = System.currentTimeMillis();
			serverMessage = message + "\r\n";
			if (message.equalsIgnoreCase("quit"))
			    break;
			writer.writeBytes(serverMessage);
			message = reader.readLine();
			pingEnd = System.currentTimeMillis();
			System.out.println("response from server: " + message);
			System.out.println(pingEnd-pingStart);
		    }
		    System.out.println("connection closed");
		    socket.close();
		}catch(Exception e){e.getStackTrace();}
	}
}
