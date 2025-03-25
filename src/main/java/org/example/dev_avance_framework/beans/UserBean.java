package org.example.dev_avance_framework.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
    private String username;
    private String attribut1;
    private String attribut2;

    // Getters & Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAttribut1() {
        return attribut1;
    }
    public void setAttribut1(String attribut1) {
        this.attribut1 = attribut1;
    }
    public String getAttribut2() {
        return attribut2;
    }
    public void setAttribut2(String attribut2) {
        this.attribut2 = attribut2;
    }
}
