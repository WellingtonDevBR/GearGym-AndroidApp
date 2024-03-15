package com.gamezzar.geargymtest.core;

public class User {
    private String name;
    private String email;
    private String password;
    private String repeatPassword;

    private User(String name, String email, String password, String repeatPassword) {
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
        User user = new User(name, email, password, repeatPassword);
        return user.validate();
    }
}
