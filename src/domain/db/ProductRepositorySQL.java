package domain.db;

import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductRepositorySQL implements ProductRepository {

    private Properties properties = new Properties();
    private String url = "jdbc:postgresql://databanken.ucll.be:51920/2TX37";

    public ProductRepositorySQL(Properties properties) {
        this.properties = properties;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    /**
     * Returns a list with all products
     */
    @Override
    public List<Product> getAll() {
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM r0613846_proef.veggie");
            return createListFromResultset(resultSet);
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    /**
     * Creates a list of products from the given resultset
     *
     * @throws SQLException
     */
    private List<Product> createListFromResultset(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            double price = Double.parseDouble(resultSet.getString("price"));
            boolean vegetarian = resultSet.getBoolean("vegetarian");
            Product product = new Product(name, price, vegetarian);
            products.add(product);
        }
        return products;
    }


    /**
     * Gives list of all vegetarian products
     */
    @Override
    public List<Product> getAllVegetarianProducts() {
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM r0613846_proef.veggie WHERE vegetarian = 'true'");
            return createListFromResultset(resultSet);
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    /**
     * Gives list of all non-vegetarian products
     */
    @Override
    public List<Product> getAllNonVegetarianProducts() {
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM r0613846_proef.veggie WHERE vegetarian = 'false'");
            return createListFromResultset(resultSet);
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }


    /**
     * Gives products ordered by price
     */
    @Override
    public List<Product> sortProducts() {
        try(Connection connection = DriverManager.getConnection(url, properties);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM r0613846_proef.veggie ORDER BY price DESC ")){
            ResultSet resultSet = statement.executeQuery();
            return createListFromResultset(resultSet);
        } catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }


}
