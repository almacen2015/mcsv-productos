package backend.mcsvproductos.services.impl;

import backend.mcsvproductos.exceptions.ProductoException;
import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;
import backend.mcsvproductos.models.entities.Producto;
import backend.mcsvproductos.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoServiceImpl service;

    // Test methods here
    @Test
    void testAgregarProducto_DadoPrecioNegativo_RetornaError() {
        // Arrange
        ProductoDtoRequest dto = new ProductoDtoRequest("Producto 1", "Descripcion 1", -100.0);

        // Act & Assert
        assertThrows(ProductoException.class, () -> service.verificarDatos(dto));
    }

    @Test
    void testAgregarProducto_DadoPrecioCero_RetornaError() {
        // Arrange
        ProductoDtoRequest dto = new ProductoDtoRequest("Producto 1", "Descripcion 1", 0.0);

        // Act & Assert
        assertThrows(ProductoException.class, () -> service.verificarDatos(dto));
    }

    @Test
    void testAgregarProducto_DadoDescripcionVacia_RetornaError() {
        // Arrange
        ProductoDtoRequest dto = new ProductoDtoRequest("Producto 1", "", 100.0);

        // Act & Assert
        assertThrows(ProductoException.class, () -> service.verificarDatos(dto));
    }

    @Test
    void testAgregarProducto_DadoNombreVacio_RetornaError() {
        // Arrange
        ProductoDtoRequest dto = new ProductoDtoRequest("", "Descripcion 1", 100.0);

        // Act & Assert
        assertThrows(ProductoException.class, () -> service.verificarDatos(dto));
    }

    @Test
    void testAgregarProducto_DadoParametrosValidos_RetornaProducto() {
        // Arrange
        ProductoDtoRequest dto = new ProductoDtoRequest("Producto 1", "Descripcion 1", 100.0);
        Producto productoGuardado = Producto.builder()
                .id(1)
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .precio(100.0)
                .build();

        // Act
        when(repository.save(any(Producto.class))).thenReturn(productoGuardado);
        ProductoDtoResponse productoDtoResponse = service.agregarProducto(dto);

        // Assert
        assertThat(productoDtoResponse).isNotNull();
        assertThat(productoDtoResponse.id()).isGreaterThan(0);
        assertThat(productoDtoResponse.nombre()).isEqualTo("Producto 1");
        assertThat(productoDtoResponse.descripcion()).isEqualTo("Descripcion 1");
        assertThat(productoDtoResponse.precio()).isEqualTo(100.0);
    }
}