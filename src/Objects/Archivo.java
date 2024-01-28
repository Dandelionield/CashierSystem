package Objects;

import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Archivo{
	
	private String Codigo;
	private String Producto;
	private String Marca;
	private String Descripcion;
	private float Cantidad;
	private float Vendido;
	private float Precio;
	private String Unidad;
	private String Imagen;
	
	public Archivo(String Codigo, String Producto, String Marca, String Descripcion, float Cantidad, float Vendido, float Precio, String Unidad, String Imagen){
		
		this.Codigo = Codigo;
		this.Producto = Producto;
		this.Marca = Marca;
		this.Descripcion = Descripcion;
		this.Cantidad = Cantidad;
		this.Vendido = Vendido;
		this.Precio = Precio;
		this.Unidad = Unidad;
		this.Imagen = Imagen;
		
	}
	
	public Archivo(String Codigo, String Producto, String Marca, float Cantidad, float Precio, String Unidad){
		
		this.Codigo = Codigo;
		this.Producto = Producto;
		this.Marca = Marca;
		this.Descripcion = "None";
		this.Cantidad = Cantidad;
		this.Vendido = 0;
		this.Precio = Precio;
		this.Unidad = Unidad;
		this.Imagen = "./src/ResourcePackCaja/image-not-found.png";
		
	}

	public String getCode(){
		
		return Codigo;
		
	}

	public void setCode(String Codigo){
		
		this.Codigo = Codigo;
		
	}

	public String getProduct(){
		
		return Producto;
		
	}

	public void setProduct(String Producto){
		
		this.Producto = Producto;
		
	}
	
	public String getBrand(){
		
		return Marca;
		
	}

	public void setBrand(String Marca){
		
		this.Marca = Marca;
		
	}
	
	public String getDescription(){
		
		return Descripcion;
		
	}

	public void setDescription(String Descripcion){
		
		this.Descripcion = Descripcion;
		
	}
	
	public float getAmount(){
		
		return Cantidad;
		
	}

	public void setAmount(float Cantidad){	
		
		this.Cantidad = Cantidad;
		
	}
	
	public float getSold(){
		
		return Vendido;
		
	}
	
	public void setSold(float Vendido){
		
		this.Vendido = Vendido;
		
	}
	
	public float getPrice(){
		
		return Precio;
		
	}

	public void setPrice(float Precio){
		
		this.Precio = Precio;
		
	}
	
	public String getUnid(){
		
		return Unidad;
		
	}
	
	public void setUnid(String Unidad){
		
		this.Unidad = Unidad;
		
	}
	
	public String getImage(){
		
		return Imagen;
		
	}
	
	public void setImage(String Imagen){
		
		this.Imagen = Imagen;
		
	}
	
	public void withDraw(float wd){
		
		Cantidad-= wd;
		Vendido+= wd;
		
	}
	
	public static Archivo get(String Code){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Inventario WHERE `Code`= '"+Code+"' ");
			
			Archivo p = new Archivo(Script.getString("Code"), Script.getString("Product"), Script.getString("Brand"), Script.getString("Description"), Script.getFloat("Amount"), Script.getFloat("Sold"), Script.getFloat("Price"), Script.getString("Unid"), Script.getString("Image"));
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Selección:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
			return null;
			
		}
		
	}
	
	public static Archivo[] get(){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Inventario;");
			
			Archivo[] p = new Archivo[length()];
			
			int n = 0;
			
			while (Script.next()){
				
				p[n] = new Archivo(Script.getString("Code"), Script.getString("Product"), Script.getString("Brand"), Script.getString("Description"), Script.getFloat("Amount"), Script.getFloat("Sold"), Script.getFloat("Price"), Script.getString("Unid"), Script.getString("Image"));
				
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
			
			ResultSet Script = cn.consulta("SELECT COUNT(*) AS total FROM Inventario");
			
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
			
			cn.sentence("INSERT INTO Inventario (Code, Product, Brand, Description, Amount, Sold, Price, Unid, Image) VALUES "
			+"('"+this.getCode()+"', '"+this.getProduct()+"', '"+this.getBrand()+"', '"+this.getDescription()+"', "+this.getAmount()+", "+this.getSold()
			+", "+this.getPrice()+", '"+this.getUnid()+"', '"+this.getImage()+"');");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void remove(){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("DELETE FROM `Inventario` WHERE `Code`='"+this.getCode()+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Remoción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void edit(String Code){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("UPDATE `Inventario` SET 'Code' = '"+this.getCode()+
            "', 'Product' = '"+this.getProduct()+
            "', 'Brand' = '"+this.getBrand()+
            "', 'Description' = '"+this.getDescription()+
            "', 'Amount' = '"+this.getAmount()+
            "', 'Sold' = '"+this.getSold()+
            "', 'Price' = '"+this.getPrice()+
            "', 'Unid' = '"+this.getUnid()+
            "', 'Image' = '"+this.getImage()+
            "' WHERE Code = '"+Code+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Edición:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
}
