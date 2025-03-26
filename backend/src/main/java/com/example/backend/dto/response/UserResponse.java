package com.example.backend.dto.response;

import java.util.Date;

public class UserResponse {
    private int id;
    private String userCode;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int gender;
    private Date dateOfBirth;
    private int roleId;
    private String statusCode;
    private Integer experience;
    private String certification;

    public UserResponse(int id, String userCode, String name, String email, String phone, String address, int gender, Date dateOfBirth, int roleId, String statusCode, Integer experience, String certification) {
        this.id = id;
        this.userCode = userCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.roleId = roleId;
        this.statusCode = statusCode;
        this.experience = experience;
        this.certification = certification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getCertification() {
        return certification != null ? certification : "Chưa có chứng chỉ";
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }
}
