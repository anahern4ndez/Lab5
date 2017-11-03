/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase para modelar la información específica de un tanque con forma cilíndrica
 * Cubico.java
 **/
package Acueducto;

public class Cubico extends Tanque 
{
    
    private double lado;
    
    public Cubico(String numero, String[] municipios, long[] habitantes, String region, double lado) 
    {
       super(numero, municipios, habitantes, region);
       this.lado = lado;
    }
    
    public double getLado() 
    { 
       return lado;   
    }
     
    public double calcularVolumen(double lado)
    {
       double resultado = (lado*lado*lado);
       return resultado;
    }
}
