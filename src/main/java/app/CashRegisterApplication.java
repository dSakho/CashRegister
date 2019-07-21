package app;

import org.jdbi.v3.core.Jdbi;

import config.DatabaseConfiguration;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CashRegisterApplication extends Application<DatabaseConfiguration> {
    public static void main(String[] args) throws Exception {
        new CashRegisterApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DatabaseConfiguration> bootstrap) {
        // nothing to do yet
    }
    
    @Override
    public void run(DatabaseConfiguration configuration, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        environment.jersey().register(jdbi);
    }

}