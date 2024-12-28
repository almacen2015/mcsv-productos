package backend.mcsvproductos.repositories;

import backend.mcsvproductos.models.entities.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

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