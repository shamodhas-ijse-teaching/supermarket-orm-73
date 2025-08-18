package lk.ijse.supermarketfx.dao.custom.impl;

import lk.ijse.supermarketfx.config.FactoryConfiguration;
import lk.ijse.supermarketfx.dao.SQLUtil;
import lk.ijse.supermarketfx.dao.custom.CustomerDAO;
import lk.ijse.supermarketfx.dto.CustomerDTO;
import lk.ijse.supermarketfx.entity.Customer;
import lk.ijse.supermarketfx.util.CrudUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 10:51 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public class CustomerDAOImpl implements CustomerDAO {
    private final FactoryConfiguration factoryConfiguration =
            FactoryConfiguration.getInstance();


    @Override
    public boolean save(Customer customer) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        List<Customer> list = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            list.add(customer);
        }
        return list;
    }

    @Override
    public String getLastId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return SQLUtil.execute(
                "UPDATE customer SET name = ?, nic = ?, email = ?, phone = ? WHERE customer_id = ?",
                customer.getName(),
                customer.getNic(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM customer WHERE customer_id = ?", id);
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer");
        List<String> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    @Override
    public Optional<Customer> findById(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customer_id = ?", id);
        if (resultSet.next()) {
            return Optional.of(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> search(String text) throws SQLException {
        String searchText = "%" + text + "%";
        ResultSet resultSet = SQLUtil.execute(
                "SELECT * FROM customer WHERE customer_id LIKE ? OR name LIKE ? OR nic LIKE ? OR email LIKE ? OR phone LIKE ?",
                searchText, searchText, searchText, searchText, searchText
        );

        List<Customer> list = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            list.add(customer);
        }
        return list;
    }

    @Override
    public Optional<Customer> findCustomerByNic(String nic) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE nic = ?", nic);
        if (resultSet.next()) {
            return Optional.of(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsCustomerByPhoneNumber(String phoneNumber) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE phone = ?", phoneNumber);
//        if (resultSet.next()){
//            return true;
//        }
//        return false;

        return resultSet.next();
    }
}
