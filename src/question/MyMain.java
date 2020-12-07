/*
 *  Created by IntelliJ IDEA.
 *  User: Vaibhav
 *  Date: 07-Dec-20
 *  Time: 3:11 PM
 */
/**
 * create a database "gla", in that database create a table "student", which contain following columns
 * rollno (int),
 * name(text),
 * email(varchar),
 * cpi(double).
 *
 * Perform the following queries
 * 1. Insert  - > INSERT INTO `student` (`rollno`, `name`, `email`, `cpi`) VALUES ('12', 'Vaibhav', 'vai@gmail.com', '5.73');
 * 2. update where rollno is 12 set cpi t0 8.91 - > UPDATE `student` SET `cpi`= 8.91 WHERE rollno = 12
 * 3. Delete where cpi = 9.9 -> DELETE FROM `student` WHERE cpi = 9.9;
 * 4. Select all the data - > SELECT * FROM student;
 */
package question;

import java.sql.*;
import java.util.Scanner;

public class MyMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter roll no, name, email, cpi");
        int rollNo = sc.nextInt();
        sc.nextLine();
        String name = sc.nextLine();
        String email = sc.nextLine();
        double cpi = sc.nextDouble();

        try {
            System.out.println("inserting data");
            //calling insertdata
            int rows = insertData(rollNo, name, email, cpi);
            System.out.println("no of rows affected =  " + rows);
            //calling select
            ResultSet rs = selectAll();
            if(rs != null) {
                while (rs.next()){
                    rollNo = rs.getInt("rollno");
                    name = rs.getString("name");
                    email = rs.getString("email");
                    cpi = rs.getDouble("cpi");
                    System.out.println(rollNo + "\t" + name + "\t" + email + "\t" + cpi );
                }
            }
            //update
            System.out.println("updating");
            rows = updateData(54, "abc@gmail.com");
            System.out.println("no of rows affected =  " + rows);
            //delete
            System.out.println("deleting");
            rows= deleteData(12);
            System.out.println("no of rows affected =  " + rows);

        } catch (SQLException throwables) {
            System.out.println(throwables);
            throwables.printStackTrace();
        }

    }
    //connection
    public static Connection getDbConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gla";
        String user = "root";
        String pass = "";
        Connection con  = DriverManager.getConnection(url, user, pass);
        return con;
    }
    //insert
    public static int insertData(int rollNo, String name, String email, double cpi) throws SQLException {
        Connection con = getDbConnection();
        String sql = "INSERT INTO `student` (`rollno`, `name`, `email`, `cpi`) VALUES(?, ?, ?, ?);";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //setting parameters
        pstmt.setInt(1, rollNo);
        pstmt.setString(2, name);
        pstmt.setString(3, email);
        pstmt.setDouble(4, cpi);
        int rows = pstmt.executeUpdate();
        return rows;
    }

    //update
    // update on the basis of roll no
    public static int updateData(int rollNo, String email) throws SQLException {
        Connection con = getDbConnection();
        String sql = "UPDATE student SET email = ? WHERE rollno = ?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //setting parameter
        pstmt.setString(1, email);
        pstmt.setInt(2, rollNo);
        int rows = pstmt.executeUpdate();
        return rows;
    }
    //select
    public static ResultSet selectAll() throws SQLException {
        Connection con = getDbConnection();
        String sql = "SELECT * FROM student";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }

    //delete
    //on the basis of rollNo
    public static int deleteData(int rollNo) throws SQLException {
        Connection con  = getDbConnection();
        String sql = "DELETE FROM student WHERE rollno = ?;";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setInt(1, rollNo);
        int rows = pstmt.executeUpdate();
        return rows;
    }
}
