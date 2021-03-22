/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author imadariaga
 */
public class SQLiteKudeatu {

    private static String db = "db/Puntuak.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + db;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            //  System.out.println("Connection to SQLite has been established.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

//    public static ArrayList<Score> irakurri() {
//        ArrayList<Score> alScores = new ArrayList<>();
//        String taula = "Izenak";
//        String sql = "SELECT * FROM " + taula;
//
//        try (Connection conn = connect();
//                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                Izena iz = new Izena(rs.getInt("id"), rs.getString("izena"));
//                alScores.add(iz);
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return alScores;
//    }
//    
//    public static int gehitu(Izena i) {
//
//        String sql = "INSERT INTO Izenak(izena) VALUES(?)";
//
//        try (Connection conn = connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            
//            pstmt.setString(1, i.getIzena());
//            return pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return 0;
//        }
//
//    }


}
