
import java.rmi.*;
import java.rmi.server.*;

/**
 * This class implements the remote interface CallbackClientInterface.
 *
 * @author M. L. Liu
 */
public class ClientImpl extends UnicastRemoteObject
        implements ClientInterface {

    private VChat ig;

    public ClientImpl(VChat ig) throws RemoteException {
        super();
        this.ig = ig;
        
    }

    @Override
    public void nuevoChat(Amigo u) throws RemoteException {
         this.ig.actualizarNuevosChats(u);

    }

    @Override
    public void borrarChat(String u) throws RemoteException {
        this.ig.borrarChats(u);
    }

    
    
    @Override
    public void hayNotificaciones() throws RemoteException {
        this.ig.haySolicitudes();
    }

}// end CallbackClientImpl class   
