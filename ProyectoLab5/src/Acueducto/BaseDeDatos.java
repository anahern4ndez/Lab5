/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase principal que realiza los calculos y guarda los datos relevantes
 * Acueducto.java
 **/
package Acueducto;

import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

public class BaseDeDatos {
    private Acueducto acueducto;
    private Datastore ds;
    
    public BaseDeDatos()
    {
        acueducto = new Acueducto();
        MongoClient mongo = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(Acueducto.class).map(Valvula.class).map(Tanque.class).map(Cilindrico.class).map(Ortogonal.class).map(Cubico.class); // clases a guardar
        ds = morphia.createDatastore(mongo, "Acueducto");//Creacion de la base de datos
    }
    public void ingresarTanque(String tipo, String numero, String[] municipios, long[] habitantes, String region,double radio, double altura, double ancho, double alto, double largo, double lado)
    {
        if (tipo.equals("Cilindrico"))
        {
            //acueducto.nuevoCilindrico(numero, municipios, habitantes, region, radio, altura);
            Tanque cilin = new Cilindrico(numero, municipios, habitantes, region, radio, altura);
            
            ds.save(cilin);
        }
        if (tipo.equals("Cubico"))
        {
            acueducto.nuevoCubico(numero, municipios, habitantes, region, lado);
            Tanque tanque = new Cubico(numero, municipios, habitantes, region, lado);
            ds.save(tanque);
        }
        if (tipo.equals("Ortogonal"))
        {
            acueducto.nuevoOrtogonal(numero, municipios, habitantes, region, largo, ancho, alto);
            Tanque tanque = new Ortogonal(numero, municipios, habitantes, region, largo, ancho, alto);
            ds.save(tanque);
        }
    }
    public String buscarTanques()
    {
        ArrayList<Tanque> todosTanques = new ArrayList<>();
        String tanques ="";
        Query<Ortogonal> query = ds.createQuery(Ortogonal.class);
        List<Ortogonal> busqueda = query.asList();
        for(Ortogonal orto: busqueda)
        {
            todosTanques.add(orto);
        }
        Query<Cilindrico> query2 = ds.createQuery(Cilindrico.class);
        List<Cilindrico> busqueda2 = query2.asList();
        for(Cilindrico orto: busqueda2)
        {
            todosTanques.add(orto);
        }
        Query<Cubico> query3 = ds.createQuery(Cubico.class);
        List<Cubico> busqueda3 = query3.asList();
        for(Cubico orto: busqueda3)
        {
            todosTanques.add(orto);
        }
        for (Tanque tanque: todosTanques)
        {
            tanques += ("\n"+ tanque.getID() + "\n"+ tanque.getRegion());
        }
        return tanques;
    }
    public void updateTanques()
    {
        
    }
    public long valvulasC()
    {
        long valv = 0;
        Query<Cilindrico> query = ds.createQuery(Cilindrico.class);
        List<Cilindrico> valvulasAbiertas = query.asList();
        for (Cilindrico cilindrico : valvulasAbiertas) 
        {
            for(int i =0; i<10; i++)
            {
               if (cilindrico.getValvulas()[i].getEstado() == true)
                {
                    valv +=1;
                }
            }
        } 
        return valv;
    }
    
    
    
}
