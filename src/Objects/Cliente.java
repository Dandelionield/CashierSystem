package Objects;

import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Cliente{
	
	private String Nombre;
	private String Apellido;
	private String ID;
	private String Telefono;
	private String Email;
	private String Direccion;
	
	public Cliente(String Nombre, String Apellido, String ID, String Telefono, String Email, String Direccion){
		
		this.Nombre = Nombre;
		this.Apellido = Apellido;
		this.ID = ID;
		this.Telefono = Telefono;
		this.Email = Email;
		this.Direccion = Direccion;
		
	}

	public String getName(){
		
		return Nombre;
		
	}

	public void setName(String Nombre){
		
		this.Nombre = Nombre;
		
	}

	public String getLastName(){
		
		return Apellido;
		
	}

	public void setLastName(String Apellido){
		
		this.Apellido = Apellido;
		
	}
	
	public String getID(){
		
		return ID;
		
	}

	public void setID(String ID){
		
		this.ID = ID;
		
	}
	
	public String getPhone(){
		
		return Telefono;
		
	}

	public void setPhone(String Telefono){
		
		this.Telefono = Telefono;
		
	}
	
	public String getEmail(){
		
		return Email;
		
	}

	public void setEmail(String Email){
		
		this.Email = Email;
		
	}
	
	public String getAddress(){
		
		return Direccion;
		
	}

	public void setAddress(String Direccion){
		
		this.Direccion = Direccion;
		
	}
	
	public static Cliente get(String ID){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Cliente WHERE `ID`= '"+ID+"' ");
			
			Cliente p = new Cliente(Script.getString("Nombre"), Script.getString("Apellido"), Script.getString("ID"), Script.getString("Telefono"), Script.getString("Email"), Script.getString("Direccion"));
		
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Selección:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
			return null;
			
		}
		
	}
	
	public static Cliente[] get(){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Cliente;");
			
			Cliente[] p = new Cliente[length()];
			
			int n = 0;
			
			while (Script.next()){
				
				p[n] = new Cliente(Script.getString("Nombre"), Script.getString("Apellido"), Script.getString("ID"), Script.getString("Telefono"), Script.getString("Email"), Script.getString("Direccion"));
				
				n++;
				
			}
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Selección:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
			return null;
			
		}
		
	}
	
	public static int length(){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT COUNT(*) AS total FROM Cliente");
			
			int p = Script.getInt("total");
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Selección:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
			return 0;
			
		}
		
	}
	
	public void add(){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("INSERT INTO Cliente (ID, Nombre, Apellido, Telefono, Email, Direccion) VALUES "
			+"('"+this.getID()+"', '"+this.getName()+"', '"+this.getLastName()+"', '"+this.getPhone()+"', '"+this.getEmail()+"', '"+this.getAddress()+"');");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void remove(){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("DELETE FROM `Cliente` WHERE `ID`='"+this.getID()+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Remoción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void edit(String ID){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("UPDATE `Cliente` SET 'ID' = '"+this.getID()+
            "', 'Nombre' = '"+this.getName()+
            "', 'Apellido' = '"+this.getLastName()+
            "', 'Telefono' = '"+this.getPhone()+
            "', 'Email' = '"+this.getEmail()+
            "', 'Direccion' = '"+this.getAddress()+
            "' WHERE ID = '"+ID+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Edición:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
}