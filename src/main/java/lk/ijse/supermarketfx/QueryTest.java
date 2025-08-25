package lk.ijse.supermarketfx;

import lk.ijse.supermarketfx.config.FactoryConfiguration;
import lk.ijse.supermarketfx.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 8/25/2025 9:17 AM
 * Project: supermarket-orm
 * --------------------------------------------
 **/

public class QueryTest {
    public static void main(String[] args) {
//      Native SQL, HQL, JPQL with Hibernate
        FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

        Session session = factoryConfiguration.getSession();

        // HQL / JPQL
//        System.out.println("============== HQL / JPQL ===============");
//        Query<Customer> query = session.createQuery("from Customer", Customer.class);
//        List<Customer> list = query.list();
//        list.forEach(customer -> {
//            System.out.println(customer.toString());
//        });

        // Native SQL where case
        NativeQuery<Customer> nativeQuery = session.createNativeQuery(
                "select * from customers where customer_name='John Doe'", Customer.class);
        List<Customer> customerList = nativeQuery.list();
        customerList.forEach(customer -> {
            System.out.println("============== Native SQL ===============");
            System.out.println(customer.toString());
        });

        // HQL / JPQL where case
        // customers db table name, Customer entity class name
        // customer_name db table column name, name entity class attribute
        Query<Customer> query = session.createQuery("from Customer where name='John Doe'", Customer.class);
        List<Customer> list = query.list();
        list.forEach(customer -> {
            System.out.println("============== HQL / JPQL where case ===============");
            System.out.println(customer.toString());
        });

    }
}
