package Objects;

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
		this.Precio = Precio;
		this.Unidad = Unidad;
		this.Imagen = Imagen;
		
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
	
}