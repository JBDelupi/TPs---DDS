import Controller.DonacionController;
import Models.FormasDeContribucion.FormaDeContribucion;
import Models.Heladera;
import Models.Personas.Colaborador;
import Models.Personas.Humano;
import Models.TipoFrecuencia;
import Models.Vianda;

public class Main {
    public static void main(String[] args) {
        Colaborador lucas = new Humano("lucas","iturrioz");

        DonacionController controller = new DonacionController(lucas);
        System.out.println("1) Cantidad donaciones -> " + lucas.getFormaDeContribucion().size());
        System.out.println("..............................................................");

        controller.create("1", 15, TipoFrecuencia.ANUAL);
        System.out.println("2) Cantidad donaciones -> " + lucas.getFormaDeContribucion().size());
        System.out.println("Tipo Donacion -> "+ lucas.getFormaDeContribucion().get(0).getClass().getSimpleName());
        System.out.println("..............................................................");


        Heladera heladera = new Heladera();
        Vianda vianda = new Vianda();

        controller.create("2",vianda,heladera);
        System.out.println("3) Cantidad donaciones -> " + lucas.getFormaDeContribucion().size());
        System.out.println("Tipo Donacion -> "+ lucas.getFormaDeContribucion().get(1).getClass().getSimpleName());

    }

}
