/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase padre que modela toda la informacion general de un tanque cualquiera
 * Tanque.java
 **/
package Acueducto;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Tanque {
    @Id protected ObjectId id;
    protected String numero;
    protected double capacidad;
    protected double porcentajeAguaDisponible;
    protected double cantAguaDisponible;
    protected String region; // region a la cual el tanque esta proveendo agua
    protected Valvula[] valvulas;
    //CONSTRUCTOR PARA MONGO
    public Tanque(){}
    //CONSTRUCTOR PARA JAVA
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
    /**
     * 
     * Método para calcular el porcentaje de agua restante de un tanque
     */
    public void calcularPorcentaje()
    {
        double habitantes = 0;
        for(int i=0; i<10; i++)
        {
            if (valvulas[i].getEstado() == true)
            {
                habitantes += (10*valvulas[i].getcant_Habitantes());
            }
        } 
        cantAguaDisponible = capacidad - habitantes;
        if (cantAguaDisponible > 0.0)
        {
            porcentajeAguaDisponible = (cantAguaDisponible * 100.0)/capacidad;
        }
        else 
        {
            porcentajeAguaDisponible =0.0;
            cantAguaDisponible =0.0;
        }
       
    }
    /**
     * Método para llenar el tanque. 
     * @return double de cantidad de agua disponible en el tanque una vez se ha llenado.
     */
    public double llenarTanque()
    {
        porcentajeAguaDisponible = 100.0;
        cantAguaDisponible = capacidad;
        return cantAguaDisponible;
    }
    /**
     * 
     * @return String ID de tanque
     */
    public String getID()
    {
        return numero;
    }
    /**
     * 
     * @return String region a la que alimenta el tanque
     */
    public String getRegion()
    {
        return region;
    }
    /**
     * 
     * @return Valvula[] lista de valvulas del tanque
     */
    public Valvula[] getValvulas()
    {
        return valvulas;
    }
    /**
     * 
     * @return double porcentaje de agua restante en el tanque
     */
    public double getPorcentaje()
    {
        return porcentajeAguaDisponible;
    }
    /**
     * 
     * @return double capacidad del tanque
     */
    public double getCapacidad()
    {
        return capacidad;
    }
    /**
     * 
     * @return double agua restante
     */
    public double getAguaRestante()
    {
        return cantAguaDisponible;
    }
    /**
     * Para cuando el porcentaje de agua esté a menos del 10%, se cierre el tanque por completo.
     * @param fecha: para guardar la fecha en la cual se cerraron todas las valvulas del tanque
     */
    public void cerrarTodasValvulas(String fecha)
    {
        for (int i =0; i<10; i++)
        {
            valvulas[i].setEstado(false);
            valvulas[i].fechaCerrado(fecha);
        }
    }
    /**
     * 
     * @return String informacion relevante del tanque
     */
    public String toString()
    {
        String hilo = "\n TANQUE NÚMERO "+ numero + "\n CANTIDAD DE AGUA RESTANTE DENTRO DEL DEL TANQUE: "+ cantAguaDisponible;
        hilo += "\n REGION AL QUE ALIMENTA: "+ region;
        hilo += "\n PORCENTAJE DE AGUA RESTANTE: "+ porcentajeAguaDisponible;
        hilo += "\n VÁLVULAS QUE DISPONE: ";
        for (Valvula valv : valvulas)
        {
            hilo += ("\n\t" +valv);
        }
        return hilo;
    }
    
}
