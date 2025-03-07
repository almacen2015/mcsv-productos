package backend.mcsvproductos.exceptions;

public class ProductoException extends RuntimeException {
    public static final String PRODUCT_NAME_EMPTY = "Name can't be empty";
    public static final String PRODUCT_DESCRIPTION_EMPTY = "Description can't be empty";
    public static final String PRODUCT_PRICE_INVALID = "The product price cannot be less than or equal to 0";
    public static final String INVALID_ID = "Id invalid";
    public static final String AMOUNT_INVALID = "Amount can't be less than or equal to 0";
    public static final String INVALID_STOCK = "Stock can't be less than or equal to 0";
    public static final String PRODUCT_NOT_FOUND = "Product not found";

    public ProductoException(String message) {
        super(message);
    }
}
