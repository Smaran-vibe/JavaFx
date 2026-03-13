package com.example.actual_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class tabpane extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Tab tab1= new Tab("First Tab");
        Tab tab2= new Tab("Second Tab");
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab1, tab2);

        Scene scene=new Scene(tabPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TabPane Layout");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}