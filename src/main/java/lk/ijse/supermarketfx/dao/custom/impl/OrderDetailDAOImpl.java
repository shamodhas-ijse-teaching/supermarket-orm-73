package lk.ijse.supermarketfx.dao.custom.impl;

import lk.ijse.supermarketfx.dao.SQLUtil;
import lk.ijse.supermarketfx.dao.custom.OrderDetailsDAO;
import lk.ijse.supermarketfx.entity.OrderDetail;

import java.sql.SQLException;
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

public class OrderDetailDAOImpl implements OrderDetailsDAO {
    @Override
    public List<OrderDetail> getAll() {
        return List.of();
    }

    @Override
    public String getLastId() {
        return "";
    }

    @Override
    public boolean save(OrderDetail orderDetail) throws SQLException {
//        return SQLUtil.execute(
//                "insert into order_details values (?,?,?,?)",
//                orderDetail.getOrderId(),
//                orderDetail.getItemId(),
//                orderDetail.getQuantity(),
//                orderDetail.getPrice()
//        );
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
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
    public Optional<OrderDetail> findById(String id) {
        return Optional.empty();
    }
}
