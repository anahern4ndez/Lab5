/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acueducto;

/**
 *
 * @author Ana
 */
public class Tanque {
    protected String numero;
    protected double capacidad;
    protected Valvula[] valvulas;
    protected double porcentajeAguaDisponible;
    protected double cantAguaDisponible;
    protected String region; // region a la cual el tanque esta proveendo agua
    
    public Tanque(String numero, String[] municipios, long[] habitantes, String region)
    {
        this.numero = numero;
        this.region=region;
        valvulas = new Valvula[10];
        for(int i=0; i<10; i++)
        {
            valvulas[i]= new Valvula(municipios[i], habitantes[i]);
        }
    }
    public void calcularPorcentaje()
    {
        
    }
    public void llenarTanque()
    {
        porcentajeAguaDisponible = 100.0;
    }
    public double getPorcentaje()
    {
        return porcentajeAguaDisponible;
    }
    public double getCapacidad()
    {
        return capacidad;
    }
    public double getAguaRestante()
    {
        return cantAguaDisponible;
    }
    public String toString()
    {
        return "\n TANQUE NÚMERO "+ numero + "\n CANTIDAD DE AGUA RESTANTE DENTRO DEL DEL TANQUE: "+ cantAguaDisponible;
    }
}
