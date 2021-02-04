package domain.model;

import domain.db.ProductRepository;
import domain.db.ProductRepositorySQL;
import domain.db.UserRepository;
import domain.db.UserRepositorySQL;
import domain.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ProductService {
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public ProductService(Properties properties) {
        this.userRepository = new UserRepositorySQL(properties);
        this.productRepository = new ProductRepositorySQL(properties);
    }


    /**
     * Returns a list with all products
     */
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    /**
     * Gives list of vegetarian products
     */
    public List<Product> allVegetarianProducts() {
        return productRepository.getAllVegetarianProducts();
    }

    /**
     * Gives list of all non-vegetarian products
     */
    public List<Product> allNonVegetarianProducts() {
        return productRepository.getAllNonVegetarianProducts();
    }

    public List<Product> sortProduct() {
        return productRepository.sortProducts();
    }

    /**
     * Returns a user based on the name
     */
    public User get(String name){
        return userRepository.get(name);
    }

    /*
    * Get all users
     */
    public List<User> getAllUsers(){
        return userRepository.getAll();
    }

    private UserRepository getUserDb(){
        return userRepository;
    }

    public void add(User user){
        getUserDb().add(user);
    }


    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }
}
