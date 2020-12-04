import java.sql.*;

/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 03-Dec-20
 *  Time: 1:50 PM
 */
//trying to connect ot database
public class JDBCDemo {
    public static void main(String[] args) {
        try {
            //step 1
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //step 2
            //create the connection
            String url = "jdbc:mysql://localhost:3306/demo";
            String user = "root";
            String pass = "";
            Connection con = DriverManager.getConnection(url,user, pass);
            //step 3
            //prepare statement
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO info (`id`, `name`) VALUES ('112', 'Diwan');";
            //insert

            //step 4
            //execute statement or sql
            int rows = stmt.executeUpdate(sql);
            //step 5
            //result processing
            System.out.println("no of rows affected = " + rows);
        }
        catch (ClassNotFoundException e){
            System.out.println(e);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch (SQLException e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
}
