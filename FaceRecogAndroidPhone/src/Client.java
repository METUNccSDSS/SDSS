import java.io.*;
import java.net.*;
import org.apache.commons.io.FileUtils;


public class Client
{

	String newLine = System.getProperty("line.separator");
	
	public static int value;
	public static String satus="Not Provided";
	public static String order="Not Provided";
    private static Boolean controller = true;
    public static int sendResult = 0;
    public static double result;
	// IO streams
	public static DataOutputStream toServer;
	public static DataInputStream fromServer;
	public static DataOutputStream toServer2;
	public static DataInputStream fromServer2;
	private static boolean User=false;
	public static void main(String[] args) throws IOException 
	{
		
		do
		{
		//User=true;
		controller=true;
		String newLine = System.getProperty("line.separator");
		Socket socket=null;
		Socket socket2=null;

	    FileUtils.deleteQuietly(new File("C:/Users/alica/Desktop/FromServer/"));
		/*try 
	    {
	    //FileUtils.deleteDirectory(new File("C:/Users/alica/Desktop/FromServer/"));
	    //FileUtils.forceDelete(new File("C:/Users/alica/Desktop/FromServer/"));
	    FileUtils.deleteQuietly(new File("C:/Users/alica/Desktop/FromServer/"));
	    } catch (FileNotFoundException ex) {
        	
            //System.out.println("File not found. New Created Alican Client");
        }*/
		
		try 
		{
			
			 socket= new Socket("192.168.137.1", 8000);
			//Socket socket = new Socket("192.168.147.102", 8000);
			
			fromServer = new DataInputStream(socket.getInputStream());
			toServer =new DataOutputStream(socket.getOutputStream());
		}
	    catch (IOException ex) 
		{
	    	ex.printStackTrace(); 
	    }
		
		//try 	
		//{
			toServer.writeUTF("FaceRecClient"); // sending client's answer to server
			toServer.writeUTF("FaceRecClient"); // sending client's answer to server
			//toServer.writeUTF(BTMac); // sending client's answer to server
		//}
		
		//catch (IOException e) 
		//{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		while(controller)
		{
		try 	
		{ 	
			order = fromServer.readUTF();
			if(order.equals("Coming"))
			{


				 System.out.println("Geldi Panpa");
				 
				 	File theDir1 = null;
				 	OutputStream out1 = null;
				
				    try 
				    {
				    	theDir1 = new File("C:/Users/alica/Desktop/FromServer/");
					    theDir1.mkdir();
					    String fileName1 = String.format("Randomly_Selected.png");
					    out1 = new FileOutputStream("C:/Users/alica/Desktop/FromServer/"+fileName1);
			     
			        } catch (FileNotFoundException ex) {
			        	
			        
			            System.out.println("File not found. New Created Alican Client");
			        }

			        byte[] b1 = new byte[16*1024];
			        

			        int count=0;
			        while ((count = fromServer.read(b1)) > 0) {
			            out1.write(b1, 0, count);
			            System.out.println("C");
			           
			        }			       
			        
			        out1.close();
			        fromServer.close();
			        toServer.flush();
			        controller=false;
				}
		}
		
		catch (IOException el) 
		{
			// TODO Auto-generated catch block
			el.printStackTrace();
		}	
		}

        try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
	    result = FaceRecognition.callFaceRecognition();
	    System.out.println("sonuç budur " + result);
	    if (result <= 0.35)
	    	sendResult = 1;
	    else
	    	sendResult = 0;

		socket2= new Socket("192.168.137.1", 8000);
		fromServer2 = new DataInputStream(socket2.getInputStream());
		toServer2 =new DataOutputStream(socket2.getOutputStream());
		toServer2.writeUTF("FaceRecResult"); // sending client's asnwer to server
		toServer2.writeUTF("FaceRecResult"); // sending client's asnwer to server
        toServer2.writeInt(sendResult);
        socket2.close();
        socket.close();
        User=false;
        
	
	}while(User==false && controller==false);

	}
}