package chatclient;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientChat {
    private static InetAddress host;
    private static final int PORT = 1234;
    
    public static void main(String[] args) {
        try {
            //host = InetAddress.getLocalHost();          
            host = InetAddress.getByName(args[0]);
            //host = InetAddress.getByName("10.10.3.137");
        } catch(UnknownHostException uhEx) {
            System.out.println("\nHost ID not found!\n");
            System.exit(1);
        }                
        sendMessages();
    }

    private static void sendMessages() {
        Socket socket = null;
        String myName;
        try {
            socket = new Socket(host,PORT);
            Scanner networkInput = new Scanner(socket.getInputStream());
            PrintWriter networkOutput =	new PrintWriter(socket.getOutputStream(),true);
            Scanner userEntry = new Scanner(System.in);            
            String message;                   
            
            System.out.print("#Your name? ");
            message = userEntry.nextLine();
            myName = message;
            
            ReceivedHandler receivedHandler = new ReceivedHandler(networkInput,myName);
            receivedHandler.start();            
            
            networkOutput.println(message);

            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                System.out.println("Sleep Error");
            }
                        
            do {
                //System.out.print('#'+myName+"> ");
                message = userEntry.nextLine();
                networkOutput.println(message);                                
                System.out.print('#'+myName+">");
            } while (!message.equals("QUIT"));
            
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\nClosing connection...");
                socket.close();
            } catch(IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}