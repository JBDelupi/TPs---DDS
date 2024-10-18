package Models.Domain.Reporte;

import Service.TareaDiferida.ChromeTask;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public abstract class TemplateReporte<T> {

    private String id;
    protected ChromeTask chromeTask;
    private LocalDate fecha;
    private List<List<String>> items;

    public TemplateReporte() {
       // this.chromeTask = new ChromeTask();
        fecha = LocalDate.now();
        items = new ArrayList<>();
    }

    public void activar() {
     //   chromeTask.ejecutarTareaPrograma(4000, this, "obtenerListado");
    }

    public void desactivar() {
        //   chromeTask.pausarTarea();
    }

    // Este método guarda en items cada fila del reporte.
    public abstract void obtenerListado(List<T> items);
}
