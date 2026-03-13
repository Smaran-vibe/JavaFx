module com.example.actual_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;


    opens com.example.actual_project to javafx.fxml;
    exports com.example.actual_project;


    opens com.example.actual_project.controllers to javafx.fxml;


    exports com.example.actual_project.models;
}