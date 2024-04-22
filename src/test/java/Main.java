import Controller.DonacionController;
import Models.*;
import Models.FormasDeContribucion.FormaDeContribucion;
import Models.Personas.Colaborador;
import Models.Personas.Humano;
import Models.Personas.Juridico;

public class Main {
    public static void main(String[] args) {
        Colaborador lucas = new Juridico("lucas", TipoJuridico.ONG,null,null);

        DonacionController controller = new DonacionController(lucas);
        System.out.println("1) Cantidad donaciones -> " + lucas.getFormaDeContribucion().size());
        System.out.println("..............................................................");

        controller.create("3", null,null,null);

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
