package com.kursov.model;

public class CurrentUserBean {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    String username;

    boolean login(String u, String password) {
        boolean rezult = false;
        if (u != null && password != null) {
            if (u.substring(0, 1).equals(password)) {
                username = u;
                rezult= true;
            } else {
                rezult= false;
            }
        }
        return rezult;
    }
}
