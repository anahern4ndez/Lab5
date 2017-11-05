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
   // @Id private ObjectId id;
    private double radio;
    private double altura;
 
   
   public Cilindrico(String numero, String[] municipios, long[] habitantes, String region, double radio, double altura) 
   {
       super(numero, municipios, habitantes, region);
       this.radio = radio; 
       this.altura= altura; 
   }
   public Cilindrico(){}
   
   public double getRadio () 
   { 
       return radio;   
   }
   public double getAltura () 
   { 
       return altura;   
   }
    
   public double calcularVolumen(double altura, double radio)
   {
      double resultado = altura*Math.PI*Math.pow(radio,2);
      return resultado;
   }
}
