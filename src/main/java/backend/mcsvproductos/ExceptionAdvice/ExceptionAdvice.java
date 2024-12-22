package backend.mcsvproductos.ExceptionAdvice;

import backend.mcsvproductos.exceptions.ProductoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ProductoException.class)
    public ResponseEntity<?> handleProductoException(ProductoException e) {
        if (Objects.equals(e.getMessage(), ProductoException.PRODUCTO_DESCRIPCION_VACIA) ||
                Objects.equals(e.getMessage(), ProductoException.PRODUCTO_NOMBRE_VACIO) ||
                Objects.equals(e.getMessage(), ProductoException.PRODUCTO_PRECIO_INVALIDO)) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
