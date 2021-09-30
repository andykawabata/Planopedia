package com.team.planopedia.dao;



import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long u_id;
    private String u_name;
    private String google_email;

    public  User(){};

    public User(Long u_id, String u_name, String email) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.google_email = email;
        //this.providerID = providerID;
    }

    public void setUid(Long uid) {
        this.u_id = uid;
    }


    public Long getUid() {
        return u_id;
    }

    public String getUserName() {
        return u_name;
    }

    public void setUserName(String userName) {
        this.u_name = userName;
    }

    public String getGoogle_email() {
        return google_email;
    }

    public void setGoogle_email(String google_email) {
        this.google_email = google_email;
    }
}


