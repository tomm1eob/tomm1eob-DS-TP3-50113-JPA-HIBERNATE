package main;

import entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tp3-50113-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            // Inicia una transacción, lo que significa que cualquier cambio en la base de datos
            // se realiza dentro de una transacción que se puede confirmar o revertir.
            entityManager.getTransaction().begin();

            // Se crea una nueva instancia de Factura, que es una entidad mapeada en la base de datos.
            // Luego se establecen los valores de número y fecha para esta factura.
            Factura factura1 = new Factura();
            factura1.setNumero(12);
            factura1.setFecha("10/08/2024");

            // Se construye una nueva instancia de Domicilio usando el patrón Builder,
            // y se establecen los valores de la calle y el número.
            Domicilio dom = Domicilio.builder().nombreCalle("San Martin").numero(1222).build();

            // Se construye un nuevo cliente, también con el patrón Builder, asignándole un nombre,
            // apellido y DNI. Luego se relaciona el cliente con el domicilio.
            Cliente cliente1 = Cliente.builder().nombre("Pablo").apellido("Muñoz").dni(453433443).build();
            cliente1.setDomicilio(dom);
            dom.setCliente(cliente1);

            // La factura también se asocia con este cliente.
            factura1.setCliente(cliente1);

            // Se crean tres categorías utilizando el patrón Builder.
            Categoria perecederos = Categoria.builder().denomincacion("Perecederos").build();
            Categoria lacteos = Categoria.builder().denomincacion("Lacteos").build();
            Categoria limpieza = Categoria.builder().denomincacion("Limpieza").build();

            // Se crean dos artículos con sus denominaciones, precios y cantidades.
            Articulo articulo1 = Articulo.builder().cantidad(200).denominacion("Yogurt Frutilla").precio(150).build();
            Articulo articulo2 = Articulo.builder().cantidad(120).denominacion("Suavizante de ropa").precio(400).build();

            // Se agregan las categorías a cada artículo, y viceversa, estableciendo las relaciones bidireccionales
            // entre las categorías y los artículos.
            articulo1.getCategorias().add(perecederos);
            articulo1.getCategorias().add(lacteos);
            perecederos.getArticulos().add(articulo1);
            lacteos.getArticulos().add(articulo1);

            articulo2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(articulo2);

            // Se crea un detalle de factura para el primer artículo, indicando la cantidad comprada
            // y el subtotal del artículo en la factura. Luego, se establece la relación bidireccional
            // entre el artículo, el detalle de la factura y la factura.
            DetalleFactura detalleFactura1= new DetalleFactura();
            detalleFactura1.setArticulo(articulo1);
            detalleFactura1.setCantidad(2);
            detalleFactura1.setSubtotal(300);
            articulo1.getDetalleFacturas().add(detalleFactura1);
            factura1.getDetalleFacturas().add(detalleFactura1);
            detalleFactura1.setFactura(factura1);

            // Se crea otro detalle de factura para el segundo artículo, con una lógica similar.
            DetalleFactura detalleFactura2 = new DetalleFactura();
            detalleFactura2.setArticulo(articulo2);
            detalleFactura2.setCantidad(3);
            detalleFactura2.setSubtotal(1200);
            articulo2.getDetalleFacturas().add(detalleFactura2);
            factura1.getDetalleFacturas().add(detalleFactura2);
            detalleFactura2.setFactura(factura1);

            // Se establece el total de la factura sumando los subtotales de los detalles.
            factura1.setTotal(1500);

            // Persiste (guarda) la factura y todas sus relaciones asociadas (cliente, domicilio, artículos,
            // detalles de factura, etc.) en la base de datos.
            entityManager.persist(factura1);

            // Limpia el EntityManager asegurando que todas las entidades se sincronicen con la base de datos.
            entityManager.flush();

            // Confirma la transacción, lo que significa que los cambios se guardan de manera definitiva
            // en la base de datos.
            entityManager.getTransaction().commit();


            // Consultar y mostrar la entidad persistida
            Factura retrievedFactura = entityManager.find(Factura.class, factura1.getId());
            System.out.println("Retrieved Factura: " + retrievedFactura.getId());

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Factura");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
