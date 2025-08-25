package lk.ijse.supermarketfx.config;

import lk.ijse.supermarketfx.entity.Customer;
import lk.ijse.supermarketfx.entity.Item;
import lk.ijse.supermarketfx.entity.Order;
import lk.ijse.supermarketfx.entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 8/18/2025 3:04 PM
 * Project: supermarket-orm
 * --------------------------------------------
 **/

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure(); // 1 (xml) load configuration

        // 2 add entity classes
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(OrderDetail.class);

        // 3 create session factory
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ?
                factoryConfiguration = new FactoryConfiguration()
                :
                factoryConfiguration;
    }

    public Session getSession() {
        Session session = sessionFactory.openSession();
        return session;
    }

    // return the same session object for the current session
    // thread bound session
    // auto close happens on transaction commit or rollback
    // recommend for layered dao + service(bo) architecture
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    // session is not Thread safe
    // session factory is Thread safe, immutable
}
