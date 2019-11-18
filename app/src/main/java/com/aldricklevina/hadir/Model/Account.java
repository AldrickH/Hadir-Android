package com.aldricklevina.hadir.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable {
    public String email, fullname, password;

    public Account (String _email, String _fullname, String _password) {
        this.email = _email;
        this.fullname = _fullname;
        this.password = _password;
    }

    public Account(Parcel in) {
        this.email = in.readString();
        this.fullname = in.readString();
        this.password = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.fullname);
        dest.writeString(this.password);
    }
}
