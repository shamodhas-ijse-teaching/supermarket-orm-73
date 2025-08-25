package lk.ijse.supermarketfx.dao.custom.impl;

import lk.ijse.supermarketfx.config.FactoryConfiguration;
import lk.ijse.supermarketfx.dao.SQLUtil;
import lk.ijse.supermarketfx.dao.custom.OrderDAO;
import lk.ijse.supermarketfx.entity.Order;
import lk.ijse.supermarketfx.util.CrudUtil;
import org.hibernate.Session;

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
 * Created: 7/1/2025 1:01 PM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public class OrderDAOImpl implements OrderDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Order> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            List<Order> list = session.createQuery("FROM Order", Order.class)
                    .getResultList();
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute(
                "select order_id from orders order by order_id desc limit 1"
        );
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean save(Order order) throws SQLException {
        Session currentSession = FactoryConfiguration.getInstance().getCurrentSession();
        try {
            currentSession.persist(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<String> getAllIds() {
        return List.of();
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean existOrdersByCustomerId(String customerId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders WHERE customer_id = ?", customerId);
        return resultSet.next();
    }
}
