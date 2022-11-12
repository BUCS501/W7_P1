package com.example.w7_p1;

public class Friend {

    private int id;
    private String firstname;
    private String lastname;
    private String email;

    public Friend(int newId, String newFirstname, String newLastname, String newEmail) {
        setId(newId);
        setFirstname(newFirstname);
        setLastname(newLastname);
        setEmail(newEmail);
    }

    public void setId(int newId) {
        id = newId;
    }

    public void setFirstname(String newFirstname) {
        firstname = newFirstname;
    }

    public void setLastname(String newLastname) {
        lastname = newLastname;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return id + "; " + firstname + "; " + lastname + "; " + email;
    }





}
