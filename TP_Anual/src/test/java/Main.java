import Models.Domain.Personas.Humano;
import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;
import Service.Converters.HumanoService;
import Service.DTO.ColaboradorDTO;
import Service.ImportadorCSV.ImportadorCSV;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, CsvValidationException {

        ImportadorCSV importadorCSV = new ImportadorCSV("test.csv");

        System.out.println("Colaboradres importados DTO");
        System.out.println("________________________");
        for( ColaboradorDTO colaborador : importadorCSV.getColaboradoresDTO() ){
            System.out.println("Tipo Documento:" + colaborador.getTipoDocumento());
            System.out.println("Documento:" + colaborador.getNumDocumento());
            System.out.println("Nombre:" + colaborador.getNombre());
            System.out.println("Apellido:" + colaborador.getApellido());
            System.out.println("Mail:" + colaborador.getMail());
            System.out.println("________________________ Colaboraciones __________________");
            colaborador.getColaboracionDTOS().forEach(g ->
                            System.out.println(
                                    " Fecha Colaboracion: " + g.getFechaColaboracion() +
                                            " Forma Colaboracion: " + g.getFormaDeColaboracion() +
                                            " Cantidad: " + g.getCantidad()

                            )

                    );
            System.out.println("________________________ _____ __________________");
        }



        List<Humano> humanoList = new ArrayList<>();
        HumanoService service = new HumanoService();
        for( ColaboradorDTO colaborador : importadorCSV.getColaboradoresDTO() ){
            humanoList.add(service.toEntity(colaborador));
        }

        System.out.println("Colaboradres importados siendo clases del dominio");
        System.out.println("________________________");
        Humano humano = humanoList.get(0);
        System.out.println("Nombre: " + humano.getNombre() );
        System.out.println("Apellido: " + humano.getApellido() );
        System.out.println("________________________ Colaboraciones __________________");
        humano.getFormaDeContribucion().forEach(f-> System.out.println(  "Clase: " + f.getClass().toString() + " Fecha: " + f.getFechaDeDonacion() ));
        System.out.println("Puntaje: " + humano.getPuntaje());




        System.out.println("********************************* PUNTOS ESTRATEGICOS ************************");

        List<Punto> puntosEstrategicos = ServicioPuntosAPI.getInstance().obtenerPuntosEstrategicos("1","30");
        for(Punto punto : puntosEstrategicos){
            System.out.println("Latitud :" + punto.getLatitud());
            System.out.println("Longitud :" + punto.getLongitud());
        }

    }

}
