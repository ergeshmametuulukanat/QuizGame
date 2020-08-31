package quiz.game.service;

import quiz.game.dao.QuestionAndAnswerDao;
import quiz.game.model.QuestionAndAnswer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/questionAndAnswer")
public class QuestionAndAnswerService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<QuestionAndAnswer> getQuestionAndAnswer_JSON() {
        return new QuestionAndAnswerDao().getAllQuestionAndAnswer();
    }

    @GET
    @Path("/{questionAndAnswerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public QuestionAndAnswer getQuestionAndAnswer(@PathParam("questionAndAnswerId") Integer questionAndAnswerId) {
        return new QuestionAndAnswerDao().getQuestionAndAnswer(questionAndAnswerId);
    }

    @POST
    @Path("/addQuestionAndAnswer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
        return new QuestionAndAnswerDao().addQuestionAndAnswer(questionAndAnswer)
                ? "Question and Answer is successfully added"
                : "Something went wrong";
    }

    @DELETE
    @Path("/{questionAndAnswerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String deleteQuestionAndAnswer(@PathParam("questionAndAnswerId") Integer questionAndAnswerId) {
        return new QuestionAndAnswerDao().deleteQuestionAndAnswer(questionAndAnswerId)
                ? "Successfully deleted" : "Something went wrong";
    }
}
