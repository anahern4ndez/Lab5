/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase padre que modela toda la informacion general de un tanque cualquiera
 * Tanque.java
 **/
package Acueducto;

public class Tanque {
    protected String numero;
    protected double capacidad;
    protected Valvula[] valvulas;
    protected double porcentajeAguaDisponible;
    protected double cantAguaDisponible;
    protected String region; // region a la cual el tanque esta proveendo agua
    
    public Tanque(String numero, String[] municipios, long[] habitantes, String region)
    {
        this.numero = numero;
        this.region=region;
        valvulas = new Valvula[10];
        for(int i=0; i<10; i++)
        {
            valvulas[i]= new Valvula(municipios[i], habitantes[i]);
        }
    }
    public void calcularPorcentaje()
    {
        double habitantes = 0;
        for(int i=0; i<10; i++)
        {
            habitantes += valvulas[i].getcant_Habitantes();
        }
        habitantes *= 2;
        cantAguaDisponible = capacidad - habitantes;
        if (cantAguaDisponible > 0.0)
        {
            porcentajeAguaDisponible = (cantAguaDisponible * 100.0)/capacidad;
        }
        else 
        {
            porcentajeAguaDisponible =0.0;
        }
       
    }
    public void llenarTanque()
    {
        porcentajeAguaDisponible = 100.0;
    }
    public String getID()
    {
        return numero;
    }
    public String getRegion()
    {
        return region;
    }
    public Valvula[] getValvulas()
    {
        return valvulas;
    }
    public double getPorcentaje()
    {
        return porcentajeAguaDisponible;
    }
    public double getCapacidad()
    {
        return capacidad;
    }
    public double getAguaRestante()
    {
        return cantAguaDisponible;
    }
    public String toString()
    {
        return "\n TANQUE NÚMERO "+ numero + "\n CANTIDAD DE AGUA RESTANTE DENTRO DEL DEL TANQUE: "+ cantAguaDisponible;
    }
}
