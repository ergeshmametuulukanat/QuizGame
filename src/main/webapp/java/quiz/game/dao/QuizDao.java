package quiz.game.dao;

import quiz.game.model.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizDao extends DBConnector {

    public ArrayList<Quiz> getAllQuiz() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String SQL = "SELECT * FROM quiz";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            Quiz quiz;
            while (rs.next()) {
                quiz = new Quiz(rs.getInt("quiz_id"),
                        rs.getTimestamp("date"),
                        rs.getInt("user_id"),
                        rs.getInt("total_grade")
                );
                quizzes.add(quiz);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return quizzes;
    }

    public Quiz getQuiz(int quizId) {
        Quiz quiz = null;
        String SQL = "SELECT * FROM quiz where quiz_id = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                quiz = new Quiz(rs.getInt("quiz_id"),
                        rs.getTimestamp("date"),
                        rs.getInt("user_id"),
                        rs.getInt("total_grade")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return quiz;
    }

    public boolean deleteQuiz(int quizId) {
        String SQL = "delete from quiz where quiz_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, quizId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
