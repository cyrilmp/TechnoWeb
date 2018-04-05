package Controller.TokenSystem;


public interface IJwtTokenService {

    String tokenGenerator(String username, String password);
}
