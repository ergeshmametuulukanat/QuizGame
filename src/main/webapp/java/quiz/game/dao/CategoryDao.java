package quiz.game.dao;

import quiz.game.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao extends DBConnector {

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        String SQL = "SELECT * FROM categories";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            Category cat;
            while (rs.next()) {
                cat = new Category(rs.getInt("category_id"),
                        rs.getString("category_name"));
                categories.add(cat);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return categories;
    }

    public Category getCategory(int categoryId) {
        Category cat = null;
        String SQL = "SELECT * FROM categories where category_id = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cat = new Category(rs.getInt("category_id"),
                        rs.getString("category_name")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cat;
    }

    public boolean addCategory(Category category) {
        String SQL =
                "insert into categories " +
                        "(category_name) " +
                        "values(?)";
        try (Connection conn = connect()) {
            PreparedStatement stmt =
                    conn.prepareStatement(SQL);

            stmt.setString(1, category.getName());
            stmt.executeUpdate();
            System.out.println("Successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public Category updateCategoryName(Category category) {
        String SQL = "update categories set category_name = ? where category_id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getCategoryId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return category;
    }

    public boolean deleteCategory(int categoryId) {
        String SQL = "delete from categories where category_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, categoryId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
