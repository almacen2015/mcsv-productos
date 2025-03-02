package backend.mcsvproductos.exceptions.advice;

import backend.mcsvproductos.exceptions.ProductoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    private static final Set<String> ERRORES_VALIDACION = Set.of(
            ProductoException.PRODUCTO_DESCRIPCION_VACIA,
            ProductoException.PRODUCTO_NOMBRE_VACIO,
            ProductoException.PRODUCTO_PRECIO_INVALIDO,
            ProductoException.ID_INVALIDO
    );

    @ExceptionHandler(ProductoException.class)
    public ResponseEntity<?> handleProductoException(ProductoException e) {
        log.error(e.getMessage(), e);
        HttpStatus status = ERRORES_VALIDACION.contains(e.getMessage()) ?
                HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(e.getMessage());
    }
}
