package quiz.game.service;

import quiz.game.dao.UserDao;
import quiz.game.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserService {
    @GET
    @Path("/getAllUsers")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getUsers_JSON() {
        return new UserDao().getAllUsers();
    }

    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUser(@PathParam("userId") Integer userId) {
        return new UserDao().getUser(userId);
    }

    @POST
    @Path("/registration")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addUser(User user) {
        UserDao userDao = new UserDao();
        if (userDao.checkLogin(user.getLogin())) {
            return "This login isn't available";
        }
        return userDao.addUser(user) ? "Registration is successfully" : "Something went wrong";
    }

    @DELETE
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String deleteUser(@PathParam("userId") Integer userId) {
        return new UserDao().deleteUser(userId) ? "Successfully deleted" : "Something went wrong";
    }
}
