/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase para modelar la información específica de un tanque con forma cilíndrica
 * Ortogonal.java
 **/
package Acueducto;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Ortogonal extends Tanque 
{
    //@Id private ObjectId id;
    private double largo;
    private double ancho;
    private double alto;
    
    public Ortogonal(){}
    public Ortogonal(String numero, String[] municipios, long[] habitantes, String region, double largo, double ancho, double alto) 
    {
        super(numero, municipios, habitantes, region);
        this.largo = largo; 
        this.ancho= ancho;
        this.alto= alto;
        capacidad = calcularVolumen();
        super.calcularPorcentaje();
    }
    public double getLargo () 
    { 
        return largo;
    }
    public double getAncho () 
    {
        return ancho;   
    }
    public double getAlto () 
    { 
        return alto;   
    }
    public double calcularVolumen()
    {
        double resultado = alto*largo*ancho;
        return resultado;
    }
    public String toString()
    {
        String hilo = "\n TIPO DE TANQUE: ORTOGONAL " +super.toString() ;
        return hilo;
    }
}
