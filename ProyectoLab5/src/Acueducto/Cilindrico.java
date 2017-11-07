/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase para modelar la información específica de un tanque con forma cilíndrica
 * Cilindrico.java
 **/
package Acueducto;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Cilindrico  extends Tanque 
{
    private double radio;
    private double altura;
 
   
   public Cilindrico(String numero, String[] municipios, long[] habitantes, String region, double radio, double altura) 
   {
       super(numero, municipios, habitantes, region);
       this.radio = radio; 
       this.altura= altura; 
       capacidad = calcularVolumen();
       super.calcularPorcentaje();
   }
   public Cilindrico(){}
   /**
     * 
     * @return double el volumen o capacidad del tanque
     */
   public double calcularVolumen()
   {
      double resultado = altura*Math.PI*Math.pow(radio,2);
      return resultado;
   }
   /**
     * 
     * @return String informacion extra relevante de un tanque cilindrico
     */
   @Override
   public String toString()
    {
        String hilo = "\n TIPO DE TANQUE: CILINDRICO "+ super.toString();
        return hilo;
    }
}
