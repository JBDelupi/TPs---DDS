package Service.ImportadorCSV.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FisicoDTO {
    String TipoDocumento;
    String numDocumento;
    String nombre;
    String apellido;
    String mail;
    List<FormaColaboracionDTO> colaboracionDTOS;

    public FisicoDTO(){
        this.colaboracionDTOS = new ArrayList<>();
    }

    public void agregarColaboracion(FormaColaboracionDTO colaboracionDTO){
        colaboracionDTOS.add(colaboracionDTO);
    }



}
