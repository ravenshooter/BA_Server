import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import com.rapidminer.operator.OperatorException;


public class Server {
	
	public static final String trainingFile="C:\\Users\\Steve\\Documents\\Eclipse Projects\\testapplication\\data\\trainingSet.csv";
	
	public static final String csvFile="C:\\Users\\Steve\\Documents\\Eclipse Projects\\testapplication\\data\\sample.csv";
	
	public static void main(String[] args) {
	 	Server server = new Server();
	 	
	 	
	}
	
	
	
	
	RapidMinerTest2 miner;
	
	public Server() {
		miner = new RapidMinerTest2();
		int port = 11111;
	 	try {
			java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
			System.out.println("Server up and running on port: "+port);
			
			while(true){
				System.out.println("Waiting for new client");
		 		java.net.Socket client = warteAufAnmeldung(serverSocket);
		 		test(client);
		 	}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	}
	
	
	void test(java.net.Socket client) throws IOException {
	 	
	 	System.out.println("Client connected");
	 	String nachricht = leseNachricht(client);
	 	System.out.println(nachricht);
	 	
	 	if(nachricht.startsWith("#safeTrack#")){//safe
	 		
	 		writeToTrainingFile(trainingFile,nachricht.split("#safeTrack#")[1]+System.getProperty("line.separator"));
	 	}else{//classify
		 	writeToCSVFile(csvFile,nachricht);
		 	try{
		 		String result = miner.classify(csvFile);
			 	schreibeNachricht(client, result);
		 	}catch(OperatorException e){
		 		e.printStackTrace();
		 		schreibeNachricht(client, "error");
		 	}
		 	
	 	}
	 	
	 	
	 	
	 	System.out.println("Client done");
	}
	
	
	
	java.net.Socket warteAufAnmeldung(java.net.ServerSocket serverSocket) throws IOException {
		 	java.net.Socket socket = serverSocket.accept(); // blockiert, bis sich ein Client angemeldet hat
		 	return socket;
	}
	    
	    
	String leseNachricht(java.net.Socket socket) throws IOException {
	  	BufferedReader bufferedReader = 
	 	    new BufferedReader(
	 	 	new InputStreamReader(
	 		    socket.getInputStream()));
	  	
	 	//char[] buffer = new char[10000];
	 	//int anzahlZeichen = bufferedReader.read(buffer, 0, 10000); // blockiert bis Nachricht empfangen
	 	//String nachricht = new String(buffer, 0, anzahlZeichen);
	  	
	 	return bufferedReader.readLine();
	}
	    
	    
	void schreibeNachricht(java.net.Socket socket, String nachricht) throws IOException {
	 	PrintWriter printWriter =
	 	    new PrintWriter(
	 	        new OutputStreamWriter(
	 	 	    socket.getOutputStream()));
	 	printWriter.print(nachricht);
	 	printWriter.flush();
	}
	
	
	public void writeToCSVFile(String file, String msg){
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(file), "utf-8"));
		    writer.write(msg);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
	
	
	public void writeToTrainingFile(String file, String msg){
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(file,true), "utf-8"));
		    writer.write(msg);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
	
}
