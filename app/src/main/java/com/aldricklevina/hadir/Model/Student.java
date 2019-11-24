package com.aldricklevina.hadir.Model;

public class Student {

    public String id, fullName, classId;
    public String status;

    public Student(String _id, String _fullName, String _classId) {
        this.id = _id;
        this.fullName = _fullName;
        this.classId = _classId;
    }

    public String getStatus() {
        return status;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public String getClassId() {
        return classId;
    }
}