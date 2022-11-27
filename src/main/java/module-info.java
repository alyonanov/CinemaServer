module com.example.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires lombok;
    requires org.hibernate.orm.core;


    opens com.example.server to javafx.fxml;
    exports com.example.server;
}