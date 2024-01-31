package Main;

import Objects.Conexion;
import Objects.Factura;
import Objects.Cliente;
import Objects.Trabajador;
import Objects.Archivo;
import Objects.ComponentBuilder;

import java.sql.SQLException;
import java.sql.ResultSet;

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

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Component;

import java.util.EventObject;
import javax.imageio.ImageIO;

public class Mecanics {

	public static int getMode(){

        try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Aspectos WHERE `Key`= '"+1+"' ");
			
			int p = Script.getInt("Mode");
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			return 0;
			
		}

    }

    public static void setMode(int m) {

        try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("UPDATE Aspectos SET Mode = "+m+" WHERE `Key`= '"+1+"' ");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}

    }

    public static int getLanguage() {

		try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Aspectos WHERE `Key`= '"+1+"' ");
			
			int p = Script.getInt("Language");
			
			cn.Close();
			
			return p;
			
		}catch(SQLException e){
			
			return 0;
			
		}

    }

    public static void setLanguage(int l) {

        try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("UPDATE Aspectos SET Language = "+l+" WHERE `Key`= '"+1+"' ");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}

    }

    public static String getTitle() {

        try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Aspectos WHERE `Key`= '"+1+"' ");
			
			String wd = Script.getString("Title");
			
			cn.Close();
			
			return wd;
			
		}catch(SQLException e){
			
			return "Error";
			
		}

    }

    public static void setTitle(String Title) {

        try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("UPDATE `Aspectos` SET 'Title' = '"+Title+"' WHERE `Key`= '"+1+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}

    }

    public static String getAddress(){

        try{
			
			Conexion cn = new Conexion();
			
			ResultSet Script = cn.consulta("SELECT * FROM Aspectos WHERE `Key`= '"+1+"' ");
			
			String wd = Script.getString("Address");
			
			cn.Close();
			
			return wd;
			
		}catch(SQLException e){
			
			return "Error";
			
		}

    }

    public static void setAddress(String Address) {

        try{
			
			Conexion cn = new Conexion();
			
			cn.sentence("UPDATE `Aspectos` SET 'Address' = '"+Address+"' WHERE `Key`= '"+1+"';");
			
			cn.Close();
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null,"Error de Inserción:  "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
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

        Code = "FTRA-" + Code + n + Factura.length();

        return Code;

    }

    public static void Facturar(Factura p) {

        int l = getLanguage();
		
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
			
			Paragraph Title = new Paragraph(getTitle());
			Title.setFontSize(20);
            Title.setBold();
            //Title.setFont(fuente);
            Title.setTextAlignment(TextAlignment.CENTER);
            Title.setVerticalAlignment(VerticalAlignment.MIDDLE);
			
			Paragraph Address = new Paragraph(getAddress());
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
				
				q = Archivo.get(p.getBuyout()[i][0].toString());
				
				if (q!=null){
					
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

    public static boolean Allowed(String wd) {//Recibe un String y verifica que sea un número, si no lo es ==false, sino ==true

		try{
			
			Double.parseDouble(wd);
			
			return true;
			
		}catch(Exception e){
			
			return false;
			
		}

    }

	public static void iArchivo(String archivo, String carpeta, String nom){
		
		try {
			
			File origenFile = new File(archivo);
			File destinoFile = new File(carpeta, nom + "." + getExtension(origenFile));
			
			Files.copy(origenFile.toPath(), destinoFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e){
			
			JOptionPane.showMessageDialog(null, "Error de copiado de imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public static String getExtension(File rut) {
		
		String [] arc= rut.getName().split("\\.");
		
		return arc[1];
		
	}


	public static File actionimage(JPanel panel) {
		
		File archivo=new File("./src/ResourcePackCaja/image-not-found.png");
		
		try {
		
			try {
			  
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			} catch (Exception e) {}

			JFileChooser jf=new JFileChooser();
			jf.setDialogTitle("Searcher");
			jf.showSaveDialog(panel);
			
			archivo= jf.getSelectedFile();
			String arc= Mecanics.getExtension(archivo);
			
			if(arc.equalsIgnoreCase("png")==false && arc.equalsIgnoreCase("jpg")==false) {
				
				String []error= {"ERROR, Seleccione un archivo con los formatos permitidos.","ERROR, Select a file with the allowed formats."};
				
			    JOptionPane.showMessageDialog(null, error[getLanguage()]+" \n\n->PNG\n->JPG\n", "Error", JOptionPane.ERROR_MESSAGE);
			    archivo=new File("./src/ResourcePackCaja/image-not-found.png");
				
			}

		}catch(Exception e) {
			
			String []error= {"Error de búsqueda: Documento no encontrado.","Search error: Document not found."};
			JOptionPane.showMessageDialog(null, error[getLanguage()], "Error", JOptionPane.ERROR_MESSAGE);
			archivo=new File("./src/ResourcePackCaja/image-not-found.png");
			
		}

		return archivo;
	} 

    //Daniel me dijo que metiera estas dos funciones, eliminar este comentario una vez leido -Cleiver
    //Cuado un Textfield es seleccionado que se cambie el color del borde
    public static FocusListener FocusText(JTextField jtext) {
        return new FocusListener() {
            @Override public void focusGained(FocusEvent evt) { jtext.setBorder(new MatteBorder(0, 0, 2, 0, java.awt.Color.BLUE)); }
            @Override public void focusLost(FocusEvent evt) { jtext.setBorder(new MatteBorder(0, 0, 2, 0, java.awt.Color.GRAY)); }
        };
    }

    //Cambiar de Textfield con las teclas
    public static KeyAdapter ChangeTextfield(JTextField down, JTextField up) {
        return new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) && down != null) { down.requestFocus(); }
                if (e.getKeyCode() == KeyEvent.VK_UP && up != null) { up.requestFocus(); }
            }
        };
    }
	
	private static boolean value = false;
	
	public static boolean Leave(JFrame parentComponent){
		
		int z = 0;
		
		final int m = getMode();
		final int l = getLanguage();
		final java.awt.Color[] Fondo = {new java.awt.Color(238, 248, 254), new java.awt.Color(20, 35, 54)};
		
		final String[] MecanicsCerrar = {"xLight", "xDark"};
		final String[] MecanicsSalir = {"SalirLight", "SalirDark"};
		final String[] MecanicsMenu = {"MenuLight", "MenuDark"};
		
		final String[] ExitTexto = {"Salir", "Exit"};
		final String[] MenuTexto = {"Menú", "Menu"};
		
		final ComponentBuilder cp = new ComponentBuilder("./src/ResourcePackCaja", Fondo[m]);
		
		if (m==1){cp.setForeground(java.awt.Color.WHITE);}
		
		JPanel Background = cp.buildPanel(cp.doBounds(0, 0, 300, 400), new int[] {0, 0, 300, 400, 60, 60}, 0, 0, Fondo[m]);
		JButton Close = cp.buildButton("", cp.doBounds(Background.getWidth()-50, 30, 30, 30), MecanicsCerrar[m], JButton.CENTER, JButton.CENTER, JButton.CENTER, true, true);
		JButton Exit = cp.buildButton("", cp.doBounds(Background.getWidth()/2-100, Background.getHeight()/2-100, 200, 50), MecanicsSalir[m], JButton.CENTER, JButton.RIGHT, JButton.CENTER, true, true);
		JButton Menu = cp.buildButton("", cp.doBounds(Background.getWidth()/2-100, Background.getHeight()/2+40, 200, 50), MecanicsMenu[m], JButton.CENTER, JButton.RIGHT, JButton.CENTER, true, true);

		Background.setLayout(null);
		Background.setComponentZOrder(Close, z);	z++;
		Background.setComponentZOrder(Exit, z);	z++;
		Background.setComponentZOrder(Menu, z);	z++;
		
		JDialog customDialog = new JDialog(parentComponent, "", true);
        customDialog.setSize(300, 400);
        customDialog.setLocationRelativeTo(parentComponent);
        customDialog.setUndecorated(true);
        customDialog.setShape(new RoundRectangle2D.Double(0, 0, Background.getWidth(), Background.getHeight(), 60, 60));
		
		Menu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				customDialog.dispose();
				
				value = true;

			}

		});
		
		Close.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				customDialog.dispose();
				
				value = false;

			}

		});
		
		Exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				
				try {
					
					Thread.sleep(200);
					
				} catch (InterruptedException ev){
					
					ev.printStackTrace();
					
				}
				
				System.exit(0);

			}

		});
		
		Menu.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Menu.setText(MenuTexto[l]);

			}

			public void mousePressed(MouseEvent e){

				mouseExited(e);

			}

			public void mouseExited(MouseEvent e) {

				Menu.setText("");

			}

		});
		
		Exit.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				Exit.setText(ExitTexto[l]);

			}

			public void mousePressed(MouseEvent e){

				mouseExited(e);

			}

			public void mouseExited(MouseEvent e) {

				Exit.setText("");

			}

		});

        customDialog.add(Background);
		
		customDialog.setVisible(true);
		
		return value;
		
	}
	
	private static MouseAdapter mouseListener;
	
	public static void txtErrorMessage(JTextField TextField, String Message){
		
		JPopupMenu PopUpMenu = new JPopupMenu();
        PopUpMenu.add(new JMenuItem(Message));
        
        TextField.removeMouseListener(mouseListener);
        
        mouseListener = new MouseAdapter() {
			
			private String BackUpErrorMessage = TextField.getText().trim();
			
            public void mouseEntered(MouseEvent e){
				
				String bup = TextField.getText().trim();
			
            	if (BackUpErrorMessage.equalsIgnoreCase(bup)==true){
					
					PopUpMenu.show(TextField, 0, TextField.getHeight());
					
            	}else{
					
					TextField.removeMouseListener(this);
					
				}
            	
            }
			
			public void mouseClicked(MouseEvent e) {

				mouseEntered(e);

			}
			
			public void mousePressed(MouseEvent e){
				
				mouseEntered(e);
				
			}

			public void mouseReleased(MouseEvent e) {

				mouseEntered(e);

			}

            public void mouseExited(MouseEvent e) {
				
                PopUpMenu.setVisible(false);
				
            }
			
        };
        
        TextField.addMouseListener(mouseListener);
        
	}

	public static void lblphoto(String ruta, JLabel label) {
		
		if(new File(ruta).exists()==false) {
			ruta="./src/ResourcePackCaja/image-not-found.png";
		}
		
		label.setIcon(new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH)));
	}
	
}
