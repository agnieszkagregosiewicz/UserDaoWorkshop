package org.workshop;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_QUERY = "INSERT INTO workshop.users (name, password, email) VALUES (?, ?, ?)";
    private static final String FIND_QUERY = "SELECT * FROM workshop.users WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM workshop.users WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE workshop.users SET name = ?, password = ?, email = ? WHERE id = ?";
    private static final String FINDALL_QUERY = "SELECT * FROM workshop.users";

    public static User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, hashPassword(user.getPassword()));
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no generated hey obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User read(int id) {
        User user = new User();
        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(FIND_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void update(User user) throws IndexOutOfBoundsException {
        try (Connection conn = DbUtil.getConnection()) {
            if (exist(user.getId())) {
                PreparedStatement preparedStmt = conn.prepareStatement(UPDATE_QUERY);
                preparedStmt.setString(1, user.getName());
                preparedStmt.setString(2, hashPassword(user.getPassword()));
                preparedStmt.setString(3, user.getEmail());
                preparedStmt.setInt(4, user.getId());
                preparedStmt.executeUpdate();
                System.out.println("Update done.");
            } else throw new IndexOutOfBoundsException("Id " + user.getId() + " does not exist.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User[] findAll() throws SQLException {
        User[] users = new User[0];
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FINDALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                System.out.println(user.getId());
                user.setName(resultSet.getString("name"));
                System.out.println(user.getName());
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                users = Arrays.copyOf(users, users.length + 1);
                users[users.length - 1] = user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public static void delete(int id) throws IndexOutOfBoundsException {
        try (Connection conn = DbUtil.getConnection()) {
            if (exist(id)) {
                PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
                statement.setInt(1, id);
                statement.executeUpdate();
                System.out.println("Row " + id + " successfully removed.");
            } else throw new IndexOutOfBoundsException("Row " + id + " does not exist");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean exist(int id) {
        return read(id) != null ? true : false;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());

    }
}

