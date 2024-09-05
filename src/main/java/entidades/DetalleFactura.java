package entidades;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "DetalleFactura")

public class DetalleFactura implements Serializable {

    // Declaraciones

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "Subtotal")
    private int subtotal;

    // Relación muchos a uno con Articulo
    // @JoinColumn define la columna de la clave foránea
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

    // Relación muchos a uno con Factura
    // @JoinColumn define la columna de la clave foránea
    // Eliminar en caso de querer unidireccional
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_factura")
    private Factura factura;
}
