package com.example.models;

public class AccountData
{
    public AccountData(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
