package com.example.actual_project.controllers;

import com.example.actual_project.Dao.MemberDAO;
import com.example.actual_project.models.Member;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Date;

public class MemberController {

    @FXML private TextField txtMemberId;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private ComboBox<String> cmbGender;
    @FXML private DatePicker dpDateOfBirth;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtAddress;
    @FXML private DatePicker dpJoinDate;
    @FXML private ComboBox<Integer> cmbPlanId;

    private MemberDAO memberDAO = new MemberDAO();

    @FXML
    public void initialize() {

        cmbGender.getItems().addAll("Male", "Female", "Other");
        cmbPlanId.getItems().addAll(1, 2, 3); // 1: Basic, 2: Standard, 3: Premium
    }

    @FXML
    public void handleSaveMember() {
        if (!validateInput()) {
            return;
        }

        try {
            int id = Integer.parseInt(txtMemberId.getText());
            String fName = txtFirstName.getText();
            String lName = txtLastName.getText();
            String gender = cmbGender.getValue();
            Date dob = Date.valueOf(dpDateOfBirth.getValue());
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String address = txtAddress.getText();
            Date joinDate = Date.valueOf(dpJoinDate.getValue());
            int planId = cmbPlanId.getValue();

            Member newMember = new Member(id, fName, lName, gender, dob, phone, email, address, joinDate, planId);

            boolean success = memberDAO.addMember(newMember);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Member added successfully!");
                clearForm();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add member to the database.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }


    private boolean validateInput() {
        if (txtFirstName.getText().trim().isEmpty() || txtLastName.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "First and Last name cannot be empty.");
            return false;
        }


        if (!txtPhone.getText().matches("\\d{10}")) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Phone number must be 10 digits.");
            return false;
        }


        if (!txtEmail.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter a valid email address.");
            return false;
        }

        if (dpDateOfBirth.getValue() == null || dpJoinDate.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please select valid dates.");
            return false;
        }

        if (cmbPlanId.getValue() == null || cmbGender.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please select a Plan and Gender.");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        txtMemberId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtAddress.clear();
        dpDateOfBirth.setValue(null);
        dpJoinDate.setValue(null);
    }
}

