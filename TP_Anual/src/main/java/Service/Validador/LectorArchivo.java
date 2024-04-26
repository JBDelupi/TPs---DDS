package Service.Validador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivo {

    public LectorArchivo() {
    }

    public List<String> leerArchivo(String nombreArchivo) throws IOException {
        List<String> topPeoresContrasenias = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                topPeoresContrasenias.add(linea);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }
        return topPeoresContrasenias;
    }
}
