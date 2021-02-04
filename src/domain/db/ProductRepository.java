package domain.db;

import domain.model.Product;

import java.util.List;

public interface ProductRepository {

    /**
     * Returns a list with all products
     */
    List<Product> getAll();

    /**
     * Gives list of all vegetarian products
     */
    List<Product> getAllVegetarianProducts();

    /**
     * Gives list of all non-vegetarian products
     */
    List<Product> getAllNonVegetarianProducts();

    /**
     * Gives products but sorted by price
     */
    List<Product> sortProducts();

}
