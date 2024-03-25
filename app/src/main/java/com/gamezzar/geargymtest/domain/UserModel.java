package com.gamezzar.geargymtest.domain;

import androidx.annotation.Nullable;

public class UserModel {
    private int id;
    private String name;
    private String email;
    @Nullable
    private String password;
    @Nullable
    private String repeatPassword;

    public UserModel(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserModel(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public UserModel(int id, String name, String email, @Nullable String password, @Nullable String repeatPassword) {
        this(id, name, email);
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public UserModel(String name, String email, @Nullable String password, @Nullable String repeatPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    private boolean validate() {
        if (name.isEmpty() || email.isEmpty() || (password != null && password.isEmpty()) || (repeatPassword != null && repeatPassword.isEmpty())) {
            return false;
        }
        if (!email.contains("@")) {
            return false;
        }
        if (password != null && password.length() < 6) {
            return false;
        }
        return password == null || password.equals(repeatPassword);
    }

    public static boolean isUserValid(String name, String email, String password, String repeatPassword) {
        UserModel userModel = new UserModel(name, email, password, repeatPassword);
        return userModel.validate();
    }
}
