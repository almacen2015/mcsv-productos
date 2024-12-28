package backend.mcsvproductos.controllers;

import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;
import backend.mcsvproductos.services.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService service;

    @Autowired
    private ObjectMapper objectMapper;

    // Test methods here
    @Test
    void testAgregarProducto() throws Exception {
        // Arrange
        ProductoDtoRequest dto = new ProductoDtoRequest("Producto 1", "Descripcion 1", 100.0);
        String json = objectMapper.writeValueAsString(dto);
        // Act
        ProductoDtoResponse producto = new ProductoDtoResponse(1, "Producto 1", "Descripcion 1", 100.0, true, LocalDate.now());
        when(service.agregarProducto(any(ProductoDtoRequest.class))).thenReturn(producto);
        // Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

}