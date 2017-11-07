/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase para modelar la información específica de un tanque con forma cilíndrica
 * Cubico.java
 **/
package Acueducto;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Cubico extends Tanque 
{
    //@Id private ObjectId id;
    private double lado;
    
    public Cubico(){}
    public Cubico(String numero, String[] municipios, long[] habitantes, String region, double lado) 
    {
       super(numero, municipios, habitantes, region);
       this.lado = lado;
       capacidad = calcularVolumen();
       super.calcularPorcentaje();
    }
    /**
     * 
     * @return double del volumen del tanque
     */
    public double calcularVolumen()
    {
       double resultado = (lado*lado*lado);
       return resultado;
    }
    /**
     * 
     * @return String informacion extra relevante de un tanque
     */
    @Override
    public String toString()
    {
        String hilo = " \n TIPO DE TANQUE: CUBICO  " + super.toString();
        return hilo;
    }
}
