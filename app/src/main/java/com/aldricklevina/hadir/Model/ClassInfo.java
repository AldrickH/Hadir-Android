package com.aldricklevina.hadir.Model;

public class ClassInfo {
    public String type;
    public String name;
    public String schedule;

    public ClassInfo(String _type, String _name, String _schedule) {
        this.type = _type;
        this.name = _name;
        this.schedule = _schedule;
    }

    public String getName() {
        return name;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getType() {
        return type;
    }
}
