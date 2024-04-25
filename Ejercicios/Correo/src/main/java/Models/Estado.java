package Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Estado {
    private TipoEstado nombre;
    private LocalDate fecha;
    private Administrativo empleado;
    private Sucursal sucursal;
    private Cartero cartero;

    public Estado(TipoEstado nombre, Sucursal sucursal) {
        this.nombre = nombre;
        this.fecha = LocalDate.now();
        this.sucursal = sucursal;
        // this.empleado = empleado;

    }
}
