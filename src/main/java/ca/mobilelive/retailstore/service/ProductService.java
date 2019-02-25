package ca.mobilelive.retailstore.service;

import ca.mobilelive.retailstore.exception.ProductNotFoundException;
import ca.mobilelive.retailstore.model.Constants;
import ca.mobilelive.retailstore.model.Product;
import ca.mobilelive.retailstore.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


/**
 * Created by Noush on 10/25/2018.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public Product saveOrUpdateProduct(Product product) {
        if(product.getId() == 0)
            LOGGER.info("Product is creating...");
        else {
            if(!productRepository.existsById(product.getId()))
                LOGGER.info("The product with id {} does not exists! So, it is creating..." , product.getId());
            else
                LOGGER.info("Product is updating...");
        }
        return productRepository.save(product);
    }

    public void deleteProduct(long id) throws ProductNotFoundException {
        if(!productRepository.existsById(id)) {
            LOGGER.error("Exception occurred! There is no product with id {} to delete!", id);
            throw new ProductNotFoundException(String.valueOf(id), Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        productRepository.deleteById(id);
    }

    public Optional<Product> findProduct(long id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }
}
