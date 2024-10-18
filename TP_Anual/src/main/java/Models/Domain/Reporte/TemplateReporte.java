package Models.Domain.Reporte;

import Service.TareaDiferida.ChromeTask;
import lombok.Getter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public abstract class TemplateReporte<T> {

    private String id;
    protected ChromeTask chromeTask;
    private LocalDate fecha;
    private List<Object[]> items;

    public TemplateReporte() {
        this.chromeTask = new ChromeTask();
        fecha = LocalDate.now();
        items = new ArrayList<>();
    }

    public void activar() {
        chromeTask.ejecutarTareaPrograma(4000, this, "obtenerListado");
    }

    public void desactivar() {
        chromeTask.pausarTarea();
    }

    // Deberia ejecutarse con la tarea programada, este metodo guarda en items cada fila del reporte.
    // hay q persistir los items
    public abstract void obtenerListado(List<T> items);



}
