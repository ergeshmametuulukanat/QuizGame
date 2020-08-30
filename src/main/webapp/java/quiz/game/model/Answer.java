package quiz.game.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "answer")
public class Answer {
    private int answerId;
    private int questionId;
    private String text;
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(int id, int questionId, String text, boolean isCorrect) {
        this.answerId = id;
        this.questionId = questionId;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
