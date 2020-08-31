package quiz.game.service;

import quiz.game.dao.QuizDao;
import quiz.game.model.Quiz;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/quiz")
public class QuizService {
    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Quiz> getQuiz_JSON() {
        return new QuizDao().getAllQuiz();
    }

    @GET
    @Path("/{quizId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Quiz getQuiz(@PathParam("quizId") Integer quizId) {
        return new QuizDao().getQuiz(quizId);
    }

    @DELETE
    @Path("/{quizId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String deleteQuiz(@PathParam("quizId") Integer quizId) {
        return new QuizDao().deleteQuiz(quizId) ? "Successfully deleted" : "Something went wrong";
    }
}

