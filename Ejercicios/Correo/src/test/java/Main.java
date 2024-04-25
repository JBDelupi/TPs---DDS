import Models.*;

import javax.sound.midi.SysexMessage;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /////////////////////////// INSTANCIANDO DIRECCION ////////////////////////
        Localidad  moreno = new Localidad("Moreno", "1712");
        Localidad  ituzaingo = new Localidad("Ituzaingo", "1714");

        Direccion direccion = new Direccion("Corrientes", "256",moreno);
        Direccion direccion2 = new Direccion("Belgrano", "300",ituzaingo);
        Direccion direccion3 = new Direccion("Maipu", "259",moreno);
        Direccion direccion4 = new Direccion("Callao", "123",ituzaingo);
        /////////////////////////// INSTANCIANDO DIRECCION ////////////////////////

        /////////////////////////// INSTANCIANDO DESTINATARIO - REMITENTE ////////////////////////
        Cliente destinatario = new Cliente("lucas",direccion2,"Humano");
        Cliente remitente = new Cliente("Jugetes.SA",direccion,"Empresa");
        /////////////////////////// INSTANCIANDO DESTINATARIO - REMITENTE ////////////////////////

        /////////////////////////// INSTANCIANDO ENVIO ////////////////////////
        Envio envio = new Encomienda(TipoEncomienda.PACKET, destinatario,remitente,130.5);
        /////////////////////////// INSTANCIANDO ENVIO ////////////////////////

        /////////////////////////// INSTANCIANDO SUCURSALES ////////////////////////
        Sucursal sucursal_moreno = new Sucursal (1, "Sucursal Moreno", direccion3);
        Sucursal sucursal_ituzaingo = new Sucursal (1, "Sucursal ituzaingo", direccion4);
        /////////////////////////// INSTANCIANDO SUCURSALES ////////////////////////

        System.out.print("Codigo de rastreo del paquete: " + envio.getCodigoRastreo().getLink() +"\n");

        Estado estado1 = new Estado(TipoEstado.PROCESANDO,sucursal_moreno);
        envio.agregarNuevoEstado(estado1);
        Estado estado2 = new Estado(TipoEstado.EN_ENVIO,sucursal_moreno);
        envio.agregarNuevoEstado(estado2);
        Estado estado3 = new Estado(TipoEstado.PROCESANDO,sucursal_ituzaingo);
        envio.agregarNuevoEstado(estado3);
        Estado estado4 = new Estado(TipoEstado.EN_ENVIO,sucursal_ituzaingo);
        envio.agregarNuevoEstado(estado4);
        envio.paqueteEntregado();


        System.out.println("------------------------------------");
        for( Estado estado : envio.getEstadoActual() ){
            System.out.println("FECHA: " + estado.getFecha() );
            System.out.println("SUCURSAL: " + estado.getSucursal().getNombre() );
            System.out.println("ESTADO: " + estado.getNombre().name() );
            System.out.println("LOCALIDAD: " +  estado.getSucursal().getDireccion().getLocalidad().getNombre());
            System.out.println(" ------------------------------------");
        }


    }
}
