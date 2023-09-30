package Objects;

public class Factura{
	
    private String Code;
	private String ID;
	private String Name;
	private String Date;
	private long Pay;
	private double Change;
	private double Total;
    private Object[][] Buyout;
	private Trabajador Employe;
	private Cliente Client;

    public Factura(String Code, long Pay, double Change, double Total, Object[][] Buyout, String Date, Trabajador Employe, Cliente Client){
		
        this.Code = Code;
		this.Pay = Pay;
		this.Change = Change;
		this.Total = Total;
		this.Buyout = Buyout;
		this.Date = Date;
		this.Employe = Employe;
		this.Client = Client;
		
    }

    public String getCode(){
		
        return Code;
		
	}
	
	public void setCode(String Code){
		
        this.Code = Code;
		
	}
	
	public long getPay(){
		
        return Pay;
		
    }
	
	public void setPay(long Pay){
		
        this.Pay = Pay;
		
	}
	
	public double getChange(){
		
        return Change;
		
    }
	
	public void setChange(double Change){
		
        this.Change = Change;
		
	}
	
	public double getTotal(){
		
        return Total;
		
    }
	
	public void setTotal(double Total){
		
        this.Total = Total;
		
	}
	
	public Object[][] getBuyout(){
		
        return Buyout;
		
    }
	
	public void setBuyout(Object[][] Buyout){
		
        this.Buyout = Buyout;
		
	}
	
	public String getDate(){
		
		return Date;
		
	}
	
	public void setDate(String Date){
		
		this.Date = Date;
		
	}
	
	public Trabajador getEmploye(){
		
		return Employe;
		
	}
	
	public void setEmploye(Trabajador Employe){
		
		this.Employe = Employe;
		
	}
	
	public Cliente getClient(){
		
		return Client;
		
	}
	
	public void setClient(Cliente Client){
		
		this.Client = Client;
		
	}
	
}