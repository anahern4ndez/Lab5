/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase que llama a las exceptions a mostrar en el GUI
 *  porcentajeLow.java
 **/
package Acueducto;

public class porcentajeLow {
    public static final double cerrarTanque = 10.0, noValvulas = 25.0;
    /**
     * 
     * @param value: valor del porcentaje por el cual se está tirando la excepción
     * @throws AguaBajaException
     */
    public static void porcentajeLow(double value) throws AguaBajaException
    {
        AguaBajaException excepcion;
        String mensaje;
        double porcentaje;
        mensaje ="\n PORCENTAJE: "+ value + "%";
        
        if (value <= noValvulas && value > cerrarTanque)
        {
            mensaje += "\n Porcentaje demasiado bajo, hasta que el porcentaje sea mayor a 25% no se podrá abrir más válvulas.";
            porcentaje =25;
            excepcion = new AguaBajaException(mensaje, porcentaje);
            throw excepcion;
        }
        if (value <= cerrarTanque)
        {
            mensaje += "\n Porcentaje demasiado bajo, hasta que el porcentaje sea mayor a 10%, todas las válvulas de este tanque se mantendrán cerradas. \n Favor de llenar tanque.";
            porcentaje = 10;
            excepcion = new AguaBajaException(mensaje, porcentaje);
            throw excepcion;
        }
    }
    
}
