package com.example.actual_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MemberView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 750);
        stage.setTitle("Aspire Gym System");
        stage.setScene(scene);
        stage.show();
    }
}

