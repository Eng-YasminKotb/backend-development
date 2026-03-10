# JDBC — The 7 Steps

**Every database operation in Java follows these 7 steps.**

---

## 1. Import Packages

```java
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
```

---

## 2 & 3. Load & Register Driver

> In modern JDBC (Java 6+) this is **automatic** — no code needed.
> As long as the MySQL JAR is in your classpath, the driver registers itself.

---

## 4. Create Connection

```java
Connection con = DriverManager.getConnection(url, username, password)
```

---

## 5. Create Statement

```java
Statement st = con.createStatement();
```

---

## 6. Execute Statement

```java
ResultSet rs = st.executeQuery(sql);
rs.next();
String sessionName = rs.getString(1);
System.out.println(sessionName);
```

> `rs.next()` moves the cursor to the first row.
> Without it you get a `SQLException` — the cursor starts **before** row 1.

---

## 7. Close

> ✅ Using **try-with-resources** — closes `Connection` automatically.

---

## Full Clean Example

```java
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        String sql = "Select * from study_sessions where id=3";

        String url = null;
        String username = null;
        String password = null;

        try (var stream = Main.class.getClassLoader().getResourceAsStream("db.properties")){
            Properties props = new Properties();
            props.load(stream);

            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");

            try (Connection con = DriverManager.getConnection(url, username, password)){

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                String sessionName = rs.getString(1);
                System.out.println(sessionName);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```
