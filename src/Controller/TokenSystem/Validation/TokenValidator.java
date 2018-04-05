package Controller.TokenSystem.Validation;
import Model.BusinessObject.User;
import Model.DataAccess.IUserDAO;
import Controller.Util.Constant;
import Controller.Util.ITimeProvider;
import com.google.inject.Inject;
import io.jsonwebtoken.Jwts;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class TokenValidator implements ITokenValidator {

    private ITimeProvider timeProvider;

    @Inject
    public TokenValidator(ITimeProvider timeProvider) {

        this.timeProvider = timeProvider;
    }

    public boolean validate(String token, IUserDAO userDAO) {

        boolean valid = false;


        Object username = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token)
                .getBody().get("username");

        Object password = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token)
                .getBody().get("password");

        Object expireTime = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token)
                .getBody().get("expireTime");

        Long currentTimeInMilisecond = timeProvider.getCurrentTime();

        User user = userDAO.findByUsernameAndPassword(username.toString(),password.toString());

        if (user != null && (Long) expireTime > currentTimeInMilisecond) {
            valid = true;
        }

        return valid;
    }
}
