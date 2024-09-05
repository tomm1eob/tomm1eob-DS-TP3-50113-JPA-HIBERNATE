package entidades;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="Articulo")

public class Articulo implements Serializable {

    // Declaraciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "Denominacion")
    private String denominacion;
    @Column(name = "Precio")
    private int precio;

    // Relación uno a muchos con DetalleFactura
    // mappedBy indica que la propiedad en DetalleFactura que se usa para la relación es 'articulo'
    @Builder.Default
    @OneToMany(mappedBy = "articulo")
    private List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();

    // Relación muchos a muchos con Categoria
    // @JoinTable define la tabla intermedia y las columnas de unión
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Articulo_categoria",
                joinColumns = @JoinColumn(name = "articulo_id"),
                inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<Categoria>();

}
