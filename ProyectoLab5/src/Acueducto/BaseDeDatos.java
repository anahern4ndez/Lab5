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
import org.mongodb.morphia.query.UpdateOperations;

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
    /**
     * Método para crear un nuevo tanque en la BD.
     * @param tipo: tipo de tanque que se agregará a la base de datos
     * @param numero: numero de identificación del tanque
     * @param municipios: todos los municipios a lso que alimenta el tanque
     * @param habitantes: todos los habitantes (o el número en promedio) de un municipio
     * @param region: region a la que alimenta el tanque
     * @param radio: en el caso sea un tanque cilindrico
     * @param altura:en el caso sea un tanque cilindrico
     * @param ancho:en el caso sea un tanque ortogonal
     * @param alto:en el caso sea un tanque ortogonal
     * @param largo: en el caso sea un tanque ortogonal
     * @param lado: en el caso sea un tanque cubico
     * 
     */
    public void ingresarTanque(String tipo, String numero, String[] municipios, long[] habitantes, String region,double radio, double altura, double ancho, double alto, double largo, double lado)
    {
        if (tipo.equals("Cilindrico"))
        {
            acueducto.nuevoCilindrico(numero, municipios, habitantes, region, radio, altura);
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
    /**
     * Método para mostrar todos los tanques actuales del acueducto
     * @return String de la inormación que se quiere saber de los tanques buscados en la base de datos.
     * 
     */
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
            tanques += ("\n"+ tanque);
        }
        return tanques;
    }
    /**
     * Método para cambiarle los valores de un tanque a su 100% cuando éste se llena
     * @param tanqueID: ID del tanque al que se quiere llenar
     * @return boolean de si el tanque ingresado existe en la base de datos
     */
    public boolean updateLlenado(String tanqueID)
    {
        boolean existeTanque= false;
        ArrayList<Tanque> todosTanques = new ArrayList<>();
        Query<Ortogonal> query = ds.createQuery(Ortogonal.class).field("numero").equal(tanqueID);
        List<Ortogonal> busqueda = query.asList();
        for(Ortogonal orto: busqueda)
        {
            todosTanques.add(orto);
        }
        Query<Cilindrico> query2 = ds.createQuery(Cilindrico.class).field("numero").equal(tanqueID);
        List<Cilindrico> busqueda2 = query2.asList();
        for(Cilindrico orto: busqueda2)
        {
            todosTanques.add(orto);
        }
        Query<Cubico> query3 = ds.createQuery(Cubico.class).field("numero").equal(tanqueID);
        List<Cubico> busqueda3 = query3.asList();
        for(Cubico orto: busqueda3)
        {
            todosTanques.add(orto);
        }
        for(Tanque tanque : todosTanques)
        {
            if (tanque.getID().equals(tanqueID))
            {
                existeTanque = true;
                if (tanque instanceof Ortogonal)
                {
                    UpdateOperations upd = ds.createUpdateOperations(Ortogonal.class).set("cantAguaDisponible", acueducto.llenarTanque(tanqueID, todosTanques)).set("porcentajeAguaDisponible",100);
                    ds.update(query, upd,false);        
                }
                if (tanque instanceof Cilindrico)
                {
                    UpdateOperations upd = ds.createUpdateOperations(Cilindrico.class).set("cantAguaDisponible", acueducto.llenarTanque(tanqueID, todosTanques)).set("porcentajeAguaDisponible",100);
                    ds.update(query2, upd,false);
                }
                if (tanque instanceof Cubico)
                {
                    UpdateOperations upd = ds.createUpdateOperations(Cubico.class).set("cantAguaDisponible", acueducto.llenarTanque(tanqueID, todosTanques)).set("porcentajeAguaDisponible",100);
                    ds.update(query3, upd,false);
                }
            }
        }
        return existeTanque;
    }
    /**
     * Método para mostrar la cantidad de válvulas de todos los tanques cilíndricos que están abiertas
     * @return long cantidad de valvulas
     * 
     */
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
    /**
     * Método para abrir una válvula de un tanque específico
     * @param IDtanque: tanque que contiene la válvula en cuestión
     * @param municipio: municipioal que alimenta la válvula
     * @param fecha: fecha en la cual se está abriendo la valvula
     * @return boolean para ver si la valvula que ha ingresado el usuario existe en los tanques de la base de datos
     * 
     */
    public boolean abrirValvula(String IDtanque, String municipio, String fecha)
    {
        boolean existeValvula = false;
        Valvula[] valvulas =null;
        ArrayList<Tanque> contenedores = new ArrayList<>();
        Query<Ortogonal> query = ds.createQuery(Ortogonal.class).field("numero").equal(IDtanque);
        List<Ortogonal> busqueda = query.asList();
        for(Ortogonal orto: busqueda)
        {
            contenedores.add(orto);
        }
        Query<Cilindrico> query2 = ds.createQuery(Cilindrico.class).field("numero").equal(IDtanque);
        List<Cilindrico> busqueda2 = query2.asList();
        for(Cilindrico orto: busqueda2)
        {
            contenedores.add(orto);
        }
        Query<Cubico> query3 = ds.createQuery(Cubico.class).field("numero").equal(IDtanque);
        List<Cubico> busqueda3 = query3.asList();
        for(Cubico orto: busqueda3)
        {
            contenedores.add(orto);
        }
        for (Tanque tanque: contenedores)
        {
            valvulas = tanque.getValvulas();
            for(int i =0; i<10; i++)
            {
                if (valvulas[i].getMunicipio().equals(municipio))
                {
                    existeValvula = true;
                    if (tanque.getID().equals(IDtanque))
                    {
                        if (tanque instanceof Ortogonal)
                        {
                            UpdateOperations upd = ds.createUpdateOperations(Ortogonal.class).set("valvulas", acueducto.abrirValvula(IDtanque, municipio, fecha, contenedores));
                            ds.update(query, upd,false);
                            tanque.calcularPorcentaje();
                            upd = ds.createUpdateOperations(Ortogonal.class).set("porcentajeAguaDisponible", tanque.getPorcentaje()).set("cantAguaDisponible", tanque.getAguaRestante());
                            ds.update(query, upd,false);
                        }
                        if (tanque instanceof Cilindrico)
                        {
                            UpdateOperations upd = ds.createUpdateOperations(Cilindrico.class).set("valvulas", acueducto.abrirValvula(IDtanque, municipio, fecha, contenedores));
                            ds.update(query2, upd,false);
                            tanque.calcularPorcentaje();
                            upd = ds.createUpdateOperations(Cilindrico.class).set("porcentajeAguaDisponible", tanque.getPorcentaje()).set("cantAguaDisponible", tanque.getAguaRestante());
                            ds.update(query2, upd,false);
                        }
                        if (tanque instanceof Cubico)
                        {
                            UpdateOperations upd = ds.createUpdateOperations(Cubico.class).set("valvulas", acueducto.abrirValvula(IDtanque, municipio, fecha, contenedores));
                            ds.update(query3, upd,false);
                            tanque.calcularPorcentaje();
                            upd = ds.createUpdateOperations(Cubico.class).set("porcentajeAguaDisponible", tanque.getPorcentaje()).set("cantAguaDisponible", tanque.getAguaRestante());
                            ds.update(query3, upd,false);
                        }
                    }
                }
            }
        }
                
        return existeValvula;
    }
    /**
     * Método para cerrar una válvula de un tanque específico
     * @param IDtanque: tanque que contiene la válvula en cuestión
     * @param municipio: municipioal que alimenta la válvula
     * @param fecha: fecha en la cual se está cerrando la valvula
     * 
     */
    public void cerrarValvula(String IDtanque, String municipio, String fecha)
    {
        ArrayList<Tanque> contenedores = new ArrayList<>();
        Query<Ortogonal> query = ds.createQuery(Ortogonal.class).field("numero").equal(IDtanque);
        List<Ortogonal> busqueda = query.asList();
        for(Ortogonal orto: busqueda)
        {
            contenedores.add(orto);
        }
        Query<Cilindrico> query2 = ds.createQuery(Cilindrico.class).field("numero").equal(IDtanque);
        List<Cilindrico> busqueda2 = query2.asList();
        for(Cilindrico orto: busqueda2)
        {
            contenedores.add(orto);
        }
        Query<Cubico> query3 = ds.createQuery(Cubico.class).field("numero").equal(IDtanque);
        List<Cubico> busqueda3 = query3.asList();
        for(Cubico orto: busqueda3)
        {
            contenedores.add(orto);
        }
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(IDtanque))
            {
                if (tanque instanceof Ortogonal)
                {
                    UpdateOperations upd = ds.createUpdateOperations(Ortogonal.class).set("valvulas", acueducto.cerrarValvula(IDtanque, municipio, fecha, contenedores));
                    ds.update(query, upd,false);
                }
                if (tanque instanceof Cilindrico)
                {
                    UpdateOperations upd = ds.createUpdateOperations(Cilindrico.class).set("valvulas", acueducto.cerrarValvula(IDtanque, municipio, fecha, contenedores));
                    ds.update(query2, upd,false);
                }
                if (tanque instanceof Cubico)
                {
                    UpdateOperations upd = ds.createUpdateOperations(Cubico.class).set("valvulas", acueducto.cerrarValvula(IDtanque, municipio, fecha, contenedores));
                    ds.update(query3, upd,false);
                }
            }
        }
    }
    /**
     * Método para obtener el agua que dispone un tanque que alimenta una región
     * @param region: region a la que alimenta el tanque
     * @return double de cantidad de agua disponible
     * 
     */
    public double aguaDisponible(String region)
    {
        double agua =0;
        ArrayList<Tanque> contenedores = new ArrayList<>();
        Query<Ortogonal> query = ds.createQuery(Ortogonal.class).field("region").equal(region);
        List<Ortogonal> busqueda = query.asList();
        for(Ortogonal orto: busqueda)
        {
            contenedores.add(orto);
        }
        Query<Cilindrico> query2 = ds.createQuery(Cilindrico.class).field("region").equal(region);
        List<Cilindrico> busqueda2 = query2.asList();
        for(Cilindrico orto: busqueda2)
        {
            contenedores.add(orto);
        }
        Query<Cubico> query3 = ds.createQuery(Cubico.class).field("region").equal(region);
        List<Cubico> busqueda3 = query3.asList();
        for(Cubico orto: busqueda3)
        {
            contenedores.add(orto);
        }
        for (Tanque tanque: contenedores)
        {
            agua = tanque.getAguaRestante();
        }
        return agua;
    }
    /**
     * Método para obtener el porcentaje de agua disponible que tiene un tanque específico
     * @param IDtanque: identificacion del tanque
     * @return double porcentaje del tanque
     */
    public double getPorcentajeTanque(String IDtanque)
    {
        double porcentaje = 0;
        ArrayList<Tanque> contenedores = new ArrayList<>();
        Query<Ortogonal> query = ds.createQuery(Ortogonal.class);
        List<Ortogonal> busqueda = query.asList();
        for(Ortogonal orto: busqueda)
        {
            contenedores.add(orto);
        }
        Query<Cilindrico> query2 = ds.createQuery(Cilindrico.class);
        List<Cilindrico> busqueda2 = query2.asList();
        for(Cilindrico orto: busqueda2)
        {
            contenedores.add(orto);
        }
        Query<Cubico> query3 = ds.createQuery(Cubico.class);
        List<Cubico> busqueda3 = query3.asList();
        for(Cubico orto: busqueda3)
        {
            contenedores.add(orto);
        }
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(IDtanque))
            {
                porcentaje = tanque.getPorcentaje();
            }
        }
        return porcentaje;
    }
    /**
     * Método para cerrar todas las válvulas de un tanque cuyo porcentaje es menor a 10% de agua. 
     * @param ID: identificacion del tanque
     * @param fechacerrado: fecha en la que se está cerrando todas las valvulas
     */
    public void cerrarTodasValvulas(String ID, String fechacerrado)
    {
        ArrayList<Tanque> contenedores = new ArrayList<>();
        Query<Ortogonal> query = ds.createQuery(Ortogonal.class);
        List<Ortogonal> busqueda = query.asList();
        for(Ortogonal orto: busqueda)
        {
            contenedores.add(orto);
        }
        Query<Cilindrico> query2 = ds.createQuery(Cilindrico.class);
        List<Cilindrico> busqueda2 = query2.asList();
        for(Cilindrico orto: busqueda2)
        {
            contenedores.add(orto);
        }
        Query<Cubico> query3 = ds.createQuery(Cubico.class);
        List<Cubico> busqueda3 = query3.asList();
        for(Cubico orto: busqueda3)
        {
            contenedores.add(orto);
        }
        for (Tanque tanque: contenedores)
        {
            if (tanque.getID().equals(ID))
            {
                tanque.cerrarTodasValvulas(fechacerrado);
            }
        }   
    }
}
