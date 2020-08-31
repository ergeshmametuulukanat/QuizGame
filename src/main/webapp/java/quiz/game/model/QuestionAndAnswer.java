package quiz.game.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "questionAndAnswer")
public class QuestionAndAnswer {
    private int questionAndAnswerId;
    private int quizId;
    private int questionId;
    private int answerId;
    private boolean isCorrect;

    public QuestionAndAnswer() {
    }

    public QuestionAndAnswer(int id, int quizId, int questionId, int answerId, boolean isCorrect) {
        this.questionAndAnswerId = id;
        this.quizId = quizId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrect = isCorrect;
    }

    public int getQuestionAndAnswerId() {
        return questionAndAnswerId;
    }

    public void setQuestionAndAnswerId(int questionAndAnswerId) {
        this.questionAndAnswerId = questionAndAnswerId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
