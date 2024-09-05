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
@Table(name="Cliente")

public class Cliente implements Serializable {

    // Declaraciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Dni", unique = true)
    private int dni;

    // Relación uno a uno con Domicilio
    // @JoinColumn define la columna de la clave foránea
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_domicilio")
    private Domicilio domicilio;

    // Relación uno a muchos con Factura
    // mappedBy indica que la propiedad en Factura que se usa para la relación es 'cliente'
    @Builder.Default
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();
}
