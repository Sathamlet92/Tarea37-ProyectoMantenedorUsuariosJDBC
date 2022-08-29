package com.sathamlet.usermanteiner.model;

public class User {
    private String userName;
    private Integer id;
    private String password;
    private String email;

    public User() {}

    public User(String userName, int id, String password, String email) {
        this.userName = userName;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "\n#"+id +
                ", " + userName +
                ", " + password +
                ", " + email + "\n";
    }
}
