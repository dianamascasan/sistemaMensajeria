
import java.io.Serializable;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author teres
 */
public class Usuario implements Serializable  {
    private  String nombre;
    
    private HashMap<String, Usuario> amigos;
    private ClientInterface interfaz;
    private String conversacion;

    public Usuario(String nombre,ClientInterface interfaz) {
        this.nombre = nombre;
        this.conversacion="";
        this.amigos= new HashMap<>();
        this.interfaz=interfaz;
    }

    public String getConversacion() {
        return conversacion;
    }

    public void setConversacion(String conversacion) {
        this.conversacion += conversacion;
    }

    public ClientInterface getInterfaz() {
        return interfaz;
    }
    
    public HashMap<String, Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(Usuario amigo) {
        this.amigos.put(amigo.getNombre(), amigo);
    }
    public void borrarAmigos(Usuario amigo) {
        this.amigos.remove(amigo.getNombre());
    }
   

    public String getNombre() {
        return nombre;
    }

   
    
    
}
