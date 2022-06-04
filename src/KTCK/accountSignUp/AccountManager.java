package KTCK.accountSignUp;



import java.sql.*;

public class AccountManager {
    Connection conn;

    public AccountManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetmanager"
                    + "?useUnicode=true&characterEncoding=utf-8","hoang","1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addAccount(signupAccount s){
        String sql = "INSERT INTO signup(ID,name,user,pass,access) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,s.getId());
            ps.setString(2,s.getNameSU());
            ps.setString(3,s.getUserSU());
            ps.setString(4,s.getPassSU());
            ps.setString(5,"user");
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    Statement statement;
    public boolean checkAccount(signupAccount s){
        try {
            statement = conn.createStatement();
            ResultSet rst = statement.executeQuery("SELECT * FROM signup ");
            while (rst.next()) {
                if (rst.getString(3).equals(s.getUserSU()) && rst.getString(4).equals(s.getPassSU())) {
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
