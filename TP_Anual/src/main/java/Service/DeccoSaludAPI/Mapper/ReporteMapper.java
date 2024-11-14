package Service.DeccoSaludAPI.Mapper;

import Service.DeccoSaludAPI.DTO.Reporte.Informacion;
import Service.DeccoSaludAPI.DTO.Reporte.ReporteSalud;
import Service.DeccoSaludAPI.DTO.RespuestaDTO;

import java.util.ArrayList;
import java.util.List;

public class ReporteMapper {
    private ReporteSalud reporteSalud;

    public ReporteMapper(List<RespuestaDTO> respuestaDTOS) {
        reporteSalud = new ReporteSalud();
        this.init(respuestaDTOS);
    }

    public void init(List<RespuestaDTO> respuestas) {
        for(RespuestaDTO respuesta : respuestas) {
            reporteSalud.agregarInformacion(toInfo(respuesta));
        }
    }


    public Informacion toInfo(RespuestaDTO respuestaDTO){
        Informacion informacion = new Informacion();

        informacion.setBarrio(respuestaDTO.getBarrio());
        informacion.setPersonas(respuestaDTO.getPersonas());
        informacion.setCantidadPersonas(respuestaDTO.getCantidadPersonas());

        return informacion;

    }

}
