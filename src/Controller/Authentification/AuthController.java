package Controller.Authentification;

import Controller.Bootstrapper.AppModule;
import Model.DataAccess.UserDAO;
import Model.DataAccess.IUserDAO;
import Model.BusinessObject.User;
import Controller.TokenSystem.IJwtTokenService;
import Controller.TokenSystem.Validation.ITokenValidator;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class AuthController {

    final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController() {

        Injector injector = Guice.createInjector(new AppModule());
        IJwtTokenService jwtAuthService = injector.getInstance(IJwtTokenService.class);
        ITokenValidator tokenValidation = injector.getInstance(ITokenValidator.class);
        IUserDAO userDao = injector.getInstance(UserDAO.class);

        Gson gson = new Gson();

        before("/protected/*", (request, response) -> {

            String xApiToken = request.headers("X-API-TOKEN");

            if (xApiToken != null) {
                boolean result = tokenValidation.validate(xApiToken, userDao);

                if (!result) {
                    halt(401, "Unauthorized");
                }

            } else {
                halt(401, "header token not found");
            }

        });

        post("/token", "application/json", (request, response) -> {

            if (!request.queryParams().contains("username") || request.queryParams().size() != 2 || !request.queryParams().contains("password")) {
                return gson.toJson("Invalid parameter");
            }
            String result = null;
            try {
                String username = request.queryParams("username");
                String password = request.queryParams("password");
                Map<String, String> keyValuePair;
                if (username != null && password != null)
                {
                    User user = userDao.findByUsernameAndPassword(username, password);
                    /* if(user != null){*/
                        String token = jwtAuthService.tokenGenerator(username, password);
                        keyValuePair = new HashMap<>();
                        keyValuePair.put("token", token);
                        result = gson.toJson(keyValuePair);
                    /*}else{
                        response.status(401);
                        result = gson.toJson("Unauthorized");
                    }*/

                }

            } catch (JsonSyntaxException e) {
                logger.error(e.getMessage());
                response.status(400);
                result = gson.toJson("INVALID JSON");
            }
            catch (Exception e) {
                logger.error(e.getMessage());
                response.status(500);
                result = gson.toJson(e.getMessage());
            }
            return result;
        });

        //registration
        post("/user", "application/json", (request, response) -> {

            if (!request.queryParams().contains("name")
                    || request.queryParams().size() != 5
                    || !request.queryParams().contains("firstname")
                    || !request.queryParams().contains("role")
                    || !request.queryParams().contains("password")
                    || !request.queryParams().contains("username")) {
                return gson.toJson("Invalid parameter");
            }

            String name = request.queryParams("name");
            String firstname = request.queryParams("firstname");
            String role = request.queryParams("role");
            String username = request.queryParams("username");
            String password = request.queryParams("password");

            String result;
            try {
                Map<String, Object> user;
                User userModel = new User(name, firstname, role, username, password);
                userDao.add(userModel);
                user = new HashMap<>();
                user.put("Registered:", userModel.getUsername());
                response.status(201);
                response.type("application/json");
                result = gson.toJson(user);

            } catch (JsonSyntaxException e) {
                logger.error(e.getMessage());
                response.status(400);
                result =gson.toJson( "INVALID JSON");
            }
            catch (Exception e) {
                logger.error(e.getMessage());
                response.status(500);
                result = gson.toJson( e.getMessage());
            }

            return result;
        });

    }
}
