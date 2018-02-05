package com.example.model;

public class AppStatus {
    private boolean isLoggedIn;
    private boolean isCartEmpty;
    private boolean isAdminUser;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isCartEmpty() {
        return isCartEmpty;
    }

    public void setCartEmpty(boolean cartEmpty) {
        isCartEmpty = cartEmpty;
    }

    public boolean isAdminUser() {
        return isAdminUser;
    }

    public void setAdminUser(boolean adminUser) {
        isAdminUser = adminUser;
    }
}
