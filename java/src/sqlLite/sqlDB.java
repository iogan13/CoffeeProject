/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chilse
 */
public class sqlDB {

    private final String sqlPath;

    private String error = "";

    /*
     public static void main(String[] args) {
     new sql_cer("C://cry//CDS.sqlite");
     }*/
    public sqlDB(String sqlP) {
        this.sqlPath = sqlP;

    }

    public String getError() {
        return error;
    }

    public boolean addUser(String name, boolean guest, boolean admin, int cardId) {
        Connection con = null;
        boolean DONE = true;
        try {
            String acc = "cryptet";
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + sqlPath);
            Statement stmt = null;
            stmt = con.createStatement();
                String sql = "INSERT INTO user (cardId,name,account,guest,admin,register) VALUES (" + cardId + ", '" + name + "', '" + acc + "', '" + guest + "', '" + admin + "','" + ts + "')";

            stmt.executeUpdate(sql);

            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            DONE = false;
            error = e.toString();
            try {
                con.close();
            } catch (SQLException ex) {
                DONE = false;
            }
        }
        return DONE;
    }

    public boolean addHistory(int id, int type, int count) {
        Connection con = null;
        boolean DONE = true;
        try {
            String acc = "cryptet";
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + sqlPath);
            Statement stmt = null;
            stmt = con.createStatement();
            String sql = "INSERT INTO history (id,type,count) VALUES (" + id + ", " + type + ", " + count + ")";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            DONE = false;
            error = e.toString();
            try {
                con.close();
            } catch (SQLException ex) {
                DONE = false;
            }
        }
        return DONE;
    }

    public String[] getUser() {
        Connection con = null;
        List<String> usernames = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + sqlPath);
            Statement stmt = null;
            stmt = con.createStatement();
            String sql = "SELECT name FROM user;";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                usernames.add(rs.getString("name"));
            }
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            error = e.toString();
            try {
                con.close();
            } catch (SQLException ex) {

            }
        }
        String[] names = new String[usernames.size()];
        return usernames.toArray(names);
    }

    public int getUserIdByName(String name) {
        Connection con = null;
        int id = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + sqlPath);
            Statement stmt = null;
            stmt = con.createStatement();
            String sql = "SELECT id FROM user WHERE name = '" + name + "';";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("id");
                break;
            }
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            error = e.toString();
            try {
                con.close();
            } catch (SQLException ex) {

            }
        }

        return id;
    }

    public boolean deleteUser(int id) {
        Connection con = null;
        boolean done = true;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + sqlPath);
            Statement stmt = null;
            stmt = con.createStatement();
            String sql = "Delete From user WHERE id =" + id + ";";
            stmt.executeUpdate(sql);
            sql = "Delete From history WHERE id =" + id + ";";
            stmt.executeUpdate(sql);

            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            error = e.toString();
            done = false;
            try {
                con.close();
            } catch (SQLException ex) {
                done = false;
            }
        }
        return done;
    }

    public boolean updateAcc(int id, String value) {
        Connection con = null;
        boolean done = true;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + sqlPath);
            Statement stmt = null;
            stmt = con.createStatement();
            String sql = "UPDATE user set account = '" + value + "' where id = " + id + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            error = e.toString();
            done = false;
            try {
                con.close();
            } catch (SQLException ex) {
                done = false;
            }
        }
        return done;
    }
}
