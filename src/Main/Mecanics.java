package Main;

import Objects.Conexion;
import Objects.Factura;
import Objects.Cliente;
import Objects.Trabajador;
import Objects.Archivo;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Mecanics {

    public static ArrayList<Archivo> Archive = new ArrayList<>();
    public static ArrayList<Cliente> Client = new ArrayList<>();
    public static ArrayList<Factura> Receipt = new ArrayList<>();
    public static ArrayList<Trabajador> Employe = new ArrayList<>();

    public static int getLanguage(boolean b) {

        int n = 0;

        if (b == true) {

            Conexion cn = new Conexion();

            n = cn.getLanguage();

            cn.Close();

        }

        return n;

    }

    public static void setLanguage(boolean b, int l) {

        if (b == true) {

            Conexion cn = new Conexion();

            cn.setLanguage(l);

            cn.Close();

        }

    }

    public static int getMode(boolean b) {

        int n = 0;

        if (b == true) {

            Conexion cn = new Conexion();

            n = cn.getMode();

            cn.Close();

        }

        return n;

    }

    public static void setMode(boolean b, int m) {

        if (b == true) {

            Conexion cn = new Conexion();

            cn.setMode(m);

            cn.Close();

        }

    }

    public static String getTitle(boolean b) {

        String wd = "";

        if (b == true) {

            Conexion cn = new Conexion();

            wd = cn.getTitle();

            cn.Close();

        }

        return wd;

    }

    public static void setTitle(boolean b, String Title) {

        if (b == true) {

            Conexion cn = new Conexion();

            cn.setTitle(Title);

            cn.Close();

        }

    }

    public static String getAddress(boolean b) {

        String wd = "";

        if (b == true) {

            Conexion cn = new Conexion();

            wd = cn.getAddress();

            cn.Close();

        }

        return wd;

    }

    public static void setAddress(boolean b, String Address) {

        if (b == true) {

            Conexion cn = new Conexion();

            cn.setAddress(Address);

            cn.Close();

        }

    }

    public static void getFile(boolean b) {

        long n = 0;

        if (b == true) {

            Mecanics.Archive.clear();

            Conexion cn = new Conexion();
            Archivo p = cn.getArchivo(n);

            while (p != null) {

                Mecanics.Archive.add(p);
                n++;
                p = cn.getArchivo(n);

            }

            cn.Close();

        }

    }

    public static void setFile(boolean b) {

        if (b == true) {

            Conexion cn = new Conexion();

            cn.clearArchivo();

            if (Mecanics.Archive.size() != 0) {

                for (Archivo p : Mecanics.Archive) {

                    cn.setArchivo(p);

                }

            }

            cn.Close();

        }

    }

    public static void getClient(boolean b) {

        long n = 0;

        if (b == true) {

            Mecanics.Client.clear();

            Conexion cn = new Conexion();
            Cliente p = cn.getCliente(n);

            while (p != null) {

                Mecanics.Client.add(p);
                n++;
                p = cn.getCliente(n);

            }

            cn.Close();

        }

    }

    public static void setClient(boolean b) {

        if (b == true) {

            Conexion cn = new Conexion();

            cn.clearCliente();

            if (Mecanics.Client.size() != 0) {

                for (Cliente p : Mecanics.Client) {

                    cn.setCliente(p);

                }

            }

            cn.Close();

        }

    }

    public static void getReceipt(boolean b) {

        long n = 0;

        if (b == true) {

            Conexion cn = new Conexion();
            Factura p = cn.getFactura(n);

            while (p != null) {

                Mecanics.Receipt.add(p);
                n++;
                p = cn.getFactura(n);

            }

            cn.Close();

        }

    }

    public static void setReceipt(boolean b) {

        if (b == true) {

            Conexion cn = new Conexion();

            cn.clearFactura();

            if (Mecanics.Receipt.size() != 0) {

                for (Factura p : Mecanics.Receipt) {

                    cn.setFactura(p);

                }

            }

            cn.Close();

        }

    }

    public static void getEmploye(boolean b) {

        String[] parts;

        if (b == true) {

            Mecanics.Employe.clear();

            try (BufferedReader nbr = new BufferedReader(new FileReader("Trabajadores.txt"));) {

                String nlinea = nbr.readLine();

                while (nlinea != null) {

                    parts = nlinea.split(",");

                    Mecanics.Employe.add(new Trabajador(parts[0].trim(), parts[1].trim(), parts[2], parts[3], parts[4], parts[5], parts[6], parts[7].charAt(0), Byte.parseByte(parts[8]), Boolean.parseBoolean(parts[9])));

                    nlinea = nbr.readLine();

                }

            } catch (IOException e) {

                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

    public static void setEmploye(boolean b) {

        if (b == true) {

            try (BufferedWriter nbw = new BufferedWriter(new FileWriter("Trabajadores.txt", false))) {

                nbw.write("");

                for (Trabajador p : Mecanics.Employe) {

                    nbw.write(p.getCode() + "," + p.getID() + "," + p.getName() + "," + p.getLastName() + "," + p.getPhone() + "," + p.getEmail() + "," + p.getAddress() + "," + p.getGender() + "," + p.getAge() + "," + p.getAdmin());
                    nbw.newLine();

                }

            } catch (IOException e) {

                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

    public static boolean ValidarEmail(String email) {

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public static String FacturaCode(String Time) {

        String[] parts = Time.split("-");
        String Date = parts[0].trim();
        String Hour = parts[1].trim();
        String Code = Date.charAt(Date.length() - 2) + "" + Date.charAt(Date.length() - 1);
        long n = 0;

        parts = Date.split("/");
        Code += parts[1];

        n = Long.parseLong(parts[0]);
        parts = Hour.split(":");

        for (String p : parts) {

            n += Long.parseLong(p);

        }

        Code = "FTRA-" + Code + n + Receipt.size();

        return Code;

    }
	
	public static void main(String[] abc){
		
		Conexion cn = new Conexion();
		
		Factura p = cn.getFactura(1);
		
		Facturar(p);
		
		cn.Close();
		
		/*byte edad = 18;
		
		Factura p = new Factura("FTRA-23091020",150000,18000.00,132000.00,Conexion.getSerieMatrix("CP1;Masa de Cerdo;2.0;21200.0$;42400.0$;CP2;Lomo de Cerdo;4.0;22400.0$;89600.0$"),
		"10/09/2023 - 22:27:43",new Trabajador("2220080008","1043641881","Felipe","May Salguedo","3016184993",
		"felipemaysalguedo@gmail.com","Trv 54 #80-26 Urb Portales de Alicante C2 B3 Apto 306Trv 54 #80-26 Urb Portales de Alicante C2 B3 Apto 306",'M',edad,true),
		new Cliente("Felipe","May Salguedo","1043641881","3016184993",
		"felipemaysalguedo@gmail.com","Trv 54 #80-26 Urb Portales de Alicante C2 B3 Apto 306Trv 54 #80-26 Urb Portales de Alicante C2 B3 Apto 306"));//*/
		
	}

    public static void Facturar(Factura p) {

        int m = getMode(true);
        int l = getLanguage(true);
		
		Path Downloads = Paths.get(System.getProperty("user.home"), "Downloads");
		String url = Downloads.toString()+"\\";
		
		try (PdfWriter pdfw = new PdfWriter(new File(url+p.getCode()+".pdf"))){
			
			PdfDocument pdfdoc = new PdfDocument(pdfw);
			Document doc = new Document(pdfdoc);
			
			Paragraph line = new Paragraph("Wasaaa!!!");
			
			doc.add(line);
			
			doc.close();
			pdfdoc.close();
			
		}catch (Exception e){
			
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
    }

    public static int getArchive(String wd) {

        if (Archive.size() != 0) {

            for (int i = 0; i < Archive.size(); i++) {

                Archivo p = Archive.get(i);

                if (wd.equalsIgnoreCase(p.getCode()) == true || wd.equalsIgnoreCase(p.getProduct()) == true) {

                    return i;

                }

            }

        }

        return -1;

    }

    public static int getClient(String wd) {

        if (Client.size() != 0) {

            for (int i = 0; i < Client.size(); i++) {

                Cliente p = Client.get(i);

                if (wd.equalsIgnoreCase(p.getID()) == true) {

                    return i;

                }

            }

        }

        return -1;

    }

    public static int getReceipt(String wd) {

        if (Receipt.size() != 0) {

            for (int i = 0; i < Receipt.size(); i++) {

                Factura p = Receipt.get(i);

                if (wd.equalsIgnoreCase(p.getCode()) == true) {

                    return i;

                }

            }

        }

        return -1;

    }

    public static int getEmploye(String wd) {

        if (Employe.size() != 0) {

            for (int i = 0; i < Employe.size(); i++) {

                Trabajador p = Employe.get(i);

                if (wd.equalsIgnoreCase(p.getCode()) == true || wd.equalsIgnoreCase(p.getID()) == true) {

                    return i;

                }

            }

        }

        return -1;

    }

    public static String DeleteChar(String cadena, int indice) {

        String nc = "";

        for (int i = 0; i < cadena.length(); i++) {

            if (i == indice) {
                continue;
            }

            nc += cadena.charAt(i) + "";

        }

        return nc;

    }

    public static boolean Allowed(String ca) {//Recibe un String y verifica que sea un número, si no lo es ==false, sino ==true

        String bank[] = {"-", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
        boolean b = false;
        int p = 0, v = 0, m = 0;

        for (int c = 0; c < ca.length(); c++) {

            for (int f = 0; f < bank.length; f++) {

                if (bank[f].equalsIgnoreCase(ca.charAt(c) + "") == true) {

                    v += 1;

                }

            }

        }

        if (v == ca.length()) {

            for (int c = 0; c < ca.length(); c++) {

                for (int f = 0; f < bank.length; f++) {

                    if (bank[bank.length - 1].equalsIgnoreCase(ca.charAt(c) + "") == true && c == ca.length() - 1) {

                        b = false;
                        break;

                    } else {

                        b = true;

                    }

                }

                if (b == false) {
                    break;
                }

            }

            for (int c = 0; c < ca.length(); c++) {

                for (int f = 0; f < bank.length; f++) {

                    if (bank[0].equalsIgnoreCase(ca.charAt(c) + "") == true && c != 0) {

                        b = false;
                        break;

                    } else {

                        b = true;

                    }

                }

                if (b == false) {
                    break;
                }

            }

            for (int c = 0; c < ca.length(); c++) {

                if (bank[0].equalsIgnoreCase(ca.charAt(c) + "") == true) {
                    m += 1;
                }

            }

            for (int c = 0; c < ca.length(); c++) {

                if (bank[bank.length - 1].equalsIgnoreCase(ca.charAt(c) + "") == true) {
                    p += 1;
                }

            }

            if (p > 1) {
                b = false;
            }
            if (m > 1) {
                b = false;
            }
            if (ca.length() == 1 && bank[bank.length - 1].equalsIgnoreCase(ca.charAt(0) + "") == true) {
                b = false;
            }
            if (ca.length() == 1 && bank[0].equalsIgnoreCase(ca.charAt(0) + "") == true) {
                b = false;
            }

        } else {
            b = false;
        }

        return b;

    }

}
