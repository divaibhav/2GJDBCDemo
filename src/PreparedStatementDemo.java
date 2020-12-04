/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 04-Dec-20
 *  Time: 1:35 PM
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        try {
            Connection con = SelectQueryDemo.getDbConnection();
            String sql = "SELECT * FROM info WHERE name = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, "Akash");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
