package gochatserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

class ClientHandler extends Thread {
    private Socket client;
    private Scanner networkInput;
    private PrintWriter networkOutput;
    private HashMap<String,Socket> onlineChat;
    private Scanner friendInput;
    private PrintWriter friendOutput;
    private String myname;
    

    public ClientHandler(Socket socket, HashMap<String,Socket> onlineChat) 	{        
        client = socket;
        this.onlineChat = onlineChat;
        try {
            networkInput = new Scanner(client.getInputStream());
            networkOutput = new PrintWriter(client.getOutputStream(),true);        
        } catch (IOException ex) {
            System.out.println("Unable to creat network I/O");
        }
    }    
        
    @Override
    public void run()
    {
        String message, received, fren[]; 
        
        // your name?
        received = networkInput.nextLine();
        myname = received;
        onlineChat.put(myname, client);

        //networkOutput.println("Online chat: "+onlineChat.keySet().toString());        
                        
        do {
            received = networkInput.nextLine();
            if (received.contains(":")) {
                fren = received.split(":");
                forward(fren[0], fren[1]);    
            } else {
                networkOutput.println("[tujuan]:[pesan] or QUIT");
            }
        } while (!received.equals("QUIT"));  
        
        networkOutput.println("QUIT");     
        onlineChat.remove(myname);
        
        try {
            if (client!=null) {
                System.out.println("Closing down connection...");
                client.close();
            }
        } catch(IOException ioEx) {
            System.out.println("Unable to disconnect!");
        }
    }	    

    private void forward(String fren0, String fren1) {        
        if (onlineChat.containsKey(fren0)) {
            try {
                friendInput = new Scanner(onlineChat.get(fren0).getInputStream());            
                friendOutput = new PrintWriter(onlineChat.get(fren0).getOutputStream(),true);
            } catch (IOException ex) {
                System.out.println("Unable to creat friend I/O");
            }
            //networkOutput.println(fren0+"online");
            friendOutput.println(myname+">"+fren0+":"+ fren1);
        } else {
            networkOutput.println(fren0+" not online!");            
        }        
    }
}
