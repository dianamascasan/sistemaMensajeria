
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * This class implements the remote interface CallbackServerInterface.
 *
 * @author M. L. Liu
 */
public class ServerImpl extends UnicastRemoteObject
        implements ServerInterface {

    private Vector clientList;
    private java.sql.Connection conexion;

    public ServerImpl() throws RemoteException {
        super();
        clientList = new Vector();
        conexion();
    }

    public String sayHello()
            throws java.rmi.RemoteException {
        return ("hello");
    }

    public synchronized void registerForCallback(
            ClientInterface callbackClientObject)
            throws java.rmi.RemoteException {
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.addElement(callbackClientObject);
            System.out.println("Registered new client ");
            doCallbacks();
        } // end if
    }

// This remote method allows an object client to 
// cancel its registration for callback
// @param id is an ID for the client; to be used by
// the server to uniquely identify the registered client.
    public synchronized void unregisterForCallback(
            ClientInterface callbackClientObject)
            throws java.rmi.RemoteException {
        if (clientList.removeElement(callbackClientObject)) {
            System.out.println("Unregistered client ");
        } else {
            System.out.println(
                    "unregister: clientwasn't registered.");
        }
    }

    private synchronized void doCallbacks() throws java.rmi.RemoteException {
        // make callback to each registered client
        System.out.println(
                "**************************************\n"
                + "Callbacks initiated ---");
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println("doing " + i + "-th callback\n");
            // convert the vector object to a callback object
            ClientInterface nextClient
                    = (ClientInterface) clientList.elementAt(i);
            // invoke the callback method
            nextClient.notifyMe("Number of registered clients="
                    + clientList.size());
        }// end for
        System.out.println("********************************\n"
                + "Server completed callbacks ---");
    } // doCallbacks

    /**
     *
     */
    public void conexion() {

        Properties configuracion = new Properties();
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://"
                    + configuracion.getProperty("servidor") + ":"
                    + configuracion.getProperty("puerto") + "/"
                    + configuracion.getProperty("baseDatos"),
                    usuario);
//            try {
//                PreparedStatement stmCategorias = conexion.prepareStatement("insert into public.usuario"(nombre,clave)" + " values(?, ?)");
//                stmCategorias.setString(1, "diana");
//                stmCategorias.setString(2, "diana");
//                stmCategorias.executeUpdate();
//                PreparedStatement stmCategorias1 = conexion.prepareStatement("select * from public.usuario");
//
//                ResultSet rsCategorias = stmCategorias1.executeQuery();
//                while (rsCategorias.next()) {
//                    System.out.println(rsCategorias.getString("nombre") + rsCategorias.getString("clave"));
//                }
//
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            } finally {
//                try {
//                    stmCategorias.close();
//                } catch (SQLException e) {
//                    System.out.println("Imposible cerrar cursores");
//                }
            //           }
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());

        } catch (IOException | java.sql.SQLException i) {
            System.out.println(i.getMessage());

        }

    }

}// end CallbackServerImpl class   
