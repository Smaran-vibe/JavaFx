package com.example.actual_project.models;

import java.sql.Date;

public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String phone;
    private String email;
    private String address;
    private Date joinDate;
    private int planId;

    // Constructor
    public Member(int memberId, String firstName, String lastName, String gender,
                  Date dateOfBirth, String phone, String email, String address,
                  Date joinDate, int planId) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.joinDate = joinDate;
        this.planId = planId;
    }


    public int getMemberId() { return memberId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public Date getJoinDate() { return joinDate; }
    public int getPlanId() { return planId; }
}

