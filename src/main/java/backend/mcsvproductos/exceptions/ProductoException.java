package backend.mcsvproductos.exceptions;

public class ProductoException extends RuntimeException {
    public static final String PRODUCTO_NOMBRE_VACIO = "El nombre del producto no puede estar vacío";
    public static final String PRODUCTO_DESCRIPCION_VACIA = "La descripción del producto no puede estar vacía";
    public static final String PRODUCTO_PRECIO_INVALIDO = "El precio del producto no puede ser menor o igual a 0";
    public static final String ID_INVALIDO = "El id del producto no puede ser menor o igual a 0";
    public static final String CANTIDAD_INVALIDA = "La cantidad del producto no puede ser menor o igual a 0";
    public static final String STOCK_INVALIDO = "El stock del producto no puede ser menor a 0";

    public ProductoException(String message) {
        super(message);
    }
}
