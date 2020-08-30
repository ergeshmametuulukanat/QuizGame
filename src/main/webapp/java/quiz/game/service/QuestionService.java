package quiz.game.service;

import quiz.game.dao.QuestionDao;
import quiz.game.model.Question;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/questions")
public class QuestionService {
    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Question> getQuestions_JSON() {
        return new QuestionDao().getAllQuestions();
    }

    @POST
    @Path("/addQuestion")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addQuestion(Question question) {
        return new QuestionDao().addQuestion(question)
                ? "Question is successfully added"
                : "Something went wrong";
    }

    @PUT
    @Path("/updateQuestion")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public Question updateQuestion(Question question) {
        return new QuestionDao().updateQuestionText(question);
    }

    @GET
    @Path("/{questionId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Question getQuestion(@PathParam("questionId") Integer questionId) {
        return new QuestionDao().getQuestion(questionId);
    }

    @DELETE
    @Path("/{questionId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String deleteQuestion(@PathParam("questionId") Integer questionId) {
        return new QuestionDao()
                .deleteQuestion(questionId)
                ? "A question was successfully deleted"
                : "Something went wrong";
    }
}

