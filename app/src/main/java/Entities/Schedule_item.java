package Entities;

/**
 * Created by Grand on 27.05.2015.
 */
public class Schedule_item {
    private int number;
    private boolean week;
    private String name;
    private String teacher;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getWeek() {
        return week;
    }

    public void setWeek(boolean week) {
        this.week = week;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
