
import java.rmi.*;
import java.rmi.server.*;

/**
 * This class implements the remote interface CallbackClientInterface.
 *
 * @author M. L. Liu
 */
public class Client2Impl extends UnicastRemoteObject
        implements Client2Interface {

    private VChat ig;

    public Client2Impl(VChat ig) throws RemoteException {
        super();
        this.ig = ig;
        
    }

    
    @Override
    public void recibirMensaje(String mensaje, String usuario) throws RemoteException {
        this.ig.recibirMensaje(mensaje, usuario);
    }
    
    
}// end CallbackClientImpl class   
