/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase para modelar la información de la valvula de un tanque
 *  Valvula.java
 **/
package Acueducto;

import java.util.ArrayList;

public class Valvula 
{
    private ArrayList<String> fechasAbiertos;
    private ArrayList<String> fechasCerrados;
    private String municipio;
    private long cantHab;
    private boolean estado; 
    
    public Valvula(String municipio, long habitantes)
    {
        fechasAbiertos = new ArrayList<>();
        fechasCerrados = new ArrayList<>();
        this.municipio= municipio;
        this.cantHab = habitantes;
        estado = false;
    }
    public String getMunicipio () 
    { 
        return municipio;  
    }
    public long getcant_Habitantes () 
    { 
        return cantHab;
    }
    public void setEstado(boolean abierto)
    {
        this.estado = abierto;
    }
    public boolean getEstado () 
    { 
        return estado;  
    } 
    public void fechaAbierto(String fecha)
    {
        fechasAbiertos.add(fecha);
    }
    public void fechaCerrado(String fecha)
    {
        fechasCerrados.add(fecha);
    }
   
}
