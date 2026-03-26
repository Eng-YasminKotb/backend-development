# Hibernate ORM — Learning Notes

---

## Setup
- Maven project with `hibernate-core 6.0.1.Final` and `mysql-connector-j`
- `hibernate.cfg.xml` → tells Hibernate which DB to connect to and how
- `db.properties` → stores credentials separately, ignored by Git via `.gitignore`
- Hibernate 6 requires full property names with `hibernate.` prefix (e.g. `hibernate.connection.url`)


---

### ORM & Hibernate Theory

**ORM (Object Relational Mapping)** is a concept — the idea of converting
Java objects into database rows automatically, without writing SQL manually.
ORM itself is just a concept, we need a tool to implement it.

**Hibernate** is that tool — the most popular ORM framework for Java.
Other tools exist too:
- `TopLink` → ORM tool by Oracle
- `iBATIS / MyBatis` → similar idea but you still write SQL yourself,
  so it's more of a SQL mapper than a full ORM

**How it works:**
- You have an object `o` → just call `session.save(o)` → saved to DB ✅
- No SQL needed — Hibernate writes it for you

**Where does `session` come from?**
1. `SessionFactory` → holds your DB configuration (driver, url, username, password)
2. `Session` → you get it from `SessionFactory`, use it to talk to the DB

**Two ways to configure SessionFactory:**
- XML → `hibernate.cfg.xml`
- Java code → `new Configuration().setProperty(...)`

---


## Concepts

### SessionFactory & Session
- `SessionFactory` → created **once** for the whole app, expensive to build
- `Session` → created **per operation**, like a single conversation with the DB
- `Transaction` → wraps any save/update/delete to keep data safe
 

```java
        Configuration con=new Configuration().configure()
                .setProperty("hibernate.connection.url", props.getProperty("db.url"))
                .setProperty("hibernate.connection.username", props.getProperty("db.username"))
                .setProperty("hibernate.connection.password", props.getProperty("db.password"))
                .addAnnotatedClass(Student.class);
                //.addAnnotatedClass(//your class);

        SessionFactory sessionFactory= con.buildSessionFactory();
        
        Session session= sessionFactory.openSession();

        Transaction transaction=session.beginTransaction();

        //session.save(//your object);

        transaction.commit();
```