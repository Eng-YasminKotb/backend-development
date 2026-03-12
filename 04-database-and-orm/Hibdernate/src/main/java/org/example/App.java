package org.example;

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


        Student student=new Student();
        student.setId(3);
        student.setName("khadeja");
        student.setMark(99);


        Configuration con=new Configuration().configure()
                .setProperty("hibernate.connection.url", props.getProperty("db.url"))
                .setProperty("hibernate.connection.username", props.getProperty("db.username"))
                .setProperty("hibernate.connection.password", props.getProperty("db.password"))
                .addAnnotatedClass(Student.class);

        SessionFactory sessionFactory= con.buildSessionFactory();
        Session session= sessionFactory.openSession();

        Transaction transaction=session.beginTransaction();

        session.save(student);

        transaction.commit();
    }
}
