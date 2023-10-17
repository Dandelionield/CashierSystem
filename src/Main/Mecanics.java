package Main;

import Objects.Conexion;
import Objects.Factura;
import Objects.Cliente;
import Objects.Trabajador;
import Objects.Archivo;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.font.PdfEncodings;

/*import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;//*/
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Desktop;
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

    public static void Facturar(Factura p) {

        int l = getLanguage(true);
		
		Archivo q = null;
		int indice = 0;
		String[] ClientTexto = {"Cliente: ", "Client: "};
		String[] EmployeTexto = {"Empleado: ", "Employee: "};
		String[] ProductTexto = {"Producto", "Product"};
		String[] AmountTexto = {"Cantidad", "Amount"};
		String[] PriceTexto = {"Precio", "Price"};
		String[] PayTexto = {"Pago: ", "Pay: "};
		String[] ChangeTexto = {"Vuelto: ", "Change: "};
		String[] TotalTexto = {"Total a Pagar: ", "Total Pay: "};
		
		Path Downloads = Paths.get(System.getProperty("user.home"), "Downloads");
		String url = Downloads.toString()+"\\"+p.getCode()+".pdf";
		
		try (PdfWriter pdfw = new PdfWriter(new File(url))){
			
			PdfDocument pdfdoc = new PdfDocument(pdfw);
			pdfdoc.setDefaultPageSize(PageSize.LETTER);
			Document doc = new Document(pdfdoc);
			
			Paragraph Date = new Paragraph(p.getDate());
			Date.setFontSize(13);
            Date.setBold();
            Date.setTextAlignment(TextAlignment.LEFT);
            Date.setVerticalAlignment(VerticalAlignment.TOP);
			Date.setFixedPosition(36, 750, 200);
			
			Paragraph Code = new Paragraph(p.getCode());
			Code.setFontSize(13);
            Code.setBold();
            Code.setTextAlignment(TextAlignment.LEFT);
            Code.setVerticalAlignment(VerticalAlignment.TOP);
			Code.setFixedPosition(500, 750, 200);
			
            Image Logo = new Image(ImageDataFactory.create("./src/ResourcePackCaja/Logo.png"));
            Logo.setAutoScale(false);
			Logo.scaleToFit(200, 200);
            Logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
			
			Paragraph Title = new Paragraph(getTitle(true));
			Title.setFontSize(20);
            Title.setBold();
            //Title.setFont(fuente);
            Title.setTextAlignment(TextAlignment.CENTER);
            Title.setVerticalAlignment(VerticalAlignment.MIDDLE);
			
			Paragraph Address = new Paragraph(getAddress(true));
			Address.setFontSize(16);
            Address.setBold();
            Address.setTextAlignment(TextAlignment.CENTER);
            Address.setVerticalAlignment(VerticalAlignment.MIDDLE);
			
			Text BoldClient = new Text(ClientTexto[l]);
			BoldClient.setFontSize(13);
			BoldClient.setBold();
			
			Text NormalClient = new Text(p.getClient().getName()+" "+p.getClient().getLastName());
			NormalClient.setFontSize(13);
			
			Paragraph Client = new Paragraph("\n");
			Client.add(BoldClient);
			Client.add(NormalClient);
            Client.setTextAlignment(TextAlignment.LEFT);
			
			Text BoldEmploye = new Text(EmployeTexto[l]);
			BoldEmploye.setFontSize(13);
			BoldEmploye.setBold();
			
			Text NormalEmploye = new Text(p.getEmploye().getName()+" "+p.getEmploye().getLastName());
			NormalEmploye.setFontSize(13);
			
			Paragraph Employe = new Paragraph("\n");
			Employe.add(BoldEmploye);
			Employe.add(NormalEmploye);
            Employe.setTextAlignment(TextAlignment.RIGHT);
			
			Table Info = new Table(2);
            Info.setWidth(UnitValue.createPercentValue(100));
			Info.addCell(Client);
			Info.addCell(Employe);
			Info.getCell(0, 0).setBorder(Border.NO_BORDER);
			Info.getCell(0, 1).setBorder(Border.NO_BORDER);
			
			Table Buyout = new Table(4);
			Buyout.setWidth(UnitValue.createPercentValue(100));
			Buyout.addCell(new Paragraph(ProductTexto[l]));
			Buyout.addCell(new Paragraph(AmountTexto[l]));
			Buyout.addCell(new Paragraph(PriceTexto[l]));
			Buyout.addCell(new Paragraph("Total"));
			Buyout.getCell(0, 0).setBorder(Border.NO_BORDER).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);
			Buyout.getCell(0, 1).setBorder(Border.NO_BORDER).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);
			Buyout.getCell(0, 2).setBorder(Border.NO_BORDER).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);
			Buyout.getCell(0, 3).setBorder(Border.NO_BORDER).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);
			
			for (int i=0; i<p.getBuyout().length; i++){
				
				indice = getArchive(p.getBuyout()[i][0].toString());
				
				if (indice!=-1){
					
					q = Archive.get(i);
					
					Buyout.addCell(new Paragraph(p.getBuyout()[i][1].toString()+" - "+q.getBrand()));
					Buyout.addCell(new Paragraph(p.getBuyout()[i][2].toString()+" "+q.getUnid()));
					
				}else{
					
					Buyout.addCell(new Paragraph(p.getBuyout()[i][1].toString()));
					Buyout.addCell(new Paragraph(p.getBuyout()[i][2].toString()));
					
				}
				
				Buyout.addCell(new Paragraph(p.getBuyout()[i][3].toString()));
				Buyout.addCell(new Paragraph(p.getBuyout()[i][4].toString()));
				
				Buyout.getCell(i+1, 0).setBorder(Border.NO_BORDER).setFontSize(13).setTextAlignment(TextAlignment.CENTER);
				Buyout.getCell(i+1, 1).setBorder(Border.NO_BORDER).setFontSize(13).setTextAlignment(TextAlignment.CENTER);
				Buyout.getCell(i+1, 2).setBorder(Border.NO_BORDER).setFontSize(13).setTextAlignment(TextAlignment.CENTER);
				Buyout.getCell(i+1, 3).setBorder(Border.NO_BORDER).setFontSize(13).setTextAlignment(TextAlignment.CENTER);
				
			}
			
			Text BoldPay = new Text(PayTexto[l]);
			BoldPay.setFontSize(13);
			BoldPay.setBold();
			
			Text NormalPay = new Text(p.getPay()+"$");
			NormalPay.setFontSize(13);
			
			Paragraph Pay = new Paragraph();
			Pay.add(BoldPay);
			Pay.add(NormalPay);
            Pay.setTextAlignment(TextAlignment.LEFT);
			
			Text BoldChange = new Text(ChangeTexto[l]);
			BoldChange.setFontSize(13);
			BoldChange.setBold();
			
			Text NormalChange = new Text(p.getChange()+"$");
			NormalChange.setFontSize(13);
			
			Paragraph Change = new Paragraph();
			Change.add(BoldChange);
			Change.add(NormalChange);
            Change.setTextAlignment(TextAlignment.RIGHT);
			
			Text BoldTotal = new Text(TotalTexto[l]);
			BoldTotal.setFontSize(13);
			BoldTotal.setBold();
			
			Text NormalTotal = new Text(p.getTotal()+"$");
			NormalChange.setFontSize(13);
			
			Paragraph Total = new Paragraph();
			Total.add(BoldTotal);
			Total.add(NormalTotal);
            Total.setTextAlignment(TextAlignment.RIGHT);
			
			Table Info2 = new Table(3);
            Info2.setWidth(UnitValue.createPercentValue(100));
			Info2.addCell(Pay);
			Info2.addCell(Change);
			Info2.addCell(Total);
			Info2.getCell(0, 0).setBorder(Border.NO_BORDER);
			Info2.getCell(0, 1).setBorder(Border.NO_BORDER);
			Info2.getCell(0, 2).setBorder(Border.NO_BORDER);
			
			doc.add(Date);
			doc.add(Code);
			doc.add(Logo);
			doc.add(Title);
			doc.add(Address);
			doc.add(Info);
			doc.add(new Paragraph("\n\n"));
			doc.add(Buyout);
			doc.add(new Paragraph("\n\n"));
			doc.add(Info2);
			
			doc.close();
			pdfdoc.close();
			
			Desktop.getDesktop().open(new File(url));
			
		}catch (IOException e){
			
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

 public static void iArchivo(String archivo, String carpeta, String nom)  {
		
		 try {
	            
	            Path origen = Path.of(archivo);
	            Path destino = Path.of(carpeta, nom + "." + getExtension(new File(archivo)));

	            
	            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);

	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println("Error al copiar la imagen.");
	        }
		
		
		
		
	}
	

	public static String getExtension(File rut) {
		
		
		 String [] arc= rut.getName().split("\\.");

		
		return arc[1];
	}

}
