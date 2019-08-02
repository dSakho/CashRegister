package app;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import config.ServerConfiguration;
import dao.ProductDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.ProductResource;
import service.products.ProductService;
import service.products.ProductServiceImpl;

public class CashRegisterApplication extends Application<ServerConfiguration> {
    public static void main(String[] args) throws Exception {
        new CashRegisterApplication().run(args);
    }

    @Override
    public String getName() {
        return "Cash Register";
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        // nothing to do yet
    }
    
    @Override
    public void run(ServerConfiguration configuration, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        
        final ProductDAO productDao = jdbi.onDemand(ProductDAO.class);
        final ProductService productService = new ProductServiceImpl(productDao);
     
        
        environment.jersey().register(new ProductResource(productService));
        
    }

}