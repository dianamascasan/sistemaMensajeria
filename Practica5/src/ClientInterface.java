
import java.rmi.*;

/**
 * This is a remote interface for illustrating RMI client callback.
 *
 * @author M. L. Liu
 */
public interface ClientInterface
        extends java.rmi.Remote {
    // This remote method is invoked by a callback
    // server to make a callback to an client which
    // implements this interface.
    // @param message - a string containing information for the
    //                  client to process upon being called back.

    public void nuevoChat(Usuario u)
            throws java.rmi.RemoteException;

    public void borrarChat(Usuario u)
            throws java.rmi.RemoteException;

    public void recibirMensaje(String mensaje, String usuario)
            throws RemoteException;

    public void hayNotificaciones()
            throws RemoteException;

} // end interface
