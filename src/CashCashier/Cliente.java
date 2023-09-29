package CashCashier;

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
	
}