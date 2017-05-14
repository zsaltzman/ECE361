import java.net.*;
import java.io.*;
class Lab2_FTPServer{
    
    public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		try{
		    System.out.println("FTP Server started.");
		    ServerSocket csocketListener = new ServerSocket(9876);
		    ServerSocket dsocketListener = new ServerSocket(11111);
		    Socket controlSocket;
		    Socket dataSocket;
		    String message = "Control connection open, please specify a file";
		    String fileName = "";
		    File file;
		    byte[] fileData = new byte[1024];
		    for (int i = 0;i<1024;i++)
			fileData[i] = 0;
		    BufferedReader reader;		
		    DataOutputStream writer;
		    FileInputStream fin;
		    DataOutputStream dataWriter;
		    int bytes_read = 1024;
		    int byte_offset = 0;
		    while(true){
			controlSocket = csocketListener.accept();
			System.out.println("Acquired control socket");
			reader = new BufferedReader(new InputStreamReader(controlSocket.getInputStream()));
			writer = new DataOutputStream(controlSocket.getOutputStream());
			fileName = reader.readLine();
			System.out.println("file name: " + fileName);
			file = new File(fileName);
			if (file.exists())
			    {
				fin = new FileInputStream(file);
				System.out.println("File is valid, transfer set for port 11111");
				writer.writeInt(11111);
				dataSocket = dsocketListener.accept();
				dataWriter = new DataOutputStream(dataSocket.getOutputStream());
				System.out.println("Data socket connected, beginning transfer...");
				while (bytes_read >= 1024)
				    {
					System.out.println("Sending packet.");	
					bytes_read = fin.read(fileData,0,1024);	
					System.out.println("bytes read: " + bytes_read);
					dataWriter.write(fileData, 0,fileData.length);
				        dataWriter.writeInt(bytes_read);
				    }			
				System.out.println("Transfer complete.");
				dataSocket.close();
				fin.close();
			    }
			else
			    System.out.println("Error: file not found");
	 
			System.out.println("Closing connection with client.");
			controlSocket.close();
			bytes_read = 1024;
		    }
		}catch(Exception e){
		    System.out.println("" + e.getCause());
		    e.getStackTrace();
		}
	}
}
