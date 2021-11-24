
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
    private String IP;
    private Integer puerto;
    private HashMap<String, Usuario> amigos;

    public Usuario(String nombre, String IP, Integer puerto) {
        this.nombre = nombre;
        this.IP = IP;
        this.puerto = puerto;
        this.amigos= new HashMap<>();
    }

    public HashMap<String, Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(Usuario amigo) {
        this.amigos.put(amigo.getNombre(), amigo);
    }

    public String getNombre() {
        return nombre;
    }

    public String getIP() {
        return IP;
    }

    public Integer getPuerto() {
        return puerto;
    }
    
    
    
}
