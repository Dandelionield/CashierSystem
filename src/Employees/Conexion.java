import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    String strConexionDB = "jdbc:sqlite:./PADataBase.s3db";
    Connection conn = null;
    
    public Conexion() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(strConexionDB);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR\n" + e);
        }
    }

    public int Usuario(String sqlSenteString) {
        try {
            PreparedStatement pstm = conn.prepareStatement(sqlSenteString);
            pstm.execute();

            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public ResultSet Consultar(String sqlSenteString) {
        try {
            PreparedStatement pstm = conn.prepareStatement(sqlSenteString);
            ResultSet respuesta = pstm.executeQuery();
            
            return respuesta;
        } catch (SQLException e) {
            return null;
        }
    }
}

