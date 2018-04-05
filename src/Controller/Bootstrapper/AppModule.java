package Controller.Bootstrapper;

import Controller.DatabaseConnection.ISql2oConnection;
import Controller.DatabaseConnection.Sql2oConnection;
import Model.DataAccess.UserDAO;
import Model.DataAccess.IUserDAO;
import Controller.TokenSystem.IJwtTokenService;
import Controller.TokenSystem.JwtTokenServiceImpl;
import Controller.Util.TimeProviderImpl;
import Controller.Util.ITimeProvider;
import Controller.Util.IKeyGenerator;
import Controller.Util.IKeyGeneratorImpl;
import Controller.TokenSystem.Validation.ITokenValidator;
import Controller.TokenSystem.Validation.TokenValidator;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;


public class AppModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(ITimeProvider.class).to(TimeProviderImpl.class).in(Singleton.class);
        bind(IKeyGenerator.class).to(IKeyGeneratorImpl.class).in(Singleton.class);
        bind(IJwtTokenService.class).to(JwtTokenServiceImpl.class).in(Singleton.class);
        bind(ISql2oConnection.class).to(Sql2oConnection.class);
        bind(ITokenValidator.class).to(TokenValidator.class).in(Singleton.class);
        bind(IUserDAO.class).to(UserDAO.class).asEagerSingleton();

    }
}
