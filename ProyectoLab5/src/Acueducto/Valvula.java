
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ottoalexander
 */
public class Valvula {
    private ArrayList<String> listaValvulas;
    private String municipio;
    private long cantHab;
    
    Valvula(String municipio, long habitantes)
    {
        this.municipio=municipio;
        this.cantHab = habitantes;
    } 
   
}
