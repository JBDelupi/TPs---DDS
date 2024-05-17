
import Controller.*;

import Models.FormasDeContribucion.EntregaDeTarjeta;
import Models.FormasDeContribucion.FormaDeContribucion;
import Models.FormasDeContribucion.TipoDonacion;
import Models.Heladera;
import Models.Personas.Administrador;
import Models.Personas.Colaborador;
import Models.Personas.Humano;
import Models.Personas.Usuario;
import Models.Tarjeta.RegistroDeUso;
import Models.Tarjeta.Tarjeta;


public class Main {
    public static void main(String[] args) {
        Colaborador lucas = new Humano("lucas","iturrioz");

        ContribucionController usuario = new ContribucionController(lucas);

        usuario.create(TipoDonacion.ENTREGA_TARJETAS,"Jose",0);

        EntregaDeTarjeta formaDeContribucion = (EntregaDeTarjeta) lucas.getFormaDeContribucion().get(0);
        Tarjeta tarjeta = formaDeContribucion.getTarjeta();

        // Supongamos que usa la tarjeta "JOSE"

        RegistroDeUso nuevoUso = new RegistroDeUso(new Heladera());
        tarjeta.agregarNuevoUso(nuevoUso); // 1 VEZ
        tarjeta.agregarNuevoUso(nuevoUso); // 2 VEZ
        tarjeta.agregarNuevoUso(nuevoUso); // 3 VEZ
        tarjeta.agregarNuevoUso(nuevoUso); // 4 VEZ
        tarjeta.agregarNuevoUso(nuevoUso); // 5 VEZ -> no lo dejaria


    }

}
