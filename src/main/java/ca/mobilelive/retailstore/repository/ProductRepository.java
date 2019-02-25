package ca.mobilelive.retailstore.repository;

import ca.mobilelive.retailstore.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
* Created by Noush on 10/25/2018.
*/
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
