package lk.ijse.supermarketfx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 10:27 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "item_code") // item_code
    private String id;

    @Column(length = 100) // varchar(100)
    private String name;

    private int quantity;

    // 1111111111.11
    @Column(name = "unit_price", precision = 10,scale = 2) // unit_price
    private BigDecimal price;
}
