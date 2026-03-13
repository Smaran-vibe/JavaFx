package com.example.actual_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane; // Added missing import
import javafx.scene.layout.FlowPane; // Added missing import
import javafx.scene.layout.VBox;
import java.sql.*;

public class databasetest extends Application {

    // Fix 1: Ensure URL points to the correct DB and names match the methods below
    private static final String URL = "jdbc:mysql://localhost:3306/javafxdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void start(Stage stage) {
        // --- UI ELEMENTS ---
        Label title = new Label("Student Database System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        TextField idInput = new TextField(); idInput.setPromptText("Student ID (Search/Update/Delete)");
        TextField nameInput = new TextField(); nameInput.setPromptText("Student Name");
        TextField ageInput = new TextField(); ageInput.setPromptText("Student Age");

        TextArea output = new TextArea();
        output.setEditable(false);
        output.setPromptText("Results will appear here...");
        output.setPrefHeight(200);

        // Buttons
        Button btnInsert = new Button("Insert");
        Button btnViewAll = new Button("View All");
        Button btnSearch = new Button("Search ID");
        Button btnUpdate = new Button("Update Age");
        Button btnDelete = new Button("Delete");
        Button btnCount = new Button("Total Count");
        Button btnFilter = new Button("Age > 20");


        btnInsert.setOnAction(e -> {
            String name = nameInput.getText().trim();
            String ageStr = ageInput.getText().trim();

            if (validateName(name) && validateAge(ageStr)) {
                executeSQL("INSERT INTO student(name, age) VALUES(?, ?)", name, ageStr);
                output.setText("Success: Added student " + name);
                nameInput.clear(); ageInput.clear();
            } else {
                showAlert("Validation Error", "Name cannot be empty and Age must be a positive number.");
            }
        });


        btnViewAll.setOnAction(e -> refreshData("SELECT * FROM student", output));


        btnSearch.setOnAction(e -> {
            if (validateID(idInput.getText())) {
                refreshData("SELECT * FROM student WHERE id = " + idInput.getText(), output);
            }
        });


        btnUpdate.setOnAction(e -> {
            if (validateID(idInput.getText()) && validateAge(ageInput.getText())) {
                executeSQL("UPDATE student SET age = ? WHERE id = ?", ageInput.getText(), idInput.getText());
                output.setText("Updated ID " + idInput.getText() + " to age " + ageInput.getText());
            }
        });


        btnDelete.setOnAction(e -> {
            if (validateID(idInput.getText())) {
                executeSQL("DELETE FROM student WHERE id = ?", idInput.getText());
                output.setText("Deleted Student ID: " + idInput.getText());
            }
        });


        btnCount.setOnAction(e -> {
            // Fix 2: Changed PASS to PASSWORD
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM student")) {
                if (rs.next()) output.setText("Total Students in Database: " + rs.getInt("total"));
            } catch (SQLException ex) { ex.printStackTrace(); }
        });


        btnFilter.setOnAction(e -> refreshData("SELECT * FROM student WHERE age > 20", output));


        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10); inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.addRow(0, new Label("ID:"), idInput);
        inputGrid.addRow(1, new Label("Name:"), nameInput);
        inputGrid.addRow(2, new Label("Age:"), ageInput);

        FlowPane buttons = new FlowPane(10, 10, btnInsert, btnViewAll, btnSearch, btnUpdate, btnDelete, btnCount, btnFilter);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, title, inputGrid, buttons, output);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f4f7f6;");

        stage.setScene(new Scene(root, 650, 550));
        stage.setTitle("JavaFX Student Management System");
        stage.show();
    }

    // --- VALIDATION HELPERS ---
    private boolean validateName(String name) {
        return !name.isEmpty() && name.length() <= 50;
    }

    private boolean validateAge(String ageStr) {
        try {
            int age = Integer.parseInt(ageStr);
            return age >= 0;
        } catch (NumberFormatException e) { return false; }
    }

    private boolean validateID(String idStr) {
        try {
            Integer.parseInt(idStr);
            return true;
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter a valid numeric Student ID.");
            return false;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // --- DATABASE HELPERS ---
    private void executeSQL(String sql, String... params) {
        // Fix 3: Changed PASS to PASSWORD
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setString(i + 1, params[i]);
            }
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    private void refreshData(String sql, TextArea area) {
        area.clear();
        // Fix 4: Changed PASS to PASSWORD
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                area.appendText("ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Age: " + rs.getInt("age") + "\n");
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public static void main(String[] args) { launch(args); }
}