package quiz.game.dao;

import quiz.game.model.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerDao extends DBConnector {

    public ArrayList<Answer> getAllAnswers() {
        ArrayList<Answer> answers = new ArrayList<>();
        String SQL = "SELECT * FROM answers";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            Answer ans;
            while (rs.next()) {
                ans = new Answer(rs.getInt("answer_id"),
                        rs.getInt("question_id"),
                        rs.getString("text"),
                        rs.getBoolean("is_correct")
                );
                answers.add(ans);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return answers;
    }

    public Answer getAnswer(int answerId) {
        Answer ans = null;
        String SQL = "SELECT * FROM answers where answer_id = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, answerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ans = new Answer(rs.getInt("answer_id"),
                        rs.getInt("question_id"),
                        rs.getString("text"),
                        rs.getBoolean("is_correct")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return ans;
    }

    public Answer addAnswer(Answer answer) {
        String SQL =
                "insert into answers " +
                        "(question_id, text, is_correct) " +
                        "values (?, ?, ?)";
        try (Connection conn = connect()) {
            PreparedStatement stmt =
                    conn.prepareStatement(SQL);
            stmt.setInt(1, answer.getQuestionId());
            stmt.setString(2, answer.getText());
            stmt.setBoolean(3, answer.isCorrect());
            stmt.executeUpdate();
            System.out.println("Successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return answer;
    }

    public Answer updateAnswerText(Answer answer) {
        String SQL = "update answers set text = ? where answer_id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setString(1, answer.getText());
            stmt.setInt(2, answer.getAnswerId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return answer;
    }

    public boolean deleteAnswer(int answerId) {
        String SQL = "delete from answers where answer_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, answerId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
