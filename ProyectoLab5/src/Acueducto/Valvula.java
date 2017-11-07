/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase para modelar la información de la valvula de un tanque
 *  Valvula.java
 **/
package Acueducto;

import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;

public class Valvula 
{
    @Id private ObjectId id;
    private ArrayList<String> fechasAbiertos;
    private ArrayList<String> fechasCerrados;
    private long cantHab;
    private boolean estado; 
    private String municipio;
    //CONSTRUCTOR PARA MONGO
    public Valvula()
    {
        fechasAbiertos = new ArrayList<>();
        fechasCerrados = new ArrayList<>();
    }
    //CONSTRUCTOR PARA JAVA
    public Valvula(String municipio, long habitantes)
    {
        fechasAbiertos = new ArrayList<>();
        fechasCerrados = new ArrayList<>();
        this.municipio= municipio;
        this.cantHab = habitantes;
        estado = false;
    }
    /**
     * 
     * @return String municipio al que la valvula da agua
     */
    public String getMunicipio () 
    { 
        return municipio;  
    }
    /**
     * 
     * @return long cantidad de habitantes del municipio
     */
    public long getcant_Habitantes () 
    { 
        return cantHab;
    }
    /**
     * 
     * @param abierto: boolean de falso si se quiere cerrar la valvula o verdadero si se quiere abrir.
     */
    public void setEstado(boolean abierto)
    {
        this.estado = abierto;
    }
    /**
     * 
     * @return boolean de si está abierta la válvula o no
     */
    public boolean getEstado () 
    { 
        return estado;  
    } 
    /**
     * 
     * @param fecha: fecha en la cual se abrió la valvula
     */
    public void fechaAbierto(String fecha)
    {
        fechasAbiertos.add(fecha);
    }
    /**
     * 
     * @param fecha: fecha en la cual se cerró la válvula
     */
    public void fechaCerrado(String fecha)
    {
        fechasCerrados.add(fecha);
    }
    /**
     * 
     * @return String informacion querida de la valvula
     */
    public String toString()
    {
        String hilo = "";
        String est ="";
        if (estado == true) { est = "Abierta";}
        if (estado == false) { est = "Cerrada";}
        hilo += "\n\t Estado de la válvula: "+ est;
        hilo += "\n\t Municipio al que alimenta: "+ municipio;
        return hilo;
        
    }
   
}
