import Controller.ContribucionController;
import Controller.Controller;
import Models.Domain.FormasDeContribucion.OfrecerProducto;
import Models.Domain.FormasDeContribucion.TipoDonacion;
import Models.Domain.FormasDeContribucion.TipoRubro;
import Models.Domain.Personas.Colaborador;
import Models.Domain.Personas.Humano;
import Models.Domain.Personas.Juridico;
import Models.Domain.TipoFrecuencia;
import Service.ImportadorCSV.ImportadorCSV;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, CsvValidationException {

        ImportadorCSV importadorCSV = new ImportadorCSV("test.csv");
        /*
        System.out.println("Colaboradres importados DTO");
        System.out.println("________________________");
        for( ColaboradorDTO colaborador : importadorCSV.getColaboradoresDTO() ){
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



        List<Humano> humanoList = new ArrayList<>();
        HumanoService service = new HumanoService();
        for( ColaboradorDTO colaborador : importadorCSV.getColaboradoresDTO() ){
            humanoList.add(service.toEntity(colaborador));
        }

        System.out.println("Colaboradres importados siendo clases del dominio");
        System.out.println("________________________");
        Humano humano = humanoList.get(0);
        System.out.println("Nombre: " + humano.getNombre() );
        System.out.println("Apellido: " + humano.getApellido() );
        System.out.println("________________________ Colaboraciones __________________");
        humano.getFormaDeContribucion().forEach(f-> System.out.println(  "Clase: " + f.getClass().toString() + " Fecha: " + f.getFechaDeDonacion() ));
        System.out.println("Puntaje: " + humano.getPuntaje());




        System.out.println("********************************* PUNTOS ESTRATEGICOS ************************");

        List<Punto> puntosEstrategicos = ServicioPuntosAPI.getInstance().obtenerPuntosEstrategicos("1","30");
        for(Punto punto : puntosEstrategicos){
            System.out.println("Latitud :" + punto.getLatitud());
            System.out.println("Longitud :" + punto.getLongitud());
        }

    */
        /*
        Colaborador colaborador1 = new Juridico();
        Colaborador colaborador2 = new Humano();
        Controller controller;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        controller = new ContribucionController(colaborador1);

        controller.create(TipoDonacion.OFRECER_PRODUCTO, TipoRubro.ELECTRONICA,"Laptop",30.00,1,"Notebook");
        controller.create(TipoDonacion.OFRECER_PRODUCTO, TipoRubro.ELECTRONICA,"TV",70.00,1,"Smart TV");
        controller.create(TipoDonacion.OFRECER_PRODUCTO, TipoRubro.ELECTRONICA,"Lampara",10.00,1,"Iluminacion");


        controller.setUsuario(colaborador2);

        controller.create(TipoDonacion.DONACION_DINERO, 40.00, TipoFrecuencia.DIARIO);
        // 20 puntos
        controller.create(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
        // 10 puntos

        List<OfrecerProducto> productos = colaborador1.getFormaDeContribucion().stream()
                .filter(f -> f instanceof OfrecerProducto) // Filtrar objetos de tipo Producto
                .map(f -> (OfrecerProducto) f) // Convertir a tipo Producto
                .toList();

        for(OfrecerProducto producto : productos ){
            System.out.println("Nombre de producto : " + producto.getProducto().getNombre());
            System.out.println("Precio : " + producto.getPuntosNecesarios() );
            System.out.println("Cantidad : " + producto.getStock());
            System.out.println("------------------------------------------------");
        }
        System.out.println("Selecciona una opcion : ");
        int opcion = Integer.parseInt(br.readLine());

        OfrecerProducto producto = productos.get(opcion);

        colaborador2.realizarCanje(producto,1);

        System.out.println("Opcion seleccionada");
        System.out.println("Producto comprado : " + colaborador2.getHistorialCanje().get(0).getProducto().getNombre());
        System.out.println("Fecha : " + colaborador2.getHistorialCanje().get(0).getProducto().getFechaDeDonacion());
        */

    }


}
