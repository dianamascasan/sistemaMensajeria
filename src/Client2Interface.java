
import java.rmi.*;

/**
 * This is a remote interface for illustrating RMI client callback.
 *
 * @author M. L. Liu
 */
public interface Client2Interface
        extends java.rmi.Remote {
    // This remote method is invoked by a callback
    // server to make a callback to an client which
    // implements this interface.
    // @param message - a string containing information for the
    //                  client to process upon being called back.

  

    public void recibirMensaje(String mensaje, String usuario)
            throws RemoteException;

 
} // end interface
