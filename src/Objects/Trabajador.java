package Objects;

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
	
}