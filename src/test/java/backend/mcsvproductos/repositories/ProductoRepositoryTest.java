package backend.mcsvproductos.repositories;

import backend.mcsvproductos.models.entities.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void testBuscarPorNombre_DadoNombreExistente_RetornaProducto() {
        // Arrange
        Producto producto = Producto.builder()
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .precio(100.0)
                .build();

        productoRepository.save(producto);

        // Act
        Producto productoEncontrado = productoRepository.findByNombre("Producto 1").orElse(null);

        // Assert
        assertNotNull(productoEncontrado);
        assertEquals(producto.getId(), productoEncontrado.getId());
        assertEquals("Producto 1", productoEncontrado.getNombre());
        assertEquals("Descripcion 1", productoEncontrado.getDescripcion());
        assertEquals(100.0, productoEncontrado.getPrecio());
    }

    @Test
    public void testObtenerPorId_DadoIdExistente_RetornaProducto() {
        // Arrange
        Producto producto = Producto.builder()
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .precio(100.0)
                .build();

        productoRepository.save(producto);

        // Act
        Producto productoEncontrado = productoRepository.findById(producto.getId()).orElse(null);

        // Assert
        assertNotNull(productoEncontrado);
        assertEquals(producto.getId(), productoEncontrado.getId());
        assertEquals("Producto 1", productoEncontrado.getNombre());
        assertEquals("Descripcion 1", productoEncontrado.getDescripcion());
        assertEquals(100.0, productoEncontrado.getPrecio());
    }

    @Test
    public void testListar_DadoProductosEnBD_RetornaListaProductos() {
        // Arrange
        Producto producto1 = Producto.builder()
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .precio(100.0)
                .build();

        Producto producto2 = Producto.builder()
                .nombre("Producto 2")
                .descripcion("Descripcion 2")
                .precio(200.0)
                .build();

        productoRepository.save(producto1);
        productoRepository.save(producto2);

        // Act
        List<Producto> productos = productoRepository.findAll();

        // Assert
        assertNotNull(productos);
        assertEquals(2, productos.size());
    }

    @Test
    public void testGuardar_DadoProductoValido_RetornaProductoGuardado() {
        // Arrange
        Producto producto = Producto.builder()
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .precio(100.0)
                .build();


        // Act
        productoRepository.save(producto);

        // Assert
        assertNotNull(producto);
        assertNotNull(producto.getId());
        assertEquals("Producto 1", producto.getNombre());
        assertEquals("Descripcion 1", producto.getDescripcion());
        assertEquals(100.0, producto.getPrecio());
    }
}