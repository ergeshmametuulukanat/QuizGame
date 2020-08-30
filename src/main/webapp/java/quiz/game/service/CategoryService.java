package quiz.game.service;

import quiz.game.dao.CategoryDao;
import quiz.game.dao.UserDao;
import quiz.game.model.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryService {
    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Category> getCategories_JSON(@HeaderParam("user-key") String login,
                                             @HeaderParam("password-key") String password) {
        return !new UserDao().authorize(login, password) ? null : new CategoryDao().getAllCategories();
    }

    @GET
    @Path("/{categoryId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Category getCategory(@PathParam("categoryId") Integer categoryId) {
        return new CategoryDao().getCategory(categoryId);
    }

    @PUT
    @Path("/updateCategory")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public Category updateCategory(Category category) {
        return new CategoryDao().updateCategoryName(category);
    }

    @POST
    @Path("/addCategory")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addCategory(Category category) {
        return new CategoryDao().addCategory(category) ? "Category is added" : "Something went wrong";
    }

    @DELETE
    @Path("/{categoryId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String deleteCategory(@PathParam("categoryId") Integer categoryId) {
        return new CategoryDao().deleteCategory(categoryId) ? "Successfully deleted" : "Something went wrong";
    }
}

