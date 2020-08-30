package quiz.game.dao;

import quiz.game.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends DBConnector {

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM users";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            User user;
            while (rs.next()) {
                user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email")
                );
                user.setPassword(hidePassword(user));
                users.add(user);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return users;
    }

    public User getUser(int userId) {
        User user = null;
        String SQL = "SELECT * FROM users where user_id = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        assert user != null;
        user.setPassword(hidePassword(user));
        return user;
    }

    public boolean addUser(User user) {
        String SQL = "insert into users (user_name, password, email, date_of_registration) values (?,?,?,NOW())";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteUser(int userId) {
        String SQL = "delete from users where user_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public String hidePassword(User user) {
        StringBuilder revPass = new StringBuilder("");
        char[] chars = user.getPassword().toCharArray();
        for (int i = user.getPassword().length(); i > 0; i--) {
            revPass.append(chars[i - 1]);
        }
        revPass.append(chars[0]);
        return revPass.toString();
    }

    public boolean checkLogin(String login) {
        UserDao userDao = new UserDao();
        for (User user : userDao.getAllUsers()) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
