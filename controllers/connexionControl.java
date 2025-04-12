package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class connexionControl {

    public static boolean verifyLogin(String log, String pwd) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM personnel WHERE email = ? AND telephone = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, log);
            stmt.setString(2, pwd);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}