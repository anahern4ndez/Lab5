/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase principal que realiza los calculos y guarda los datos relevantes
 * Acueducto.java
 **/
package Acueducto;

import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
public class Acueducto 
{
    @Id private ObjectId id;
    private ArrayList<Tanque> contenedores;
    
    public Acueducto()
    {
        contenedores = new ArrayList<>();
    }
    public void nuevoCilindrico(String ID, String[] municipios, long[] habitantes, String region, double radio, double altura)
    {
        Tanque nuevo = new Cilindrico(ID, municipios, habitantes, region, radio, altura);
        contenedores.add(nuevo);
    }
    public void nuevoCubico(String ID, String[] municipios, long[] habitantes, String region, double lado)
    {
        Tanque nuevo = new Cubico(ID, municipios, habitantes, region, lado);
        contenedores.add(nuevo);   
    }
    public void nuevoOrtogonal(String ID, String[] municipios, long[] habitantes, String region, double largo, double ancho, double alto)
    {
        Tanque nuevo = new Ortogonal(ID, municipios, habitantes, region, largo, ancho, alto);
        contenedores.add(nuevo);   
    }
    public String obtenerTanques()
    {
        String cilindricos = "TANQUES CILINDRICOS: ";
        String cubo = "\n TANQUES CUBICOS: ";
        String ort = "\n TANQUES ORTOGONALES: ";
        for (Tanque tanque: contenedores)
        {
            if (tanque instanceof Cilindrico)
            {
                cilindricos += "\n\tTanque número ";
                cilindricos += ((Cilindrico)tanque).getID();
            }
            if (tanque instanceof Cubico)
            {
                cubo += "\n\tTanque número ";
                cubo += ((Cubico)tanque).getID();
            }
            if (tanque instanceof Ortogonal)
            {
                ort += "\n\tTanque número ";
                ort += ((Ortogonal)tanque).getID();
            }
        }
        return cilindricos + cubo + ort;
    }
    public long valvulasAbiertasC()
    {
        long numero =0;
        for (Tanque tanque: contenedores)
        {
            if (tanque instanceof Cilindrico)
            {
                for(int i=0; i<10; i++)
                {
                    if (((Cilindrico)tanque).getValvulas()[i].getEstado() == true)   
                    {
                        numero +=1;
                    }
                }
            }
        }
        return numero;
    }
    public Valvula[] abrirValvula(String IDtanque, String municipio, String fecha, ArrayList<Tanque> contenedores)
    {
        Valvula[] valvulas = null;
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(IDtanque))
            {
                for(int i=0; i<10; i++)
                {
                    if (tanque.getValvulas()[i].getMunicipio().equals(municipio))   
                    {
                        tanque.getValvulas()[i].setEstado(true);
                        tanque.getValvulas()[i].fechaAbierto(fecha);
                    }
                }
                valvulas = tanque.getValvulas();
            }
        }
        return valvulas;
    }
    public Valvula[] cerrarValvula(String IDtanque, String municipio, String fecha, ArrayList<Tanque> contenedores)
    {
        Valvula[] valvulas = null;
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(IDtanque))
            {
                for(int i=0; i<10; i++)
                {
                    if (tanque.getValvulas()[i].getMunicipio().equals(municipio))   
                    {
                        tanque.getValvulas()[i].setEstado(false);
                        tanque.getValvulas()[i].fechaCerrado(fecha);
                    }
                }
            }
            valvulas = tanque.getValvulas();
        }
        return valvulas;
    }
    public double getAguaRegion(String region)
    {
        double agua =0;
        for (Tanque tanque: contenedores)
        {
            if (tanque.getRegion().equals(region))
            {
                agua = tanque.getAguaRestante();
            }
        }
        return agua;
    }
    public double llenarTanque(String ID, ArrayList<Tanque> contenedores)
    {
        double capacidadTanque = 0;
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(ID))
            {
                capacidadTanque = tanque.llenarTanque();
            }
        }
        return capacidadTanque;
    }
    
}
