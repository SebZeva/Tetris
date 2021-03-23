/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author imadariaga
 */
public class SQLiteKudeatu
{

    private static String db = "db/Puntuak.db";

    public static Connection connect()
    {
        Connection conn = null;
        try
        {
            // db parameters
            String url = "jdbc:sqlite:" + db;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            //  System.out.println("Connection to SQLite has been established.");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    public static ArrayList<Score> irakurri()
    {
        ArrayList<Score> alScores = new ArrayList<>();
        String taula = "Puntuak";
        String sql = "SELECT Izena, Puntuazioa, Ordua FROM " + taula;

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                Score iz = new Score(rs.getString("Izena"), rs.getLong(
                        "Puntuazioa"), rs.getLong("Ordua"));
                alScores.add(iz);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        Collections.sort(alScores);
        return alScores;
    }

    public static int gehitu(Score sr)
    {

        String sql = "INSERT INTO Puntuak VALUES (?, ?, ?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {

            pstmt.setString(1, sr.izena);
            pstmt.setLong(2, sr.puntuak);
            pstmt.setLong(3, sr.denbora);
            return pstmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Score> al = irakurri();
        for (Score sr : al)
        {
            System.out.println(sr);
        }
    }
}
