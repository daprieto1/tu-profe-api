/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author diegoprietotorres
 */
@DynamoDBTable(tableName = "teacher")
public class Teacher extends User implements Serializable {

    //Personal Data
    private String identification;
    private String cellPhone;
    private String phone;
    private String city;
    private String neighborhood;
    private String address;

    //Academical Data
    private String university;
    private String universityAddress;
    private Integer semester;
    private Date gradeDate;
    private String profession;

    //Financial Data
    private String bank;
    private String accountNumber;
    private String accountType;

    //Schedule
    private String schedule;
    
    //
    private boolean acceptGameRules;

    public Teacher() {        
        super();
        this.acceptGameRules = false;
    }

    public Teacher(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
    }

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    @Override
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "name")
    @Override
    public String getName() {
        return name;
    }

    @DynamoDBAttribute(attributeName = "state")
    @Override
    public Integer getState() {
        return state;
    }

    @DynamoDBAttribute(attributeName = "lastName")
    @Override
    public String getLastName() {
        return lastName;
    }

    @DynamoDBAttribute(attributeName = "email")
    @Override
    public String getEmail() {
        return email;
    }

    @DynamoDBAttribute(attributeName = "password")
    @Override
    public String getPassword() {
        return password;
    }

    @DynamoDBAttribute(attributeName = "identification")
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @DynamoDBAttribute(attributeName = "cellPhone")
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @DynamoDBAttribute(attributeName = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DynamoDBAttribute(attributeName = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @DynamoDBAttribute(attributeName = "neighborhood")
    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @DynamoDBAttribute(attributeName = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @DynamoDBAttribute(attributeName = "university")
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @DynamoDBAttribute(attributeName = "universityAddress")
    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    @DynamoDBAttribute(attributeName = "semester")
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @DynamoDBAttribute(attributeName = "gradeDate")
    public Date getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    @DynamoDBAttribute(attributeName = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @DynamoDBAttribute(attributeName = "bank")
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @DynamoDBAttribute(attributeName = "accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @DynamoDBAttribute(attributeName = "accountType")
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @DynamoDBAttribute(attributeName = "schedule")
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

}
