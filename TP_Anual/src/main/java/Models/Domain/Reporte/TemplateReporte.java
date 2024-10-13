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

    protected List<T> items;
    protected ChromeTask chromeTask;

    public TemplateReporte() {
        items = new ArrayList<>();
        this.chromeTask = new ChromeTask();
    }



    public void activar() {
        chromeTask.ejecutarTareaPrograma(4000, this, "generarReporte");
    }

    public void desactivar() {
        chromeTask.pausarTarea();
    }

    public void cargarItem(T item) {
        items.add(item);
    }

    public void cargarItems(List<T> items) {
        this.items.addAll(items);
    }

    public void generarReporte() throws IOException {
        List<Object[]> listado = obtenerListado(items);
        try {
            String filePath = getFilePath();
            armarReporte(listado, filePath);
            System.out.println("Reporte generado: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract List<Object[]> obtenerListado(List<T> items);
    protected abstract String getFilePath();
    protected abstract void armarReporte(List<Object[]> listado, String filePath) throws IOException;


}
