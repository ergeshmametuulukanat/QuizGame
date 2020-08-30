package quiz.game.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "questions")
public class Question {
    private int questionId;
    private String text;
    private String description;
    private int categoryId;
    private int grade;

    public Question() {
    }

    public Question(int questionId, String text, String description, int categoryId, int grade) {
        this.questionId = questionId;
        this.text = text;
        this.description = description;
        this.categoryId = categoryId;
        this.grade = grade;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
