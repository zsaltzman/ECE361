import java.net.*;
import java.io.*;
class FTP_Client{
    
    public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		try{
		    System.out.println("FTP Client started.");
		    System.out.println("Attempting to connect to server...");
		    long transferStart = 0;
		    long transferEnd = 0;
		    Socket socket = new Socket("localhost",21); //well-known FTP control port
		    System.out.println("Connected.");
		    String fileName = "";
		    String message = "";
		    int portNumber = 0;
		    File fileSrc;
		    File fileDest = new File("ClientDestination.txt");
		    if (fileDest.exists())
			System.out.println("Destination file exists within scope.");
		    byte[] fileData = new byte[1024];
		    for (int i = 0;i<1024;i++)
			fileData[i] = 0;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
		    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
		    DataInputStream din;
		    DataInputStream bin = new DataInputStream(socket.getInputStream());
		    FileOutputStream fout = new FileOutputStream(fileDest);
		    int bytes_read = 1024;
		    int byte_offset = 0;
		    System.out.println("Please enter a command.");
		    fileName = reader2.readLine();
		    message = message + "\r\n";
		    writer.writeBytes(message);
		   
		   
		    socket.close();
			
		}catch(Exception e){
		    System.out.println("" + e.getCause());
		    e.getStackTrace();
			}
	}
}
