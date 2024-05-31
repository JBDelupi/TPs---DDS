package Service.Converters;

import Models.FormasDeContribucion.FormaDeContribucion;
import Models.Personas.Humano;
import Service.DTO.ColaboradorDTO;
import Service.DTO.FormaColaboracionDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HumanoService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public Humano toEntity(ColaboradorDTO dto) {

        Humano colaborador = new Humano(dto.getNombre(), dto.getApellido());
        Double puntaje = 0.0;

        for(FormaColaboracionDTO colaboracionDTO : dto.getColaboracionDTOS() ){
            FormaDeContribucion unaForma = FactoryContribucion.controller(colaboracionDTO.getFormaDeColaboracion());

            LocalDate fechaDonacion = LocalDate.parse(colaboracionDTO.getFechaColaboracion(), formatter);
            unaForma.setFechaDeDonacion(fechaDonacion);

            colaborador.getFormaDeContribucion().add(unaForma);
            puntaje += Double.parseDouble(colaboracionDTO.getCantidad());
        }
        colaborador.setPuntaje(puntaje);

        return colaborador;


    }




    public ColaboradorDTO toDTO(Humano humano) {

        return null;
    }


}
