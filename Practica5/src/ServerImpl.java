
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the remote interface CallbackServerInterface.
 *
 * @author M. L. Liu
 */
public class ServerImpl extends UnicastRemoteObject
        implements ServerInterface {

    private Vector clientList;
    private java.sql.Connection conexion;
    private HashMap<String, Usuario> usuariosConectados;

    public ServerImpl() throws RemoteException {
        super();
        clientList = new Vector();
        conexion();
        this.usuariosConectados = new HashMap<>();
    }

    @Override
    public String sayHello()
            throws java.rmi.RemoteException {
        return ("hello");
    }

    @Override
    public synchronized void registerForCallback(
            ClientInterface callbackClientObject, Usuario u)
            throws java.rmi.RemoteException {
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.addElement(callbackClientObject);
            System.out.println("Registered new client ");

        }
        this.usuariosConectados.put(u.getNombre(), u);
        conectado(u);
    }

// This remote method allows an object client to 
// cancel its registration for callback
// @param id is an ID for the client; to be used by
// the server to uniquely identify the registered client.
    @Override
    public synchronized void unregisterForCallback(
            ClientInterface callbackClientObject, Usuario u)
            throws java.rmi.RemoteException {
        if (clientList.removeElement(callbackClientObject)) {
            System.out.println("Unregistered client ");
        } else {
            System.out.println(
                    "unregister: clientwasn't registered.");
        }
        desconectado(u);
        this.usuariosConectados.remove(u.getNombre());
    }

    private synchronized void conectado(Usuario u) throws java.rmi.RemoteException {
        // make callback to each registered client
        System.out.println(
                "**************************************\n"
                + "Callbacks initiated ---");
        for (Usuario us : u.getAmigos().values()) {
            us.getInterfaz().nuevoChat(u);
        }

        // end for
        System.out.println("********************************\n"
                + "Server completed callbacks ---");
    } // doCallbacks

    private synchronized void solicitudAceptada(String usuario, String usuarioAmigo) throws java.rmi.RemoteException {
        // make callback to each registered client
        System.out.println(
                "**************************************\n"
                + "Callbacks initiated ---");
        for (Usuario us : usuariosConectados.values()) {
            if (us.getNombre().equals(usuarioAmigo)) {
                us.getInterfaz().nuevoChat(usuariosConectados.get(usuario));
                usuariosConectados.get(usuario).getInterfaz().nuevoChat(us);
               
            }
        }
//        for (Usuario us : usuariosConectados.values()) {
//            if (us.getNombre().equals(usuarioAmigo)) {
//                us.getInterfaz().nuevoChat(usuariosConectados.get(usuario));
//                for (Usuario u : usuariosConectados.values()) {
//                    {
//                        if (u.getNombre().equals(usuario)) {
//                            us.getInterfaz().nuevoChat(usuariosConectados.get(usuario));
//                        }
//                    }
//                }
//            }
//        }
//        for (Usuario us : usuariosConectados.values()) {
//            if (us.getNombre().equals(usuario)) {
//                for (Usuario u : usuariosConectados.values()) {
//                    {
//                        if (u.getNombre().equals(usuarioAmigo)) {
//                            us.getInterfaz().nuevoChat(usuariosConectados.get(usuarioAmigo));
//                        }
//                    }
//
//                }
//            }
//
//            // end for
//            System.out.println("********************************\n"
//                    + "Server completed callbacks ---");
//        } // doCallbacks

    
    }
    private synchronized void desconectado(Usuario u) throws java.rmi.RemoteException {
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
            nextClient.borrarChat(u);
        }// end for
        System.out.println("********************************\n"
                + "Server completed callbacks ---");
    } // doCallbacks

    private void avisarNotificaciones(Usuario usuarioPeticion) throws RemoteException {
        usuarioPeticion.getInterfaz().hayNotificaciones();
    }//doCallbacks

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

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());

        } catch (IOException | java.sql.SQLException i) {
            System.out.println(i.getMessage());

        }

    }

    @Override
    public void registrarUsuario(String usuario, String clave) {
        PreparedStatement stmCategorias = null;
        try {
            stmCategorias = conexion.prepareStatement("insert into public.usuario(nombre,clave) values(?,?)");
            stmCategorias.setString(1, usuario);
            stmCategorias.setString(2, clave);
            stmCategorias.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

        }

    }

    @Override
    public String verificarUsuario(String usuario, String clave) {
        PreparedStatement stmCategorias = null;
        try {
            stmCategorias = conexion.prepareStatement("select nombre from usuario where nombre=? and clave=crypt(?,clave)");
            stmCategorias.setString(1, usuario);
            stmCategorias.setString(2, clave);
            ResultSet rsCategorias = stmCategorias.executeQuery();
            while (rsCategorias.next()) {
                return rsCategorias.getString("nombre");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmCategorias.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }

        }
        return null;

    }
    @Override
    public String cambiarContraseña(String usuario, String claveVieja, String claveNueva) {
        PreparedStatement stmCategorias = null;
        try {
            stmCategorias = conexion.prepareStatement("update usuario set clave=? where nombre=? and clave=crypt(?,clave)");
            stmCategorias.setString(1, claveNueva);
            stmCategorias.setString(2, usuario);
            stmCategorias.setString(3, claveVieja);
            if(stmCategorias.executeUpdate()!=0){
                return "Contraseña cambiada correctamente";
            }else{
                return "No se pudo cambiar la contraseña";
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmCategorias.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }

        }
        return null;

    }

    @Override
    public ArrayList<String> buscarUsuario(String buscar, String usuario) {
        ArrayList<String> usuarios = new ArrayList<>();
        PreparedStatement stmCategorias = null;
        try {
            stmCategorias = conexion.prepareStatement("select nombre "
                    + "from usuario where nombre like ? "
                    + "and nombre != ?"
                    + "and nombre not in "
                    + "(select nombreUsuario from usuarioAmigo where nombreUsuarioAmigo= ?)"
                    + "and nombre not in "
                    + "(select nombreUsuarioAmigo from usuarioAmigo where nombreUsuario= ?)");
            stmCategorias.setString(1, buscar + '%');
            stmCategorias.setString(2, usuario);
            stmCategorias.setString(3, usuario);
            stmCategorias.setString(4, usuario);
            ResultSet rsCategorias = stmCategorias.executeQuery();
            while (rsCategorias.next()) {
                usuarios.add(rsCategorias.getString("nombre"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmCategorias.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }

        }
        return usuarios;

    }

    @Override
    public ArrayList<String> solicitudesAmistad(String usuario
    ) throws RemoteException {
        PreparedStatement stmCategorias = null;
        ArrayList<String> amigosSol = new ArrayList<>();
        try {
            stmCategorias = conexion.prepareStatement("select nombreUsuario "
                    + "from usuarioAmigo "
                    + "where nombreUsuarioAmigo=? "
                    + "and aceptado=false");
            stmCategorias.setString(1, usuario);
            ResultSet rsCategorias = stmCategorias.executeQuery();
            while (rsCategorias.next()) {
                System.out.println(rsCategorias.getString("nombreUsuario"));
                amigosSol.add(rsCategorias.getString("nombreUsuario"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmCategorias.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }

        }
        return amigosSol;

    }

    @Override
    public void aceptarAmistad(String usuario, String usuarioAmigo
    ) {
        PreparedStatement stmCategorias = null;

        try {
            stmCategorias = conexion.prepareStatement("UPDATE public.usuarioamigo\n"
                    + "	SET aceptado=?\n"
                    + "	WHERE nombreUsuario=? and nombreUsuarioAmigo=?;");
            stmCategorias.setBoolean(1, true);
            stmCategorias.setString(2, usuarioAmigo);
            stmCategorias.setString(3, usuario);
            stmCategorias.executeUpdate();

            stmCategorias = conexion.prepareStatement("insert into public.usuarioAmigo(nombreUsuario,nombreUsuarioAmigo,aceptado) values(?,?,?)");
            stmCategorias.setString(1, usuario);
            stmCategorias.setString(2, usuarioAmigo);
            stmCategorias.setBoolean(3, true);
            stmCategorias.executeUpdate();
            solicitudAceptada(usuario, usuarioAmigo);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmCategorias.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }

        }
    }

    @Override
    public void solicitarAmistad(String usuario, String usuarioAmigo) throws RemoteException {
        PreparedStatement stmCategorias = null;

        try {

            stmCategorias = conexion.prepareStatement("insert into public.usuarioAmigo(nombreUsuario,nombreUsuarioAmigo,aceptado) values(?,?,?)");
            stmCategorias.setString(1, usuario);
            stmCategorias.setString(2, usuarioAmigo);
            stmCategorias.setBoolean(3, false);
            stmCategorias.executeUpdate();

            for (Usuario u : usuariosConectados.values()) {
                if (u.getNombre().equals(usuarioAmigo)) {
                    avisarNotificaciones(usuariosConectados.get(usuarioAmigo));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmCategorias.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }

        }
    }

    @Override
    public boolean usuarioYaExiste(String usuario
    ) {
        PreparedStatement stmCategorias = null;
        try {
            stmCategorias = conexion.prepareStatement("select nombre from usuario where nombre=?");
            stmCategorias.setString(1, usuario);
            ResultSet rsCategorias = stmCategorias.executeQuery();
            while (rsCategorias.next()) {
                return true;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmCategorias.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }

        }
        return false;

    }

    public HashMap<String, Usuario> buscarAmigos(Usuario usuario) {
        PreparedStatement stmCategorias = null;
        HashMap<String, Usuario> amigos = new HashMap<>();
        try {
            stmCategorias = conexion.prepareStatement("select nombreusuarioamigo from public.usuarioamigo where nombreusuario=? and aceptado=true");
            stmCategorias.setString(1, usuario.getNombre());

            ResultSet rsCategorias = stmCategorias.executeQuery();
            while (rsCategorias.next()) {
                String u = rsCategorias.getString("nombreusuarioamigo");
                for (String nombre : usuariosConectados.keySet()) {
                    if (nombre.equals(u)) {
                        System.out.println(nombre);
                        //usuario.setAmigos(usuariosConectados.get(nombre));
                        amigos.put(usuariosConectados.get(nombre).getNombre(), usuariosConectados.get(nombre));
                    }
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmCategorias.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }

        }
        return amigos;

    }
}// end CallbackServerImpl class   
