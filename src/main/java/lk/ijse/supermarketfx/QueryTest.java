package lk.ijse.supermarketfx;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lk.ijse.supermarketfx.config.FactoryConfiguration;
import lk.ijse.supermarketfx.entity.Customer;
import lk.ijse.supermarketfx.entity.Order;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;
import java.util.Objects;

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

//        // Native SQL where case
//        NativeQuery<Customer> nativeQuery = session.createNativeQuery(
//                "select * from customers where customer_name='John Doe'", Customer.class);
//        List<Customer> customerList = nativeQuery.list();
//        customerList.forEach(customer -> {
//            System.out.println("============== Native SQL ===============");
//            System.out.println(customer.toString());
//        });

        // HQL / JPQL where case
        // customers db table name, Customer entity class name
        // customer_name db table column name, name entity class attribute
//        Query<Customer> query = session.createQuery("from Customer where name='John Doe'", Customer.class);
//        List<Customer> list = query.list();
//        list.forEach(customer -> {
//            System.out.println("============== HQL / JPQL where case ===============");
//            System.out.println(customer.toString());
//        });

        // Parameter

        // Native SQL where case
//        NativeQuery<Customer> nativeQuery = session.createNativeQuery(
//                "select * from customers where customer_name = :cus_name", Customer.class);
//        nativeQuery.setParameter("cus_name", "John Doe");
//        List<Customer> customerList = nativeQuery.list();
//        customerList.forEach(customer -> {
//            System.out.println("============== Native SQL ===============");
//            System.out.println(customer.toString());
//        });

        // HQL where case with parameter
//        session.createQuery("",entity class) - HQL / JPQL
//        session.createNativeQuery("",entity class) - SQL
//        customers db table name, Customer entity class name
//        customer_name db table column name, name entity class attribute
//        :cus_name - parameters
//        cus is reference for Customer entity
//        Query<Customer> query = session.createQuery(
//                "from Customer cus where cus.name= :cus_name",
//                Customer.class
//        );
//        query.setParameter("cus_name", "John Doe");
//        List<Customer> customerList = query.list();
//
//        for (Customer customer : customerList) {
//            System.out.println(customer.toString());
//        }

//        Criteria API
        // Type safe, Programmatic way build queries in HQL / JPQL
        // Avoid writing HQL / JPQL as string
        // Use for dynamic queries

//        // 1. create CriteriaBuilder object
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        // 2. create CriteriaQuery object
//        CriteriaQuery<Customer> customerQuery = criteriaBuilder.createQuery(Customer.class);
//        // 3. Setup root entity
//        Root<Customer> root = customerQuery.from(Customer.class);
//        // 4. add where condition
//        customerQuery.select(root).where(criteriaBuilder.equal(root.get("name"), "John Doe"));
//        // 5. Run Query
//        Query<Customer> query = session.createQuery(customerQuery);
//        List<Customer> customerList = query.list();

//        select * from customers c left join orders o on c.id = o.cus_id where c.id = 'C001'
        Query<Object[]> query = session.createQuery(
                "FROM Customer c left join Order o on c.id = o.customer WHERE c.id='C001'",
                Object[].class
        );

        // Customer, Order join
        // so Object[] -> 0 index have Customer object data
        // so Object[] -> 1 index have Order object data
        List<Object[]> dataList = query.list();
        //            Object[] -> [Customer, Order]
        //            List<Object[]> - [[Customer, Order], [Customer, Order], [Customer, Order]]
        for (Object[] objects : dataList) {
            Customer customer = (Customer) objects[0];
            Order order = (Order) objects[1];
            System.out.println(customer.toString());
            System.out.println(order.toString());
//            Customer, Order join
//            objects[0] -> Customer
//            objects[1] -> Order
        }
        // Object[] columns
        // list data rows


    }
}
