package backend.mcsvproductos.controllers;

import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;
import backend.mcsvproductos.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @Operation(summary = "Agregar producto", description = "Agrega un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto agregado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ProductoDtoResponse> agregarProducto(@RequestBody ProductoDtoRequest dto) {
        ProductoDtoResponse producto = productoService.agregarProducto(dto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar productos", description = "Lista todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos listados")
    })
    public ResponseEntity<List<ProductoDtoResponse>> listar() {
        List<ProductoDtoResponse> productos = productoService.listar();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
