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

    // Your original permissions
    opens com.example.actual_project to javafx.fxml;
    exports com.example.actual_project;

    // --- ADD THESE TWO LINES ---
    // 1. Allows your FXML files to communicate with your controllers
    opens com.example.actual_project.controllers to javafx.fxml;

    // 2. Allows JavaFX (like TableViews later on) to read your Model data
    exports com.example.actual_project.models;
}