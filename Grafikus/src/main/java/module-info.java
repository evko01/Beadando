module grafikus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires weka.stable;
    opens grafikus to javafx.fxml;
    exports grafikus;
}
