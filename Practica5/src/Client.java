
import java.io.*;
import java.rmi.*;

/**
 * This class represents the object client for a distributed object of class
 * CallbackServerImpl, which implements the remote interface
 * CallbackServerInterface. It also accepts callback from the server.
 *
 *
 *
 * @author M. L. Liu
 */
public class Client {

    public static void main(String args[]) {

        
        try {
            int RMIPort;
            String hostName;
            InputStreamReader is
                    = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println(
                    "Enter the RMIRegistry host namer:");
            hostName = br.readLine();
            System.out.println(
                    "Enter the RMIregistry port number:");
            String portNum = br.readLine();
            RMIPort = Integer.parseInt(portNum);
            VAutentificacion menu;
            menu = new VAutentificacion(hostName, RMIPort);
            menu.setVisible(true);
            
        } // end try 
        catch (Exception e) {
            System.out.println(
                    "Exception in CallbackClient: " + e);
        } // end catch
    } //end main
}//end class
