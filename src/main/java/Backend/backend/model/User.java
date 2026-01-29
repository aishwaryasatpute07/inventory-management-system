package Backend.backend.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email")
    private String userEmail;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String userPassword;

    @Column(name = "security_question")
    private String securityQuestion;

    @Column(name = "security_answer")
    private String securityAnswer;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "industry")
    private String industry; // e.g., IT, Retail, Manufacturing, etc.

    @Column(name = "address")
    private String userAddress;

    @Column(name = "role")
    private Boolean isUser = true;

    public User() {
    }

    public User(Long userId, String fullName, String userEmail, String userPassword, String userAddress, String username, String securityQuestion, String securityAnswer, String companyName, String industry, Boolean isUser) {
        this.userId = userId;
        this.fullName = fullName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPassword = userPassword;
        this.username = username;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.companyName = companyName;
        this.industry = industry;
        this.isUser = isUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Boolean getUser() {
        return isUser;
    }

    public void setUser(Boolean user) {
        isUser = user;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + fullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", FullName='" + fullName + '\''+
                ", SecurityQuestion='" + securityQuestion + '\'' +
                "' SecurityAnswer='" + securityAnswer + '\'' +
                ", CompanyName='" + companyName + '\'' +
                ", Industry='" + industry + '\'' +
                ", isUser=" + isUser +
                '}';
    }
}
