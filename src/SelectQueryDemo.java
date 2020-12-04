/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 04-Dec-20
 *  Time: 1:20 PM
 */

import java.sql.*;

public class SelectQueryDemo {
    public static void main(String[] args) {
        try {
            Connection con  = getDbConnection();
            if(con != null) {
                Statement stmt = con.createStatement();
                String sql = "SELECT * FROM info WHERE name = 'Diwan';";
                ResultSet rs = stmt.executeQuery(sql);

                //processing the result
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println(id + "\t\t" + name);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    //for connection
    public static Connection getDbConnection() throws SQLException {
        Connection con = null;
        //no need to load driver
        String url = "jdbc:mysql://localhost:3306/demo";
        String user = "root";
        String pass = "";
        con = DriverManager.getConnection(url, user, pass);
        return con;
    }
}
