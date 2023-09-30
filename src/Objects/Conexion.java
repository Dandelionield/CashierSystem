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
	
	public void setCliente(Cliente p){
		
		try{
			
			PreparedStatement Script = cn.prepareStatement("INSERT INTO Cliente (ID, Nombre, Apellido, Telefono, Email, Direccion) VALUES "
			+"('"+p.getID()+"', '"+p.getName()+"', '"+p.getLastName()+"', '"+p.getPhone()+"', '"+p.getEmail()+"', '"+p.getAddress()+"');");
			
			Script.execute();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public Cliente getCliente(long indice){
		
		long n = 0;
		Cliente p = null;
		
		try{
			
			PreparedStatement list = cn.prepareStatement("SELECT * FROM Cliente;");
			ResultSet Script = list.executeQuery();
			
			while (Script.next()){
				
				if (indice==n){
					
					p = new Cliente(Script.getString("Nombre"), Script.getString("Apellido"), Script.getString("ID"), Script.getString("Telefono"), Script.getString("Email"), Script.getString("Direccion"));
					
				}
				
				n++;
				
			}
			
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return null;
			
		}
		
		return p;
		
	}
	
	public void setFactura(Factura p){
		
		try{
			
			PreparedStatement Script = cn.prepareStatement("INSERT INTO Factura (Code, Pay, Change, Total, Buyout, Date, Employe, Client) VALUES "
			+"('"+p.getCode()+"', "+p.getPay()+", "+p.getChange()+", "+p.getTotal()+", '"+Conexion.setSerieMatrix(p.getBuyout())
			+"', '"+p.getDate()+"', '"+Conexion.setSerieTrabajador(p.getEmploye())+"', '"+Conexion.setSerieCliente(p.getClient())+"');");
			
			Script.execute();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public Factura getFactura(long indice){
		
		long n = 0;
		Factura p = null;
		
		try{
			
			PreparedStatement list = cn.prepareStatement("SELECT * FROM Factura;");
			ResultSet Script = list.executeQuery();
			
			while (Script.next()){
				
				if (indice==n){
					
					p = new Factura(Script.getString("Code"), Script.getLong("Pay"), Script.getDouble("Change"), Script.getDouble("Total"), Conexion.getSerieMatrix(Script.getString("Buyout")), Script.getString("Date"), Conexion.getSerieTrabajador(Script.getString("Employe")), Conexion.getSerieCliente(Script.getString("Client")));
					
				}
				
				n++;
				
			}
			
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return null;
			
		}
		
		return p;
		
	}
	
	public void setArchivo(Archivo p){
		
		try{
			
			PreparedStatement Script = cn.prepareStatement("INSERT INTO Inventario (Code, Product, Brand, Description, Amount, Price, Unid, Image) VALUES "
			+"('"+p.getCode()+"', '"+p.getProduct()+"', '"+p.getBrand()+"', '"+p.getDescription()+"', "+p.getAmount()+", "+p.getPrice()
			+", '"+p.getUnid()+"', '"+p.getImage()+"');");
			
			Script.execute();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public Archivo getArchivo(long indice){
		
		long n = 0;
		Archivo p = null;
		
		try{
			
			PreparedStatement list = cn.prepareStatement("SELECT * FROM Inventario;");
			ResultSet Script = list.executeQuery();
			
			while (Script.next()){
				
				if (indice==n){
					
					p = new Archivo(Script.getString("Code"), Script.getString("Product"), Script.getString("Brand"), Script.getString("Description"), Script.getFloat("Amount"), Script.getFloat("Price"), Script.getString("Unid"), Script.getString("Image"));
					
				}
				
				n++;
				
			}
			
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			return null;
			
		}
		
		return p;
		
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
	
	public void sentence(String query){
        
        try {
            
            PreparedStatement snt=cn.prepareStatement(query);
            snt.execute();
            
           
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Ejecucion fallida: "+e.toString());
        }
        
    }
    
    public ResultSet consulta(String query){
        
        try {
            
            PreparedStatement snt=cn.prepareStatement(query);
            ResultSet res=snt.executeQuery();
            return res;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ejecucion fallida, Error: "+e.toString());
        }
        
        
        return null;
    }
	
	public void clearCliente(){
		
		try{
		
			Statement clear = cn.createStatement();
            clear.executeUpdate("DELETE FROM Cliente");
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void clearFactura(){
		
		try{
		
			Statement clear = cn.createStatement();
            clear.executeUpdate("DELETE FROM Factura");
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void clearArchivo(){
		
		try{
		
			Statement clear = cn.createStatement();
            clear.executeUpdate("DELETE FROM Archivo");
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void Close(){
		
		try{
		
			cn.close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private static Object[][] getSerieMatrix (String serializedMatrix) {
		
        String[] parts = serializedMatrix.split(";");
		int c = 0;

        Object[][] Matrix = new Object[parts.length/5][5];

        for (int f = 0; f <Matrix.length; f++){

            Matrix[f][0] = parts[c];c++;
			Matrix[f][1] = parts[c];c++;
			Matrix[f][2] = Float.parseFloat(parts[c]);c++;
			Matrix[f][3] = parts[c];c++;
			Matrix[f][4] = parts[c];c++;
			
        }

        return Matrix;
    }
	
	private static String setSerieMatrix (Object[][] Matrix){
		
        StringBuilder sb = new StringBuilder();
		
        for (Object[] Fila : Matrix){
			
            for (Object Columna : Fila){
				
                sb.append(Columna).append(";");
				
            }
			
        }
		
		String bup = sb.toString();

        return bup.substring(0,bup.length()-1);
		
    }
	
	private static Trabajador getSerieTrabajador(String serializedEmploye){
		
		String[] parts = serializedEmploye.split(",");
		
		return new Trabajador(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7].charAt(0), Byte.parseByte(parts[8]), Boolean.parseBoolean(parts[9]));
		
	}
	
	private static String setSerieTrabajador(Trabajador p){
		
		return p.getCode()+","+p.getID()+","+p.getName()+","+p.getLastName()+","+p.getPhone()+","+p.getEmail()+","+p.getAddress()+","+p.getGender()+","+p.getAge()+","+p.getAdmin();
		
	}
	
	private static Cliente getSerieCliente(String serializedClient){
		
		String[] parts = serializedClient.split(",");
		
		return new Cliente(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
		
	}
	
	private static String setSerieCliente(Cliente p){
		
		return p.getName()+","+p.getLastName()+","+p.getID()+","+p.getPhone()+","+p.getEmail()+","+p.getAddress();
		
	}

}