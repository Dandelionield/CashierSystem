package Objects;

import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Trabajador{
	
    private String Codigo;
	private String ID;
	private String Nombre;
	private String Apellido;
	private String Telefono;
	private String Email;
	private String Direccion;
	private char Genero;
	private byte Edad;
	private boolean Admin;
	private String Imagen;

    public Trabajador(String Codigo, String ID, String Nombre, String Apellido, String Telefono, String Email, String Direccion, char Genero, byte Edad, boolean Admin, String Imagen){
		
        this.Codigo = Codigo;
        this.ID = ID;
        this.Nombre = Nombre;
		this.Apellido = Apellido;
		this.Telefono = Telefono;
		this.Email = Email;
		this.Direccion = Direccion;
		this.Genero = Genero;
		this.Edad = Edad;
		this.Admin = Admin;
		this.Imagen = Imagen;
		
    }
	
	public String getCode(){
		
        return Codigo;
		
	}
	
	public void setCode(String Codigo){
		
        this.Codigo = Codigo;
		
	}
	
	public String getID(){
		
        return ID;
		
    }
	
	public void setID(String ID){
		
        this.ID = ID;
		
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
	
	public char getGender(){
		
		return Genero;
		
	}

	public void setGender(char Genero){
		
		this.Genero = Genero;
		
	}
	
	public byte getAge(){
		
		return Edad;
		
	}

	public void setAge(byte Edad){
		
		this.Edad = Edad;
		
	}
	
	public boolean getAdmin(){
		
		return Admin;
		
	}
	
	public void setAdmin(boolean Admin){
		
		this.Admin = Admin;
		
	}
	
	public String getImage(){
		
		return Imagen;
		
	}
	
	public void setImage(String Imagen){
		
		this.Imagen = Imagen;
		
	}

	public String getAll() {
		return "[ Codigo: " + Codigo + ", ID: " +  ID + ", Nombre: " + Nombre + ", Apellido: " + Apellido + ", Telefono: " + Telefono + ", Email: " + Email + ", Direccion: " + Direccion + ", Genero: " + Genero + ", Edad: " + Edad + ", Genero: " + Genero + ", Permiso: " + Admin + ", Image: " + Imagen + "]";
	}
	
	public static Trabajador get(String Code){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Trabajador WHERE `Code`= '"+Code+"' ");
			
			Trabajador p = new Trabajador(Script.getString("Code"), Script.getString("ID"), Script.getString("Name"), Script.getString("LastName"), Script.getString("Phone"), Script.getString("Email"), Script.getString("Address"), Script.getString("Gender").charAt(0), Script.getByte("Age"), ((Script.getInt("Admin")==1) ? true : false), Script.getString("Image"));
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Selección:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
			return null;
			
		}
		
	}
	
	public static Trabajador[] get(){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Trabajador;");
			
			Trabajador[] p = new Trabajador[length()];
			
			int n = 0;
			
			while (Script.next()){
				
				p[n] = new Trabajador(Script.getString("Code"), Script.getString("ID"), Script.getString("Name"), Script.getString("LastName"), Script.getString("Phone"), Script.getString("Email"), Script.getString("Address"), Script.getString("Gender").charAt(0), Script.getByte("Age"), (Script.getInt("Admin")==1) ? true : false, Script.getString("Image"));
				
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
			
			ResultSet Script = cn.consulta("SELECT COUNT(*) AS total FROM Trabajador");
			
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
			
			cn.sentence("INSERT INTO Trabajador (Code, ID, Name, LastName, Phone, Email, Address, Gender, Age, Admin, Image) VALUES "
			+"('"+this.getCode()+"', '"+this.getID()+"','"+this.getName()+"', '"+this.getLastName()+"', '"+this.getPhone()+"', '"+this.getEmail()
			+"', '"+this.getAddress()+"', '"+this.getGender()+"', "+this.getAge()+", "+((this.getAdmin()==true) ? 1 : 0)+", '"+this.getImage()+"');");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void remove(){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("DELETE FROM `Trabajador` WHERE `Code`='"+this.getCode()+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Remoción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void edit(String Code){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("UPDATE `Trabajador` SET 'Code' = '"+this.getCode()+
            "', 'ID' = '"+this.getID()+
            "', 'Name' = '"+this.getName()+
            "', 'LastName' = '"+this.getLastName()+
            "', 'Email' = '"+this.getEmail()+
            "', 'Address' = '"+this.getAddress()+
            "', 'Gender' = '"+this.getGender()+
            "', 'Age' = '"+this.getAge()+
			"', 'Admin' = '"+((this.getAdmin()==true) ? 1 : 0)+
            "', 'Image' = '"+this.getImage()+
            "' WHERE Code = '"+Code+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Edición:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
}