package com.team.planopedia.dao;



import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String googleEmail;


    public  User(){};

    public User(Long userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.googleEmail = email;
        //this.providerID = providerID;
    }

    public void setUid(Long uid) {
        this.userId = uid;
    }


    public Long getUid() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getgoogleEmail() {
        return googleEmail;
    }

    public void setgoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }
}


