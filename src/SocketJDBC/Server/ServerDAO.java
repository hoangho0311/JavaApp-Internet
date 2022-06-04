package SocketJDBC.Server;

import SocketJDBC.Student;

import java.sql.*;

public class ServerDAO {
    private Connection conn;

    public ServerDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student"
                    + "?useUnicode=true&characterEncoding=utf-8","hoang","1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Statement statement;
    public boolean addStudent(Student s){
        try {
            statement = conn.createStatement();
            ResultSet rst = statement.executeQuery("SELECT * FROM stu ");
            while (rst.next()) {
                if (rst.getInt(1)==s.getId() && rst.getString(2).equals(s.getName())&& rst.getString(3).equals(s.getDob())
                        && rst.getString(4).equals(s.getYear())&& rst.getString(5).equals(s.getAddress())) {
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
