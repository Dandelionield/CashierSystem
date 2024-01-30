package Objects;

import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

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
	
	public Factura(String Code, long Pay, double Change, double Total, Object[][] Buyout, String Date, Trabajador Employe){
		
        this.Code = Code;
		this.Pay = Pay;
		this.Change = Change;
		this.Total = Total;
		this.Buyout = Buyout;
		this.Date = Date;
		this.Employe = Employe;
		this.Client = new Cliente();
		
    }
	
	public Factura(Trabajador Employe){
		
        this.Code = "";
		this.Pay = 0;
		this.Change = 0;
		this.Total = 0;
		this.Buyout = new Object[][] {{000,"None",0,0,0}};
		this.Date = "";
		this.Employe = Employe;
		this.Client = new Cliente();
		
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
	
	public static Factura get(String Code){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Factura WHERE `Code`= '"+Code+"' ");
			
			Factura p = new Factura(Script.getString("Code"), Script.getLong("Pay"), Script.getDouble("Change"), Script.getDouble("Total"), getSerieMatrix(Script.getString("Buyout")), Script.getString("Date"), getSerieTrabajador(Script.getString("Employe")), getSerieCliente(Script.getString("Client")));
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			return null;
			
		}
		
	}
	
	public static Factura[] get(){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Factura;");
			
			Factura[] p = new Factura[length()];
			
			int n = 0;
			
			while (Script.next()){
				
				p[n] = new Factura(Script.getString("Code"), Script.getLong("Pay"), Script.getDouble("Change"), Script.getDouble("Total"), getSerieMatrix(Script.getString("Buyout")), Script.getString("Date"), getSerieTrabajador(Script.getString("Employe")), getSerieCliente(Script.getString("Client")));
				
				n++;
				
			}
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			return null;
			
		}
		
	}
	
	public static int length(){
		
		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT COUNT(*) AS total FROM Factura");
			
			int p = Script.getInt("total");
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			return 0;
			
		}
		
	}
	
	public void add(){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("INSERT INTO Factura (Code, Pay, Change, Total, Buyout, Date, Employe, Client) VALUES "
			+"('"+this.getCode()+"', "+this.getPay()+", "+this.getChange()+", "+this.getTotal()+", '"+setSerieMatrix(this.getBuyout())
			+"', '"+this.getDate()+"', '"+setSerieTrabajador(this.getEmploye())+"', '"+setSerieCliente(this.getClient())+"');");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void remove(){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("DELETE FROM `Factura` WHERE `Code`='"+this.getCode()+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Remoción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void edit(String Code){
		
		try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("UPDATE `Factura` SET 'Code' = '"+this.getCode()+
            "', 'Pay' = '"+this.getPay()+
            "', 'Change' = '"+this.getChange()+
            "', 'Total' = '"+this.getTotal()+
            "', 'Buyout' = '"+setSerieMatrix(this.getBuyout())+
            "', 'Date' = '"+this.getDate()+
            "', 'Employe' = '"+setSerieTrabajador(this.getEmploye())+
            "', 'Client' = '"+setSerieCliente(this.getClient())+
            "' WHERE Code = '"+Code+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Edición:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
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
		
		return new Trabajador(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7].charAt(0), Byte.parseByte(parts[8]), Boolean.parseBoolean(parts[9]), parts[10]);
		
	}
	
	private static String setSerieTrabajador(Trabajador p){
		
		return p.getCode()+","+p.getID()+","+p.getName()+","+p.getLastName()+","+p.getPhone()+","+p.getEmail()+","+p.getAddress()+","+p.getGender()+","+p.getAge()+","+((p.getAdmin()==true) ? 1 : 0)+","+p.getImage();
		
	}
	
	private static Cliente getSerieCliente(String serializedClient){
		
		String[] parts = serializedClient.split(",");
		
		return new Cliente(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
		
	}
	
	private static String setSerieCliente(Cliente p){
		
		return p.getName()+","+p.getLastName()+","+p.getID()+","+p.getPhone()+","+p.getEmail()+","+p.getAddress();
		
	}
	
}