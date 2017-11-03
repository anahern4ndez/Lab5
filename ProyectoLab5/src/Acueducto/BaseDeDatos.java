/**
 *  @author Ana Lucia Hernandez (17137)
 *          Alexander Trujillo (17189)
 *  @since 02.11.17
 *  Clase principal que realiza los calculos y guarda los datos relevantes
 * Acueducto.java
 **/
package Acueducto;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class BaseDeDatos {
    
    public BaseDeDatos()
    {
        MongoClient mongo = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(Acueducto.class).map(Valvula.class).map(Tanque.class).map(Cilindrico.class).map(Ortogonal.class).map(Cubico.class); // clases a guardar
        Datastore ds = morphia.createDatastore(mongo, "Acueducto");//Creacion de la base de datos
    }
    
    
    
}
