import java.net.*;
import java.io.*;
class Lab2_FTPClient{
    
    public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		try{
		    System.out.println("FTP Client started.");
		    System.out.println("Attempting to connect to server...");
		    long transferStart = 0;
		    long transferEnd = 0;
		    Socket socket = new Socket("localhost",9876);
		    Socket dSocket;
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
		    System.out.println("Please specify a file name.");
		    fileName = reader2.readLine();
		    fileSrc = new File(fileName);
		    fileName = fileName + "\r\n";
		    writer.writeBytes(fileName);
		    if(fileSrc.exists())
			{
			   
			    portNumber = bin.readInt();
			    System.out.println("port number: " + portNumber);
			    dSocket = new Socket("localhost",portNumber);
			    din = new DataInputStream(dSocket.getInputStream());
			    transferStart = System.currentTimeMillis();
			    while(bytes_read == 1024)
				{
				    System.out.println("Got data from server");
				    din.read(fileData,0,1024);
				    bytes_read = din.readInt();
				    System.out.println("bytes read: " + bytes_read);
				    fout.write(fileData,0,bytes_read);
				   
				    byte_offset += bytes_read;
				}
			    din.close();
			}
		    System.out.println("Closing connection with client.");
		    message = "Connection terminated.";
		    transferEnd = System.currentTimeMillis();
		    System.out.println("file length: " + fileDest.length() + " transfer time: " + (transferEnd-transferStart));
		    fout.close();
		    socket.close();
			}
		}catch(Exception e){
		    System.out.println("" + e.getCause());
		    e.getStackTrace();
			}
	}
}
