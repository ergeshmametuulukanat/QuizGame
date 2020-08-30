package quiz.game.service;

import quiz.game.dao.AnswerDao;
import quiz.game.model.Answer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/answers")
public class AnswerService {
    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Answer> getAnswers_JSON() {
        return new AnswerDao().getAllAnswers();
    }

    @POST
    @Path("/addAnswer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public Answer addAnswers(Answer answer) {
        return new AnswerDao().addAnswer(answer);
    }

    @GET
    @Path("/{answerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Answer getAnswer(@PathParam("answerId") Integer answerId) {
        return new AnswerDao().getAnswer(answerId);
    }

    @PUT
    @Path("/updateAnswer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public Answer updateAnswer(Answer answer) {
        return new AnswerDao().updateAnswerText(answer);
    }

    @DELETE
    @Path("/{answerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String deleteAnswer(@PathParam("answerId") Integer answerId) {
        return new AnswerDao().deleteAnswer(answerId) ? "Successfully deleted" : "Something went wrong";
    }
}
