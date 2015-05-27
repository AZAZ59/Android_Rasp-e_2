package Entities;

/**
 * Created by Grand on 27.05.2015.
 */
public class Schedule_item {
    private int number;
    private int day;
    private boolean week;
    private String name;
    private String teacher;
    private String cab;

    public String getCab() {
        return cab;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isWeek() {
        return week;
    }
}
