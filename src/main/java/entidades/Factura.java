package entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Factura")

public class Factura implements Serializable {

    // Declaraciones

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="Fecha")
    private String fecha;
    @Column(name = "Numero")
    private int numero;
    @Column(name = "Total")
    private int total;

    // Relación muchos a uno con Cliente
    // @JoinColumn define la columna de la clave foránea
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    // Descomentar en caso de querer que sea unidirrecional
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();

    // Caso bidireccional
    // Relación uno a muchos con DetalleFactura
    // mappedBy indica que la propiedad en DetalleFactura que se usa para la relación es 'factura'
    @Builder.Default
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();

}
