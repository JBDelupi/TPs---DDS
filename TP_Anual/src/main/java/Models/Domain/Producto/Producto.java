package Models.Domain.Producto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "Producto")
@NoArgsConstructor

public class Producto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoRubro rubro;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "descripcion")
    private String descripcion;


    public Producto(TipoRubro rubro, String nombre, String imagen, String descripcion){
        this.rubro = rubro;
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }


}
