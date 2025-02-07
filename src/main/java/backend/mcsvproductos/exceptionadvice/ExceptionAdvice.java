package backend.mcsvproductos.exceptionadvice;

import backend.mcsvproductos.exceptions.ProductoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ProductoException.class)
    public ResponseEntity<?> handleProductoException(ProductoException e) {
        if (Objects.equals(e.getMessage(), ProductoException.PRODUCTO_DESCRIPCION_VACIA) ||
                Objects.equals(e.getMessage(), ProductoException.PRODUCTO_NOMBRE_VACIO) ||
                Objects.equals(e.getMessage(), ProductoException.PRODUCTO_PRECIO_INVALIDO) ||
                Objects.equals(e.getMessage(), ProductoException.ID_INVALIDO)) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
