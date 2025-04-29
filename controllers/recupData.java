package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class recupData {

    public static int recupID(String email) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT idPersonnel FROM personnel WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getInt("idPersonnel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; 
    }

    public static String recupNames(int id) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM personnel WHERE idPersonnel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getString("firstName")+" "+rs.getString("lastName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null"; 
    }

    public static String recupFirstName(int id) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM personnel WHERE idPersonnel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getString("firstName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null"; 
    }

    public static String recupLastName(int id) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM personnel WHERE idPersonnel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getString("lastName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null"; 
    }

    public static String recupEmail(int id) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM personnel WHERE idPersonnel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getString("email");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null"; 
    }

    public static String recupPhoneNumber(int id) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM personnel WHERE idPersonnel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getString("telephone");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null"; 
    }

    public static String recupSpecialite(int id) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM personnel p INNER JOIN enseignant e ON p.idPersonnel = e.idPersonnel WHERE p.idPersonnel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getString("e.specialite");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null"; 
    }

    public static String recupClasse(int id) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM responsable r INNER JOIN classe c ON r.idClasse = c.idClasse WHERE r.idPersonnel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getString("c.classeName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null"; 
    }

    public static int recupType(String email) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM personnel WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            return rs.getInt(7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; 
    }
 
}
