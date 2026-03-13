package org.example;

import org.example.entity.Laptop;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App
{
    public static void main( String[] args )
    {


        Properties props = new Properties();

        try(InputStream stream=App.class.getClassLoader().getResourceAsStream("db.properties")){
            props.load(stream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        //Laptop object
        Laptop laptop=new Laptop();

        laptop.setId(101);
        laptop.setName("Dell");

        //Student object
        Student student=new Student();

        student.setId(1);
        student.setName("Yasmin Kotb");
        student.setMark(95);
        student.getLaptops().add(laptop);

        Configuration con=new Configuration().configure()
                .setProperty("hibernate.connection.url", props.getProperty("db.url"))
                .setProperty("hibernate.connection.username", props.getProperty("db.username"))
                .setProperty("hibernate.connection.password", props.getProperty("db.password"))
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class);

        SessionFactory sessionFactory= con.buildSessionFactory();
        Session session= sessionFactory.openSession();

        Transaction transaction=session.beginTransaction();

        session.save(laptop);
        session.save(student);


        transaction.commit();

        System.out.println(student);


    }
}
