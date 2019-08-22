package app;

import org.jdbi.v3.core.Jdbi;

import authorization.User;
import config.ServerConfiguration;
import dao.CustomersDAO;
import dao.ProductsDAO;
import dao.SalesDAO;
import dao.SupplierDAO;
import dao.TransactionsDAO;
import dao.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.CustomerResource;
import resources.ProductResource;
import resources.SaleResource;
import resources.SupplierResource;
import resources.TransactionResource;
import service.customers.CustomerService;
import service.customers.CustomerServiceImpl;
import service.products.ProductService;
import service.products.ProductServiceImpl;
import service.sales.SalesService;
import service.sales.SalesServiceImpl;
import service.suppliers.SupplierService;
import service.suppliers.SupplierServiceImpl;
import service.transactions.TransactionService;
import service.transactions.TransactionServiceImpl;

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
        
        final UserDAO userdao = jdbi.onDemand(UserDAO.class);
        
        // Authentication
        environment.jersey().register(new AuthDynamicFeature
        		(new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new authorization.Authorizer(userdao))
                        .setRealm("BASIC-AUTH-REALM")
                        .buildAuthFilter()));
        
//        environment.jersey().register(AuthFactory.binder(
//                new BasicAuthFactory<>(
//                        new GreetingAuthenticator(configuration.getLogin(),
//                                configuration.getPassword()),
//                        "SECURITY REALM",
//                        User.class)));
        // Resources
        final ProductsDAO productDao = jdbi.onDemand(ProductsDAO.class);
        final ProductService productService = new ProductServiceImpl(productDao);
     
        final TransactionsDAO TransactionsDAO = jdbi.onDemand(TransactionsDAO.class);
        final TransactionService transactionService = new TransactionServiceImpl(TransactionsDAO);
        
        final SalesDAO salesDAO = jdbi.onDemand(SalesDAO.class);
        final SalesService salesService = new SalesServiceImpl(salesDAO);
        
        final SupplierDAO supplierDAO = jdbi.onDemand(SupplierDAO.class);
        final SupplierService supplierService = new SupplierServiceImpl(supplierDAO);
        
        final CustomersDAO customersDAO = jdbi.onDemand(CustomersDAO.class);
        final CustomerService customerService = new CustomerServiceImpl(customersDAO);
        
        environment.jersey().register(new ProductResource(productService));
        environment.jersey().register(new TransactionResource(transactionService));
        environment.jersey().register(new SaleResource(salesService, productService));
        environment.jersey().register(new SupplierResource(supplierService));
        environment.jersey().register(new CustomerResource(customerService));
        
    }

}