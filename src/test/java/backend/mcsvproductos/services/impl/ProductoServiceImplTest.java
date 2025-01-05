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

import java.util.List;

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

    @Test
    void testBuscarPorId_ProductoExiste_RetornaProducto() {
        // Arrange
        Producto producto = Producto.builder()
                .id(1)
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .precio(100.0)
                .build();

        // Act
        when(repository.findById(1)).thenReturn(java.util.Optional.of(producto));
        ProductoDtoResponse productoDtoResponse = service.buscarPorId(1);

        // Assert
        assertThat(productoDtoResponse).isNotNull();
        assertThat(productoDtoResponse.id()).isEqualTo(1);
        assertThat(productoDtoResponse.nombre()).isEqualTo("Producto 1");
        assertThat(productoDtoResponse.descripcion()).isEqualTo("Descripcion 1");
        assertThat(productoDtoResponse.precio()).isEqualTo(100.0);
    }

    @Test
    void testBuscarPorId_ProductoNoExiste_RetornaNull() {
        // Arrange

        // Act
        when(repository.findById(1)).thenReturn(java.util.Optional.empty());
        ProductoDtoResponse producto = service.buscarPorId(1);

        // Assert
        assertThat(producto).isNull();
    }

    @Test
    void testListar_SinProductosEnBD_RetornaListaVacia() {
        // Arrange

        // Act
        when(repository.findAll()).thenReturn(List.of());
        List<ProductoDtoResponse> productos = service.listar();

        // Assert
        assertThat(productos).isNotNull();
        assertThat(productos).isEmpty();
    }

    @Test
    void testListar_ProductosEnBD_RetornaListaProductos() {
        // Arrange
        Producto producto1 = Producto.builder()
                .id(1)
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .precio(100.0)
                .build();
        Producto producto2 = Producto.builder()
                .id(2)
                .nombre("Producto 2")
                .descripcion("Descripcion 2")
                .precio(200.0)
                .build();

        // Act
        when(repository.findAll()).thenReturn(List.of(producto1, producto2));
        List<ProductoDtoResponse> productos = service.listar();

        // Assert
        assertThat(productos).isNotNull();
        assertThat(productos).hasSize(2);
        assertThat(productos.get(0).id()).isEqualTo(1);
        assertThat(productos.get(0).nombre()).isEqualTo("Producto 1");
        assertThat(productos.get(0).descripcion()).isEqualTo("Descripcion 1");
        assertThat(productos.get(0).precio()).isEqualTo(100.0);
        assertThat(productos.get(1).id()).isEqualTo(2);
        assertThat(productos.get(1).nombre()).isEqualTo("Producto 2");
        assertThat(productos.get(1).descripcion()).isEqualTo("Descripcion 2");
        assertThat(productos.get(1).precio()).isEqualTo(200.0);
    }

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