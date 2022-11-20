package com.innova.ds.dto;

public class BaseInput {

    private String password;

    public BaseInput() {

    }

    public BaseInput(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
