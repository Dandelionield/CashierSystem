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

	public void setMode(int p){
		
		try{
			
			PreparedStatement Script = cn.prepareStatement("UPDATE Aspectos SET Mode = ?;");
			
			Script.setInt(1, p);
			
			Script.execute();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public int getMode(){
		
		int p = 0;
		
		try{
			
			PreparedStatement list = cn.prepareStatement("SELECT * FROM Aspectos;");
			ResultSet Script = list.executeQuery();
			
			while (Script.next()){

				p = Script.getInt("Mode");
				
			}
			
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return 0;
			
		}
		
		return p;
		
	}
	
	public void setLanguage(int p){
		
		try{
			
			PreparedStatement Script = cn.prepareStatement("UPDATE Aspectos SET Language = ?;");
			
			Script.setInt(1, p);
			
			Script.execute();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public int getLanguage(){
		
		int p = 0;
		
		try{
			
			PreparedStatement list = cn.prepareStatement("SELECT * FROM Aspectos;");
			ResultSet Script = list.executeQuery();
			
			while (Script.next()){
				
				p = Script.getInt("Language");
				
			}
			
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return 0;
			
		}
		
		return p;
		
	}
	
	public void setTitle(String p){
		
		try{
			
			PreparedStatement Script = cn.prepareStatement("UPDATE Aspectos SET Title = ?;");
			
			Script.setString(1, p);
			
			Script.execute();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public String getTitle(){
		
		String p = "";
		
		try{
			
			PreparedStatement list = cn.prepareStatement("SELECT * FROM Aspectos;");
			ResultSet Script = list.executeQuery();
			
			while (Script.next()){
				
				p = Script.getString("Title");
				
			}
			
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return "";
			
		}
		
		return p;
		
	}
	
	public void setAddress(String p){
		
		try{
			
			PreparedStatement Script = cn.prepareStatement("UPDATE Aspectos SET Address = ?;");
			
			Script.setString(1, p);
			
			Script.execute();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public String getAddress(){
		
		String p = "";
		
		try{
			
			PreparedStatement list = cn.prepareStatement("SELECT * FROM Aspectos;");
			ResultSet Script = list.executeQuery();
			
			while (Script.next()){
				
				p = Script.getString("Address");
				
			}
			
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return "";
			
		}
		
		return p;
		
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
