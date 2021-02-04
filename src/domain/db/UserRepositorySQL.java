package domain.db;

import domain.model.Role;
import domain.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepositorySQL implements UserRepository {

    private Properties properties = new Properties();
    private String url = "jdbc:postgresql://databanken.ucll.be:51920/2TX37";

    public UserRepositorySQL(Properties properties) {
        this.properties = properties;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }


    @Override
    public User get(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new DbException("No name is given");
        }
        String sql = "SELECT * FROM r0613846_proef.user WHERE name = ?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getPersonFromResultSet(resultSet);
        } catch (SQLException e){
            throw new DbException(e.getMessage(), e);
        }
    }

    private User getPersonFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String roleString = resultSet.getString("role");
        Role role = Role.valueOf(roleString.toUpperCase());
        return new User(name, role);
    }

    /**
     * Returns a list of all users
     */
    @Override
    public List<User> getAll() {
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement= connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM r0613846_proef.user");
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
    private List<User> createListFromResultset(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String roleString = resultSet.getString("role");
            Role role = Role.valueOf(roleString.toUpperCase());
            User user = new User(name, role);
            users.add(user);
        }
        return users;
    }

    @Override
    public void add(User user) {
        if(user == null) {
            throw new DbException("No user given");
        }
        String sql = "INSERT INTO user " + "VALUES (?,?)";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getRole().getStringValue());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }
}
