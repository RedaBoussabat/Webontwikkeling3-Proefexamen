package domain.db;

import domain.model.User;

import java.util.List;

public interface UserRepository {

    /**
     * Returns a user depending on the given name
     */
    User get(String name);

    /**
     * Returns all the users
     */
    List<User> getAll();

    /**
     * Adds a new user to the list of users
     */
    void add(User user);
}
