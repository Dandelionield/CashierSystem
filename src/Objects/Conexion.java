package Objects;

import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.io.InputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Conexion{
	
    private String url;//= "jdbc:sqlite:./src/BaseData/PADataBase.s3db";//Conexion.class.getResource("/BaseData/PADataBase.s3db").toString();
	private Connection cn;
	
	public Conexion(){
		
		try{
			
			InputStream inputStream = Conexion.class.getClassLoader().getResourceAsStream("BaseData/PADataBase.s3db");
            File tempFile = File.createTempFile("PADataBase", ".s3db");
            tempFile.deleteOnExit(); // Eliminar el archivo temporal cuando se cierra la aplicación

            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            url = "jdbc:sqlite:" + tempFile.getAbsolutePath();
			
			cn = DriverManager.getConnection(url);
			
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null,"Error de conexión:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void sentence(String query) throws SQLException{
  
        PreparedStatement snt = cn.prepareStatement(query);
		
        snt.execute();   
        
    }
    
    public ResultSet consulta(String query){
        
        try{
            
            PreparedStatement snt=cn.prepareStatement(query);
            ResultSet res=snt.executeQuery();
            return res;
            
        }catch(Exception e){
			
            JOptionPane.showMessageDialog(null, "Ejecucion fallida, Error: "+e.toString());
			
        }
        
        
        return null;
    }
	
	public void Close(){
		
		try{
		
			cn.close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

}
