package classes;

import java.sql.*;
import interfaces.SerializableStrategy;

public class JDBCStrategy implements SerializableStrategy {

    try {
        Class.forName("org.sqlite.JDBC");
    }
    catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    try(Connection con = DriverManager.getConnection("jdbc:sqlite:ice.db")){
        executeStatement(con);
    }

        catch (SQLException e){
            e.printStackTrace();
        }


    public static executeStatement(Connection con){
        create(con);
        delete(con,1);
        insert (con, 1, "Songname","Playlist",long id,);
        select(con);
    }

    private static void create(Connection con){
        try(PreparedStatement pstmt = con.prepareStatement(
                "CREATE TABLE IF NOT EXISTS ice (id long, name text, interpret text, album text); ")){
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static boolean insert(Connection con, long id, String name,String interpret, String album); {
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO ice (id, name, interpret, album) VALUES (?,?,?,?);")){
            pstmt.setLong(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3, interpret);
            pstmt.setString(4,album);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }

        private static void delete(Connection con, long id){
            try(PreparedStatement pstmt = con.prepareStatement(
                    "DELETE FROM ice WHERE id = ?:")){
                pstmt.setLong(1,id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        private static void select (Connection con){
            try(PreparedStatement pstmt = con.prepareStatement(
                    "SELECT * FROM PIZZA;");
            ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    System.out.println(rs.getLong("id"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
