package TestEntrega2;

import Models.Domain.Personas.Actores.Fisico;
import Service.ImportadorCSV.DTO.FisicoDTO;
import Service.ImportadorCSV.ImportadorCSV;
import Service.ImportadorCSV.Mappers.FisicoMapper;
import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.Mensaje.MensajeBienvenida;
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
        Set<FisicoDTO> importados = ImportadorCSV.getInstance("test.csv", ";").getColaboradoresDTO();
        Assertions.assertFalse(importados.isEmpty());

    }

    @Test
    public void importarCSVConGuion() throws CsvValidationException, IOException {
        Set<FisicoDTO> importadosCSV = ImportadorCSV.getInstance("test2.csv", "-").getColaboradoresDTO();

        for( FisicoDTO colaborador : importadosCSV ){
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
        Set<FisicoDTO> importados = ImportadorCSV.getInstance("test.csv", ";").getColaboradoresDTO();

        FisicoMapper mapper = new FisicoMapper();
        List<Fisico> fisicoList = new ArrayList<>();




        for(FisicoDTO fisicoDTO : importados){
            MensajeBuilder notificacion = new MensajeBuilder();
            MensajeBienvenida mensajeBienvenida = notificacion.asunto("Nuevo colaborador")
                                          .contenido("Datos de usuario")
                                                  .destinatario(fisicoDTO.getMail())
                                                          .construir();

            Fisico fisico = mapper.toEntity(fisicoDTO);

            fisico.setMedioDeNotificacion(new CorreoAdapter());

            fisico.getMedioDeNotificacion().Notificar(mensajeBienvenida);


        }

      //  Assertions.assertFalse(humanoList.isEmpty());

    }



}
