package Controller.TokenSystem.Validation;

import Model.DataAccess.IUserDAO;

/**
 * Created by previousdeveloper on 15.09.2015.
 */
public interface ITokenValidator {

    boolean validate(String token, IUserDAO userDAO);
}
