package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="student_table")
public class Student {

    @Id
    private int id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_mark")
    private double mark;

    @OneToOne
    private Laptop laptop;

    public String getName() {
        return name;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public void setName(String name) {
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
                "laptop=" + laptop +
                ", mark=" + mark +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
