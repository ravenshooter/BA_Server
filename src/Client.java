import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class Client {
     
	public static void main(String[] args) {
	 	Client client = new Client();
	 	try {
	 	    client.test();
	 	} catch (IOException e) {
	 	    e.printStackTrace();
	 	}
	}
     
     
    void test() throws IOException {
	 	String ip = "127.0.0.1"; // localhost
	 	int port = 11111;
	 	java.net.Socket socket = new java.net.Socket(ip,port); // verbindet sich mit Server
	 	String zuSendendeNachricht = "hor_stroke;1.9866645336151123;0.6117985844612122;-1.5722301006317139;0.798660933971405;0.034346505999565125;-0.7472013831138611;1.1282689571380615;0.9317635893821716;-2.3884358406066895;-1.1375696659088135;0.08437924087047577;-1.4621301889419556;-9.704822540283203;-2.133579730987549;4.780306816101074;6.019453048706055;1.3665356636047363;-0.023549802601337433;5.417705535888672;-0.36242344975471497;0.826897144317627;-3.805469036102295;1.5736463069915771;-2.0763988494873047;-5.791823863983154;1.5481003522872925;0.5626359581947327;-5.612797737121582;1.2249562740325928;1.1790913343429565;-4.025102138519287;0.370209276676178;1.5661147832870483;-2.552292585372925;0.002757774433121085;1.0457119941711426;-1.5706008672714233;-0.2597755491733551;1.161488652229309;-0.9965788722038269;-0.2633499205112457;0.7790050506591797;-0.42134955525398254;-0.30072247982025146;0.7108680605888367;-0.22295886278152466;-0.22130641341209412;0.3098600208759308;";
	  	schreibeNachricht(socket, zuSendendeNachricht);
	 	String empfangeneNachricht = leseNachricht(socket);
	 	System.out.println(empfangeneNachricht);
    }
    
    void schreibeNachricht(java.net.Socket socket, String nachricht) throws IOException {
	 	 PrintWriter printWriter =
	 	    new PrintWriter(
	 	 	new OutputStreamWriter(
	 		    socket.getOutputStream()));
	 	printWriter.print(nachricht);
	 	printWriter.flush();
    }
    
    
    String leseNachricht(java.net.Socket socket) throws IOException {
	 	BufferedReader bufferedReader =
	 	    new BufferedReader(
	 		new InputStreamReader(
	 	  	    socket.getInputStream()));
	 	char[] buffer = new char[200];
	 	int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
	 	String nachricht = new String(buffer, 0, anzahlZeichen);
	 	return nachricht;
    }
    
    
 }