package TestEntrega2;

import Controller.Actores.Administrador;
import Controller.AdministradorController;
import Controller.Controller;
import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
import Models.Domain.FormasDeContribucion.TipoDonacion;
import Models.Domain.Personas.AreaCobertura;
import Models.Domain.Personas.Colaborador;
import Models.Domain.Personas.Humano;
import Models.Domain.Personas.Tecnico;
import Models.Domain.TipoDeDocumento;
import Service.APIPuntos.Punto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.function.Predicate.not;

public class TestEntrega2_Tecnico {


    Administrador administrador;
    Colaborador pepito;
    AdministradorController controller ;

    Punto punto;
    AreaCobertura areaCobertura;

    @BeforeEach
    public void init(){
        administrador = new Administrador();
        pepito = new Humano();
        controller = new AdministradorController();

        areaCobertura = new AreaCobertura();
        punto = new Punto();

        punto.setLongitud("1");
        punto.setLatitud("2");

        areaCobertura.setCentro(punto);
        areaCobertura.setRadio("30");

    }

    @Test
    public void InstanciarTecnico(){
        controller.setUsuario(administrador);

        Tecnico tecnico = controller.tecnicoCreate(
                "Lucas"
                ,"iturrioz"
                , TipoDeDocumento.DNI
                ,"13547624"
                ,"31135476248"
                ,areaCobertura);

        Assertions.assertEquals("Lucas", tecnico.getNombre() );

    }

    @Test
    public void InstanciarTecnicoNoPuedePorSuRol(){
        controller.setUsuario(pepito);

        Assertions.assertThrows(Controller.UnauthorizedAccessException.class, () -> {
            controller.tecnicoCreate(
                    "Lucas"
                    ,"iturrioz"
                    , TipoDeDocumento.DNI
                    ,"13547624"
                    ,"31135476248"
                    ,areaCobertura);
        });
    }



}
