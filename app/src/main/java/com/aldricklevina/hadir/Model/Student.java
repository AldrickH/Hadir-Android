package com.aldricklevina.hadir.Model;

public class Student {

    public String name, nim;

    public Student(String _name, String _nim) {
        this.name = _name;
        this.nim = _nim;

    }

    public String getName() {
        return name;
    }

    public String getNim() { return nim; }
}