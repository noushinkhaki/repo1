package ca.mobilelive.retailstore.exception;

import java.util.function.Supplier;

/**
 * Created by Noush on 10/26/2018.
 */
public class ProductNotFoundException extends Exception implements Supplier<ProductNotFoundException> {
    private String id;
    private String message;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public ProductNotFoundException get() {
        return this;
    }
}
