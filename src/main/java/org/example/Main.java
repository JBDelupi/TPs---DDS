package org.example;

import Models.Colaborador;
import Models.FormasDeContribucion.DonacionDeDinero;
import Models.FormasDeContribucion.FormaDeContribucion;
import Models.Humano;
import Models.TipoFrecuencia;

public class Main {
    public static void main(String[] args) {
        Humano lucas = new Humano("lucas","iturrioz");

        FormaDeContribucion unaDonacion = new DonacionDeDinero(150, TipoFrecuencia.MENSUAL);

        unaDonacion.generarDonacion(lucas);





    }

}