
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author teres
 */
public class Amigo implements Serializable{
    private Client2Interface interfaz;
    private String nombre;
    private String conversacion;

    public Amigo(Client2Interface interfaz, String nombre) {
        this.interfaz = interfaz;
        this.nombre = nombre;
        this.conversacion="";
    }

    public Client2Interface getInterfaz() {
        return interfaz;
    }

    public String getNombre() {
        return nombre;
    }

    public String getConversacion() {
        return conversacion;
    }

    public void setConversacion(String conversacion) {
        this.conversacion += conversacion;
    }
    
    
}
