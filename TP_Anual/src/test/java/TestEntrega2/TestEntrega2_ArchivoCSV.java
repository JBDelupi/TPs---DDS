package TestEntrega2;

import Models.Domain.Personas.Humano;
import Service.DTO.HumanoDTO;
import Service.ImportadorCSV.ImportadorCSV;
import Service.Mappers.HumanoMapper;
import Service.Notificacion.CorreoAdapter;
import Service.Notificacion.Mensaje;
import Service.Notificacion.NotificacionBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestEntrega2_ArchivoCSV {

    @Test
    public void importarCSVConPuntoYComa() throws CsvValidationException, IOException {
        Set<HumanoDTO> importados = ImportadorCSV.getInstance("test.csv", ";").getColaboradoresDTO();
        Assertions.assertFalse(importados.isEmpty());

    }

    @Test
    public void importarCSVConGuion() throws CsvValidationException, IOException {
        Set<HumanoDTO> importadosCSV = ImportadorCSV.getInstance("test2.csv", "-").getColaboradoresDTO();

        for( HumanoDTO colaborador : importadosCSV ){
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


        Assertions.assertFalse(importadosCSV.isEmpty());

    }

    @Test
    public void importarCSVConPuntoYComaMapper() throws CsvValidationException, IOException {
        Set<HumanoDTO> importados = ImportadorCSV.getInstance("test.csv", ";").getColaboradoresDTO();

        HumanoMapper mapper = new HumanoMapper();
        List<Humano> humanoList = new ArrayList<>();




        for(HumanoDTO humanoDTO : importados){
            NotificacionBuilder notificacion = new NotificacionBuilder();
            Mensaje mensaje = notificacion.asunto("Nuevo colaborador")
                                          .contenido("Datos de usuario")
                                                  .destinatario(humanoDTO.getMail())
                                                          .construir();

            Humano humano = mapper.toEntity(humanoDTO);

            humano.setMedioDeNotificacion(new CorreoAdapter());

            humano.getMedioDeNotificacion().Notificar(mensaje);


        }

      //  Assertions.assertFalse(humanoList.isEmpty());

    }



}
