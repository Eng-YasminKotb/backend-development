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