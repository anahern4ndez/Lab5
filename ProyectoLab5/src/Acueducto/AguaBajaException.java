/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase que modela la exception de niveles bajos de agua en un tanque
 *  AguaBajaException.java
 **/
package Acueducto;

public class AguaBajaException extends Exception
{
    private double porcentaje;
    public AguaBajaException(String texto, double porcentaje)
    {
        super(texto);
        this.porcentaje = porcentaje;
    }
    /**
     * 
     * @return double del porcentaje del tanque
     */
    public double getPorcentaje()
    {
        return porcentaje;
    }
    
    
    
}
