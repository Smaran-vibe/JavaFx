package com.example.actual_project;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class day20 {


    public static class AccordionPaneTest extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {


            TitledPane paneOne = new TitledPane();
            paneOne.setText("ID");
            paneOne.setContent(new Label("1"));

            TitledPane paneTwo= new TitledPane();
            paneTwo.setText("NAME");
            paneTwo.setContent(new Label("Smaran Aryal"));

            TitledPane paneThree= new TitledPane();
            paneThree.setText("ADDRESS");
            paneThree.setContent(new Label("Kirtipur, Kathmandu"));

            Accordion accordionNew = new Accordion();
            accordionNew.getPanes().addAll(paneOne, paneTwo, paneThree);

            Scene scene = new Scene(accordionNew, 550, 250);
            primaryStage.setScene(scene);
            primaryStage.setTitle("AccordionPane Container");
            primaryStage.show();
        }
    }

    public static void main(String[] args) {

        Application.launch(AccordionPaneTest.class, args);
    }
}