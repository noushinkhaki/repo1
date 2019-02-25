package ca.mobilelive.retailstore.controller;

import ca.mobilelive.retailstore.exception.ProductNotFoundException;
import ca.mobilelive.retailstore.model.Constants;
import ca.mobilelive.retailstore.model.Product;
import ca.mobilelive.retailstore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by Noush on 10/25/2018.
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = "/createProduct")
    public ResponseEntity<Product> createProduct(@RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("quantity") int quantity,
                                      @RequestParam("unitPrice") String unitPrice) {
        Product product = makeProductModel(null, name, description, quantity, unitPrice);
        Product createdProduct = productService.saveOrUpdateProduct(product);
        LOGGER.info("Product was created successfully!");
        return new ResponseEntity<Product>(createdProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestParam("id") long id,
                                      @RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("quantity") int quantity,
                                      @RequestParam("unitPrice") String unitPrice) {
        Product product = makeProductModel(id, name, description, quantity, unitPrice);
        Product updatedProduct = productService.saveOrUpdateProduct(product);
        LOGGER.info("Product was saved successfully!");
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteProduct")
    public String deleteProduct(@RequestParam("id") long id) {
        try {
            productService.deleteProduct(id);
        } catch (ProductNotFoundException e) {
            LOGGER.error("No product found to delete!");
            return e.getMessage();
        }
        LOGGER.info("Product was deleted successfully!");
        return Constants.SUCCESSFUL_DELETE_MESSAGE;
    }

    @RequestMapping(value = "/findProduct")
    public String findProduct(@RequestParam("id") long id) {
        Optional<Product> product = productService.findProduct(id);
        if(!product.isPresent()) {
            LOGGER.info("There is no product with id {}", id);
            return Constants.NO_PRODUCT_FOUND_MESSAGE;
        }
        LOGGER.info(product.toString());
        return product.toString();
    }

    @RequestMapping(value="/list")
    public String getProducts() {
        Iterable<Product> productList = productService.getProducts();
        LOGGER.info(productList.toString());
        if (Objects.isNull(productList) || !productList.iterator().hasNext()) {
            LOGGER.info("There is no product in the database.");
            return Constants.NO_PRODUCT_LIST_MESSAGE;
        }
        return productList.toString();
    }

    private Product makeProductModel(Long id, String name, String description, int quantity, String unitPrice) {
        Product product = new Product();
        if(id != null)
            product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setUnitPrice(unitPrice);
        LOGGER.info("Product model was created, {}", product.toString());
        return product;
    }

}
