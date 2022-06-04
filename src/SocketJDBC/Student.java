package SocketJDBC;

import java.io.Serializable;

public class Student implements Serializable {
    int id;
    String name, dob, year, address;

    public Student(){

    }

    public Student(int id, String name, String dob, String year, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.year = year;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
