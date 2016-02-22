import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.rapidminer.operator.OperatorException;


public class PreprocessorServer {
	
	public static final String trainingFile="C:\\Users\\Steve\\Documents\\Eclipse Projects\\testapplication\\data\\trainingSet.csv";
	
	public static final String csvFile="C:\\Users\\Steve\\Documents\\Eclipse Projects\\testapplication\\data\\sample.csv";
	public static final String esnFile="C:\\Users\\Steve\\Documents\\Eclipse Projects\\testapplication\\data\\esn.csv";
	
	
	public static void main(String[] args) {
	 	PreprocessorServer server = new PreprocessorServer();
	 	
	 	
	}
	
	
	
	PrintWriter out;
  	BufferedReader bufferedReader ;
	KeyboardInput key;
  	
	public PreprocessorServer() {
		showIPAdress();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter file name");
		String name = keyboard.nextLine();
		String date = getCurrentTimeStamp();
		String file = "C:\\Users\\Steve\\Documents\\Eclipse Projects\\RapidMinerJavaProject\\data\\"+date+"-"+name+".csv";
		
		
		key = new KeyboardInput();
		int port = 11111;
	 	try {
	 		File yourFile = new File(file);
	 		yourFile.createNewFile();
	 		
	 		out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
	 			
	 		java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
			
	 		System.out.println("Server up and running on port: "+port);
			
			while(true){
				System.out.println("Waiting for new client");
		 		java.net.Socket client = warteAufAnmeldung(serverSocket);
		 		bufferedReader = 
		 		 	    new BufferedReader(
		 		 	 	new InputStreamReader(
		 		 		    client.getInputStream()));
		 		
		 		test(client);
		 	}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	}
	
	
	void test(java.net.Socket client) throws IOException {
	 	
	 	System.out.println("Client connected");

	 	String msg;
	 	while((msg = leseNachricht())!=null){
	 		writeToCSVFile(msg);
	 		
	 	}
	 	
	 	
	 	
	 	
	 	System.out.println("Client done");
	}
	
	
	
	java.net.Socket warteAufAnmeldung(java.net.ServerSocket serverSocket) throws IOException {
		 	java.net.Socket socket = serverSocket.accept(); // blockiert, bis sich ein Client angemeldet hat
		 	return socket;
	}
	    
	
	
	    
	String leseNachricht() throws IOException {
	 	return bufferedReader.readLine();
	}
	    
	    
	
	public void showIPAdress(){
		try {
			String ipAdress = Inet4Address.getLocalHost().getHostAddress();
				System.out.println("Current IP Adress: " +ipAdress);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

	
	public void writeToCSVFile( String msg){
		if(msg!=null){
			
			msg = msg + ";"+key.getStateAsString();
			msg = msg.replace(';', ',');
			out.println(msg);
			out.flush();
			System.out.println(msg);
		}else{
			System.out.println("null message sent");
		}
		
	}
	
	
}
