package Backend.backend.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;
    @Column(name = "firstname")
    private String userFirstName;
    @Column(name = "lastname")
    private String userLastName;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String userPassword;
    @Column(name = "email")
    private String userEmail;
    @Column(name = "isCustomer")
    private Boolean isCustomer = false;

    public User() {
    }

    public User(String userEmail, String userPassword, String userName, String userLastName, String userFirstName, Long userId, Boolean isCustomer) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.userId = userId;
        this.isCustomer = isCustomer;
    }


    //Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Boolean getIsCustomer(){return isCustomer;}

    public void setIsCustomer(Boolean isCustomer){this.isCustomer = isCustomer;}

}
