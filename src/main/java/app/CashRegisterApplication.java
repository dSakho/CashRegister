package app;

import config.HelloWorldConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import routes.HelloWorldRoute;

public class CashRegisterApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new CashRegisterApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }
    
    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldRoute resource = new HelloWorldRoute(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        environment.jersey().register(resource);
    }

}