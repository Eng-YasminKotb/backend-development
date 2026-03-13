package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="student_table")
public class Student {

    @Id
    private int id;

    @Column(name = "student_name")
    private StudentName name;

    private double mark;


    public StudentName getName() {
        return name;
    }

    public void setName(StudentName name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}
