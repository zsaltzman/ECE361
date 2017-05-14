import java.net.*;
import java.io.*;
class Lab1_skeleton {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		try{		
			Socket socket = new Socket("localhost",9876);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			reader.readLine();
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
			String message = reader2.readLine();
			
			socket.close();
		}catch(Exception e){e.getStackTrace();}
	}
}
