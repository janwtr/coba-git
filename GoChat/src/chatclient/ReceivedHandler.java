package chatclient;

import java.util.*;

public class ReceivedHandler extends Thread {
    Scanner networkInput;
    String received, myName;
    public ReceivedHandler(Scanner networkInput, String myName) {        
        this.networkInput = networkInput;
        this.myName = myName;
    }    
    
    @Override
    public void run() {
        do {
            try {
                received = networkInput.nextLine();
            } catch(NoSuchElementException e) {
                System.out.println("Connectio Closed");
                //break;
                System.exit(0);
            } 
            System.out.println("\n"+received);               
            System.out.print("#"+myName+">");

        } while (!received.equals("QUIT"));
        System.out.println("Connectio Closed");
        //break;
        System.exit(0);

        
    }
}
