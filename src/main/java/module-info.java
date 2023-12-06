module coffeeshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;

    opens coffeeshop to javafx.fxml, org.hibernate.orm.core;
    exports coffeeshop;
    opens coffeeshop.Controllers to javafx.fxml;
    exports coffeeshop.Controllers;
}
