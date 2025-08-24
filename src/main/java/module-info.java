module lk.ijse.supermarketfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires net.sf.jasperreports.core;
    requires java.mail;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens lk.ijse.supermarketfx.config to jakarta.persistence;
    opens lk.ijse.supermarketfx.entity to org.hibernate.orm.core;

    opens lk.ijse.supermarketfx.controller to javafx.fxml;
    opens lk.ijse.supermarketfx.dto.tm to javafx.base;

    exports lk.ijse.supermarketfx;
}