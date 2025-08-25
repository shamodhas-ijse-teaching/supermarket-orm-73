package lk.ijse.supermarketfx.dao.custom;

import lk.ijse.supermarketfx.dao.CrudDAO;
import lk.ijse.supermarketfx.entity.Item;

import java.sql.SQLException;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 11:14 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public interface ItemDAO extends CrudDAO<Item> {
    boolean reduceQuantity(String id, int qty) throws SQLException;

    boolean reduceQty(Item item);
}
