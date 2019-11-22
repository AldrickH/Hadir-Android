package com.aldricklevina.hadir.Model;

import android.app.Application;

public class Account extends Application {
    public String email, fullname, password;

    public Account (String _email, String _fullname, String _password) {
        this.email = _email;
        this.fullname = _fullname;
        this.password = _password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }
}
