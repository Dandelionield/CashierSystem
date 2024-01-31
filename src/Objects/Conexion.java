package Objects;

import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Conexion{
	
    private String url = "jdbc:sqlite:./src/BaseData/PADataBase.s3db";
	private Connection cn;
	
	public Conexion(){
		
		try{
			
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
