package Models.Domain.Reporte;

import Models.Domain.Heladera.Heladera;
import Service.TareaDiferida.ChromeTask;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Setter
public abstract class Reporte {
    protected List<Object> listaObjecto;
    protected ChromeTask chromeTask;


    public void activar(int periodo) {
        chromeTask.ejecutarTareaPrograma(periodo, this, "generarReporte");
    }
    public void desactivar() {
        chromeTask.pausarTarea();
    }


    public void agregarListaParaReporte(Object ... object){
        Collections.addAll(listaObjecto,object);
    }

    public void generarReporte() throws IOException {
        try {
            String filePath = "reporte_movimientos_" + LocalDate.now().toString() + ".txt";
            armarReporte(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void armarReporte(String filePath) throws IOException;

}
