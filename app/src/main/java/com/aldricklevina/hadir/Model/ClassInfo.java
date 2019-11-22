package com.aldricklevina.hadir.Model;

public class ClassInfo {
    private String id, date, className, timeStart, timeEnd, lecturer;

    public ClassInfo(String _id, String _date, String _className, String _lecturer, String _timeStart, String _timeEnd) {
        this.id = _id;
        this.date = _date;
        this.className = _className;
        this.lecturer = _lecturer;
        this.timeStart = _timeStart;
        this.timeEnd = _timeEnd;
    }

    public String getId() {
        return id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getClassName() {
        return className;
    }

    public String getDate() {
        return date;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }
}