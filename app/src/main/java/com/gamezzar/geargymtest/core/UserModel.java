package com.gamezzar.geargymtest.core;

public class UserModel {
    private String name;
    private String email;
    private String password;
    private String repeatPassword;

    private UserModel(String name, String email, String password, String repeatPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    private boolean validate() {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            return false;
        }
        if (!email.contains("@")) {
            return false;
        }
        if (password.length() < 6) {
            return false;
        }
        return password.equals(repeatPassword);
    }

    public static boolean isUserValid(String name, String email, String password, String repeatPassword) {
        UserModel userModel = new UserModel(name, email, password, repeatPassword);
        return userModel.validate();
    }
}
