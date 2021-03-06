package com.tuprofe.api.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String accountHolderIdentification;
    private String accountHolderName;

    //Schedule
    private String schedule;

    //Courses
    private List<String> courses;

    //Auxiliar Variables
    private Boolean cultureForm;
    private Boolean validData;
    private Boolean acceptGameRules;
    private String interview;
    private String profileDescription;
    private Exam exam;

    public Teacher() {
        super();
        this.validData = false;
        this.acceptGameRules = false;
        this.courses = new ArrayList<>();
        this.exam = new Exam("", false, 0, 0);
    }

    public Teacher(String name, String lastName, String email, String password) {
        this();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // <editor-fold desc="Getter Setter" defaultstate="collapsed">
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

    @DynamoDBAttribute(attributeName = "accountHolderIdentification")
    public String getAccountHolderIdentification() {
        return accountHolderIdentification;
    }

    public void setAccountHolderIdentification(String accountHolderIdentification) {
        this.accountHolderIdentification = accountHolderIdentification;
    }

    @DynamoDBAttribute(attributeName = "accountHolderName")
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    @DynamoDBAttribute(attributeName = "schedule")
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @DynamoDBAttribute(attributeName = "courses")
    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @DynamoDBAttribute(attributeName = "cultureForm")
    public Boolean isCultureForm() {
        return cultureForm;
    }

    public void setCultureForm(Boolean cultureForm) {
        this.cultureForm = cultureForm;
    }

    @DynamoDBAttribute(attributeName = "profileDescription")
    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @DynamoDBAttribute(attributeName = "validData")
    public Boolean isValidData() {
        return validData;
    }

    public void setValidData(Boolean validData) {
        this.validData = validData;
    }

    @DynamoDBAttribute(attributeName = "acceptGameRules")
    public Boolean isAcceptGameRules() {
        return acceptGameRules;
    }

    public void setAcceptGameRules(Boolean acceptGameRules) {
        this.acceptGameRules = acceptGameRules;
    }

    @DynamoDBAttribute(attributeName = "exam")
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @DynamoDBAttribute(attributeName = "interview")
    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    // </editor-fold>
    // <editor-fold desc="Exam" defaultstate="collapsed">
    @DynamoDBDocument
    public static class Exam {

        private String idExam;
        private boolean passExam;
        private Integer correctAnswers;
        private Integer wrongAnswers;

        public Exam() {

        }

        public Exam(String idExam, boolean passExam, Integer correctAnswers, Integer wrongAnswers) {
            this.idExam = idExam;
            this.passExam = passExam;
            this.correctAnswers = correctAnswers;
            this.wrongAnswers = wrongAnswers;
        }

        @DynamoDBAttribute(attributeName = "idExam")
        public String getIdExam() {
            return idExam;
        }

        public void setIdExam(String idExam) {
            this.idExam = idExam;
        }

        @DynamoDBAttribute(attributeName = "passExam")
        public boolean isPassExam() {
            return passExam;
        }

        public void setPassExam(boolean passExam) {
            this.passExam = passExam;
        }

        @DynamoDBAttribute(attributeName = "correctAnswers")
        public Integer getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(Integer correctAnswers) {
            this.correctAnswers = correctAnswers;
        }

        @DynamoDBAttribute(attributeName = "wrongAnswers")
        public Integer getWrongAnswers() {
            return wrongAnswers;
        }

        public void setWrongAnswers(Integer wrongAnswers) {
            this.wrongAnswers = wrongAnswers;
        }

    }
    // </editor-fold>

}
