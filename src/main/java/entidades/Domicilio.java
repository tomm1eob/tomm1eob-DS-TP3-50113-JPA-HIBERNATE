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
@Table(name = "Domicilio")

public class Domicilio implements Serializable {

    // Declaraciones

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NombreCalle")
    private String nombreCalle;
    @Column(name = "Numero")
    private int numero;

    // Relación uno a uno con Cliente
    // mappedBy indica que la propiedad en Cliente que se usa para la relación es 'domicilio'
    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;
}
