import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;
import Service.ImportadorCSV.AdapterLectorArchivoCSV;
import Service.ImportadorCSV.DTO.ColaboradorDTO;
import Service.ImportadorCSV.DTO.FormaColaboracionDTO;
import Service.ImportadorCSV.ImportadorCSV;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, CsvValidationException {


    //    -- (PUNTO;Radio) -> [API MOCKEA] -> LISTA POSIBLES PUNTOS dentro del radio

        ImportadorCSV importadorCSV = new ImportadorCSV("test.csv");
            System.out.println("Colaboradres importados");
            System.out.println("________________________");
        for( ColaboradorDTO colaborador : importadorCSV.getColaboradoresDTO() ){
            System.out.println("Tipo Documento:" + colaborador.getTipoDocumento());
            System.out.println("Documento:" + colaborador.getNumDocumento());
            System.out.println("Nombre:" + colaborador.getNombre());
            System.out.println("Apellido:" + colaborador.getApellido());
            System.out.println("Mail:" + colaborador.getMail());
            System.out.println("________________________ Colaboraciones __________________");
            importadorCSV.getColaboracionesDTO().stream()
                    .filter(f -> f.getNumDocumento() == colaborador.getNumDocumento())
                    .forEach(g ->
                           System.out.println(
                                   " Fecha Colaboracion: " + g.getFechaColaboracion() +
                                   " Forma Colaboracion: " + g.getFormaDeColaboracion() +
                                   " Cantidad: " + g.getCantidad()

                           )

                    );
            System.out.println("________________________ _____ __________________");
        }


    }

}
