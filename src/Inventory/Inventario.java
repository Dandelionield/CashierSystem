package Inventory;
import java.awt.EventQueue;
import Objects.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;

import java.io.*; 

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.font.PdfEncodings;


import Main.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Inventario extends JFrame {



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Inventario frame = new Inventario(1,1);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private JLabel txttabla,txtcodigo, txtdescripcion, txtunidad, txtmarca, txtexistencia, txtproductos, txtprecio, txtInformacion, txtidioma;
	private JTextField codigo, producto, precio, existencias, marca;
	private JScrollPane scrolldescripcion;
	private String ruta="";
	public static int moder=0,lengu=0;
	
	private JTable table;	
	private DefaultTableModel modelo;
    
	
	private ImageIcon regi;
    static String [] len=new String[20];
    private JComboBox<String> unidad;
	private JLabel foto;
	private JTextArea descripcion;;
	static boolean access=false, idioma=true, modi=false;
	private JButton btnEliminar, btnimage, btnguardar;
	private JLabel lenguaje;
	private JLabel mode;
	private JLabel editar;
	private JLabel txtsold;
	private final Components cp = new Components("./src/ResourcePackCaja/", null, 0);
	private JLabel sold;
	private JLabel txtpopular;
	private JLabel popular;
	private JButton imprimir;
	private JLabel txtunpopular;
	private JLabel unpopular;
	private Archivo mayor;
	private Archivo menor;

	
	public Inventario(int theme,int leng) {
		
		moder=theme;
		lengu=leng;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/ResourcePackCaja/Inventario.png"));
			
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				int scp=JOptionPane.showConfirmDialog(null, "Desea abandonar?","Salir",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if(scp==JOptionPane.YES_OPTION) {
					
					Runner.contentPane.removeAll();
					
					Runner.Inicio = new Login();
					
					Runner.contentPane.add(Runner.Inicio, Integer.valueOf(0));
					
					Runner lg = new Runner();

					lg.setVisible(true);
					
					dispose();
					
					repaint();
				}
				
			}
		});
		
		
		setResizable(false);
		setTitle(len[19]);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1013, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(56, 24, 903, 504);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel fondo=new JLabel("");
		fondo.setBounds(0, 0, 1013, 609);
		ImageIcon imgfondo = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Fondo.JPG").getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH));
        fondo.setIcon(imgfondo);
        contentPane.add(fondo);
		
		foto = new JLabel("");
		foto.setBounds(25, 25, 204, 176);
		ImageIcon pfoto = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/image-not-found.png").getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH));
        foto.setIcon(pfoto);
		foto.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(foto);
		
		String[] titulos = {len[2], len[3], len[4], len[5], len[7]};
        modelo = new DefaultTableModel(null, titulos);

		table = new JTable();
		JLabel foto = this.foto;
		table.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseClicked(MouseEvent evt) {
				
				 Archivo miu;
			       
				 try {
					
					 if(evt.getClickCount()==1){
			            
			            JTable rec= (JTable)evt.getSource();
			            
			           codigo.setText(rec.getModel().getValueAt(rec.getSelectedRow(),0).toString());
			           
			           miu=muestra(codigo.getText());
			           producto.setText(miu.getProduct());
			           precio.setText(miu.getPrice()+"");
			           existencias.setText(miu.getAmount()+"");
			           

			           unidad.setSelectedItem(miu.getUnid());        
			          
			           marca.setText(miu.getBrand());        
			           descripcion.setText(miu.getDescription());        
			           sold.setText(miu.getSold()+"");
			           
			           ruta=miu.getImage();
			           
			           if(new File(miu.getImage()).exists()==false) {
			        	   
			        	   ruta="./src/ResourcePackCaja/image-not-found.png";
			           }
			           
			           ImageIcon ima = new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_AREA_AVERAGING));
			            foto.setIcon(ima);
			            foto.repaint();
			            
			           
			           repaint();
			            
			        }
					 
				} catch (Exception e) {
					
					repaint();
				}
				 
			       
				
				
			}
		});
		JScrollPane scrolltabla = new JScrollPane();
		scrolltabla.setBounds(512, 62, 381, 431);
		scrolltabla.setViewportView(table);
		table.setBackground(null);
		panel.add(scrolltabla);	
        table.setModel(modelo);		
        mostrar();

		txtInformacion = new JLabel(len[0]);
		txtInformacion.setBounds(262, 21, 191, 26);
		txtInformacion.setFont(new java.awt.Font("Arial", 3, 14));
		panel.add(txtInformacion);
		
		txttabla = new JLabel(len[1]);
		txttabla.setBounds(512, 25, 218, 21);
		txttabla.setFont(new java.awt.Font("Arial", 3, 14));
		panel.add(txttabla);
		
		txtcodigo = new JLabel(len[2]);
		txtcodigo.setBounds(262, 61, 60, 26);
		txtcodigo.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12));
		panel.add(txtcodigo);
		
		codigo = cp.TextPanel("", cp.setBounds(344, 65, 86, 20), SwingConstants.LEFT, new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);
		panel.add(codigo);
		codigo.setColumns(10);
		
		txtproductos = new JLabel(len[3]);
		txtproductos.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtproductos.setBounds(262, 98, 72, 26);
		panel.add(txtproductos);
		
		producto = cp.TextPanel("", cp.setBounds(344, 102, 144, 20), SwingConstants.LEFT, new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);
		panel.add(producto);
		producto.setColumns(10);
		
		txtprecio = new JLabel(len[4]);
		txtprecio.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtprecio.setBounds(262, 133, 72, 26);
		panel.add(txtprecio);
		
		precio = cp.TextPanel("", cp.setBounds(344, 137, 144, 20), SwingConstants.LEFT, new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);
		panel.add(precio);
		precio.setColumns(10);		
		
		txtexistencia = new JLabel(len[5]);
		txtexistencia.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtexistencia.setBounds(262, 170, 72, 26);
		panel.add(txtexistencia);
		
		existencias = cp.TextPanel("", cp.setBounds(344, 174, 144, 20), SwingConstants.LEFT, new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);
		panel.add(existencias);
		existencias.setColumns(10);
		
		txtmarca = new JLabel(len[7]);
		txtmarca.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtmarca.setBounds(262, 242, 72, 26);
		panel.add(txtmarca);
		
		marca = cp.TextPanel("", cp.setBounds(344, 246, 144, 20), SwingConstants.LEFT, new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);
		panel.add(marca);
		marca.setColumns(10);
		
		txtunidad = new JLabel(len[6]);
		txtunidad.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtunidad.setBounds(262, 207, 72, 26);
		panel.add(txtunidad);
		
		DefaultComboBoxModel<String> modl = new DefaultComboBoxModel<>(new String[] {"U", "Kg", "g", "L"});
		unidad = new JComboBox<>(modl);
		unidad.setBounds(344, 213, 60, 22);
		panel.add(unidad);
		
		txtdescripcion = new JLabel(len[8]);
		txtdescripcion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtdescripcion.setBounds(262, 280, 72, 26);
		panel.add(txtdescripcion);
		
		descripcion = new JTextArea();
		descripcion.setBounds(262, 317, 226, 176);
		descripcion.setLineWrap(true);
		descripcion.setWrapStyleWord(true);
		descripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollarea = new JScrollPane(descripcion);
        scrollarea.setBounds(262, 317, 226, 176);
		panel.add(scrollarea);		
		
		btnimage = new JButton(len[9]);
		btnimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent d) {
				
		       File archivo = actionimage();
		        
		        
		        if(archivo !=null){
		               
		               ruta= archivo.getAbsolutePath();   
		               
		            ImageIcon ima = new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH));
		               foto.setIcon(ima);
		               foto.repaint();
		           }
				
			}
		});
		btnimage.setBounds(25, 200, 204, 23);
		panel.add(btnimage);
		
		btnguardar = new JButton(len[10]);
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

		        if (access == false) {
		        	
		        	
		        	
		       	Conexion a = new Conexion();
		        			
		            try {

		                Archivo prod = new Archivo(codigo.getText(), producto.getText(), marca.getText(), descripcion.getText(), Float.parseFloat(existencias.getText()), 0, Float.parseFloat(precio.getText()), unidad.getSelectedItem().toString(), "./src/Inventory/productPhoto/"+codigo.getText()+"."+Mecanics.getExtension(new File(ruta)));

		                a.sentence("INSERT INTO `Inventario` (`Code`, `Product`, `Brand`, `Description`, `Amount`, `Sold`,`Price`,`Unid`,`Image`) "
		                        + "VALUES ('" + prod.getCode().toUpperCase() + "', '" + prod.getProduct() + "', '" + prod.getBrand() + "', '" + prod.getDescription() + "', '" + prod.getAmount() + "', 0, '" + prod.getPrice() + "', '" + prod.getUnid() + "', '" + prod.getImage() + "');");
		                 
		                Mecanics.iArchivo(ruta,"./src/Inventory/productPhoto",codigo.getText());
		                
		            } catch (Exception b) {
		                JOptionPane.showMessageDialog(null, "Verifique los datos\nError: " + b.toString());
		                repaint();
		            }
		            
		            
		        }

		        
		        if (access == true) {
		        		
		        	
		        	
		           	Conexion a = new Conexion();
		        	
		            try {
		            	
		            	String imgurl="./src/Inventory/productPhoto/"+codigo.getText()+"."+Mecanics.getExtension(new File(ruta));
		            	
		                a.sentence("UPDATE `Inventario` SET  `Product`='"+producto.getText()+
		                		"', `Brand`='"+marca.getText()+
		                		"', `Description`='"+descripcion.getText()+"', `Amount`='"+Float.parseFloat(existencias.getText())+
		                		"',`Price`='"+Float.parseFloat(precio.getText())+
		                		"',`Unid`='"+unidad.getSelectedItem().toString()+"',`Image`='"+imgurl+
		                		"'  WHERE `Code`='" + codigo.getText().toUpperCase() + "';");
		                
		                Mecanics.iArchivo(ruta,"./src/Inventory/productPhoto",codigo.getText());
		                
		            } catch (Exception b) {
		                JOptionPane.showMessageDialog(null, "Verifique los datos\nError: " + b.toString());

		                repaint();
		            }
		            

		            	
		            

		        }

		        modelo.setRowCount(0);
		        mostrar();
		        
		        limpiar();
				
				
			}
		});
		btnguardar.setBounds(25, 390, 204, 36);
		panel.add(btnguardar);
		
		btnEliminar = new JButton(len[11]);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent d) {
				
				try {
		            Conexion a = new Conexion();

		            a.sentence("DELETE FROM `inventario` WHERE `Code`='" + codigo.getText().toUpperCase() + "';");

		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null, "Verifique los datos\nError: " + e.toString());
		            repaint();
		        }

		        modelo.setRowCount(0);
		        mostrar();

				
			}
		});
		btnEliminar.setBounds(25, 437, 204, 36);
		panel.add(btnEliminar);
		
		JLabel candado = new JLabel("");
		candado.setBounds(843, 11, 35, 35);		
		ImageIcon can = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoCerrado.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_AREA_AVERAGING));
        candado.setIcon(can);		
		panel.add(candado);
		
		
		editar = new JLabel("");
		editar.setBounds(452, 11, 35, 35);
		ImageIcon ed = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/EditarLight.png").getImage().getScaledInstance(editar.getWidth(), editar.getHeight(), Image.SCALE_AREA_AVERAGING));
        editar.setIcon(ed);

		
		editar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				 access = (!access);
			        

			        if (access == true) {

			            regi = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoAbierto.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_AREA_AVERAGING));
			            candado.setIcon(regi);
			            btnguardar.setText(len[13]);
			        }

			        if (access == false) {

			            regi = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoCerrado.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_AREA_AVERAGING));
			            candado.setIcon(regi);
			            btnguardar.setText(len[10]);

			        }

			        repaint();
						
			}
		});

		panel.add(editar);
		
		txtidioma = new JLabel(len[12]);
		txtidioma.setBounds(10, 484, 42, 21);
		panel.add(txtidioma);
		
		JPanel propiedades = new JPanel();
		propiedades.setBounds(25, 230, 204, 149);
		panel.add(propiedades);
		propiedades.setLayout(null);
		
		txtsold = new JLabel(len[14]);
		txtsold.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsold.setBounds(10, 11, 58, 24);
		propiedades.add(txtsold);
		
		sold = new JLabel("");
		sold.setBounds(69, 11, 58, 24);
		propiedades.add(sold);
		
		mayor= mayor();menor=menor();
		String may= mayor.getCode()+" - "+mayor.getProduct();
		String men= menor.getCode()+" - "+menor.getProduct();
		
		txtpopular = new JLabel("Mas popular: ");
		txtpopular.setFont(new Font("Agency FB", Font.BOLD, 13));
		txtpopular.setBounds(10, 48, 117, 14);
		propiedades.add(txtpopular);
		
		popular = new JLabel(may);
		popular.setFont(new Font("Agency FB", Font.BOLD, 13));
		popular.setBounds(10, 73, 172, 14);
		propiedades.add(popular);
		
		txtunpopular = new JLabel("Menos popular: ");
		txtunpopular.setFont(new Font("Agency FB", Font.BOLD, 13));
		txtunpopular.setBounds(10, 98, 117, 14);
		propiedades.add(txtunpopular);
		
		unpopular = new JLabel(men);
		unpopular.setFont(new Font("Agency FB", Font.BOLD, 13));
		unpopular.setBounds(10, 123, 172, 14);
		propiedades.add(unpopular);
		
		imprimir = cp.Button("", cp.setBounds(143, 11, 53, 53), "pdfLight", 53, 53, JButton.CENTER, JButton.RIGHT, JButton.LEFT, true, true);	
		imprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				reporte();
				
			}
			
		});
		
		propiedades.add(imprimir);
		
		
		boolean aspect=false;
		
		if(moder==0) {aspect=true;}
		if(lengu==1) { ing(); setTitle(len[19]);}
		else {	esp(); setTitle(len[19]); }
			
		modifymode(aspect);
		modifylen();
		
		
	}
	
	public void reporte() {
		
		Path Downloads = Paths.get(System.getProperty("user.home"), "Downloads");
		String url = Downloads.toString()+"\\Report.pdf";
		
		try (PdfWriter pdfw = new PdfWriter(new File(url))){
			
			PdfDocument pdfdoc = new PdfDocument(pdfw);
			pdfdoc.setDefaultPageSize(PageSize.LETTER);
			Document doc = new Document(pdfdoc);
			
			com.itextpdf.layout.element.Image Logo = new com.itextpdf.layout.element.Image(ImageDataFactory.create("./src/ResourcePackCaja/Inventario.png"));
            Logo.setAutoScale(false);
			Logo.scaleToFit(180, 180);
            Logo.setHorizontalAlignment(HorizontalAlignment.CENTER);			
			
			Paragraph Title = new Paragraph(len[19]);
			Title.setFontSize(20);
            Title.setBold();
            Title.setTextAlignment(TextAlignment.CENTER);
            Title.setVerticalAlignment(VerticalAlignment.MIDDLE);
			
            Conexion a = new Conexion();

            Table tableta = new Table(7);
			tableta.setWidth(UnitValue.createPercentValue(100));
			tableta.addCell(new Paragraph(len[2]));
			tableta.addCell(new Paragraph(len[3]));
			tableta.addCell(new Paragraph(len[4]));
			tableta.addCell(new Paragraph(len[5]));
			tableta.addCell(new Paragraph(len[6]));
			tableta.addCell(new Paragraph(len[7]));
			tableta.addCell(new Paragraph(len[14]));
			
			for (int j = 0; j <= 6; j++) {
				
				tableta.getCell(0, j).setBorder(null).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);
			}
			
			
            try {

                ResultSet res = a.consulta("SELECT * FROM Inventario");
                int i=0;
                
                while (res.next()) {

                    Archivo p = muestra(res.getString("Code"));
                    
                    tableta.addCell(new Paragraph(p.getCode()));
                    tableta.addCell(new Paragraph(p.getProduct()));
                    tableta.addCell(new Paragraph(p.getPrice()+"$"));
                    tableta.addCell(new Paragraph(p.getAmount()+""));
                    tableta.addCell(new Paragraph(p.getUnid()));
                    tableta.addCell(new Paragraph(p.getBrand()));
                    tableta.addCell(new Paragraph(p.getSold()+""));
                    
                    for (int j = 0; j <= 6; j++) {
						
						tableta.getCell(i+1, j).setBorder(null).setFontSize(13).setTextAlignment(TextAlignment.CENTER);
					}

    				 i++;
                }
                
            } catch (Exception e) {
            }
            
            
            Paragraph may= new Paragraph("\n"+len[17]+mayor.getCode()+": "+mayor.getProduct());
			Title.setFontSize(20);
            Title.setBold();
            Title.setTextAlignment(TextAlignment.CENTER);
            Title.setVerticalAlignment(VerticalAlignment.MIDDLE);
            
            Paragraph men= new Paragraph(len[18]+menor.getCode()+": "+menor.getProduct());
			Title.setFontSize(20);
            Title.setBold();
            Title.setTextAlignment(TextAlignment.CENTER);
            Title.setVerticalAlignment(VerticalAlignment.MIDDLE);
            
            doc.add(Title);
            doc.add(Logo);
			doc.add(tableta);
			doc.add(may);
			doc.add(men);
			
			doc.close();
			pdfdoc.close();
			
			Desktop.getDesktop().open(new File(url));
			
		}catch (IOException e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	

	public void esp() {
		String leno[]= {"Informacion de producto:","Productos Disponibles:","Codigo:", "Producto:", "Precio:", "Existencias:","Unidad:","Marca:","Descripcion:","Seleccionar Imagen","Guardar","Eliminar","(ESP)","Editar","Vendido:","Mas popular:","Menos popular:","Producto mas vendido: ","Producto menos vendido: ","Inventario"};
			len=leno;
	}
	
	public void ing() {
		String leno[]= {"Product information:","Available products:","Code:", "Product:", "Price:", "Stock:","Unit:","Brand:","Description:","Select Image","Save","Delete","(ENG)","Edit","Sold:","Most popular:","Least popular:","Best selling product: ","Least selling product: ","Inventory"};
			len=leno;
	}

	
	public void modifylen() {

		txtInformacion.setText(len[0]);		
		txttabla.setText(len[1]);
		txtcodigo.setText(len[2]);
		txtproductos.setText(len[3]);
		txtprecio.setText(len[4]);
		txtexistencia.setText(len[5]);
		txtunidad.setText(len[6]);
		txtmarca.setText(len[7]);
		txtdescripcion.setText(len[8]);
		btnimage.setText(len[9]);
		btnguardar.setText(len[10]);
		btnEliminar.setText(len[11]);
		txtidioma.setText(len[12]);
		if(access==true) {btnguardar.setText(len[13]);}
		txtsold.setText(len[14]);
		txtpopular.setText(len[15]);
		txtunpopular.setText(len[16]);
		
		String []titulos={len[2], len[3], len[4], len[5], len[7]};
		modelo = new DefaultTableModel(null,titulos);
		table.setModel(modelo);
		mostrar();	
	}
	
	public void modifymode(boolean b) {
		
		Color colorin= new Color(20, 35, 54),fondo=new Color(238, 248, 254);
		String md="Dark",mdo="Light";
		
		if(b==false) {
			colorin=new Color(238, 248, 254);
			fondo=new Color(20, 35, 54);
			md="Light";
			mdo="Dark";
		}
		
		panel.setBackground(fondo);
		txtInformacion.setForeground(colorin);	
		txttabla.setForeground(colorin);
		txtcodigo.setForeground(colorin);
		txtproductos.setForeground(colorin);
		txtprecio.setForeground(colorin);
		txtexistencia.setForeground(colorin);
		txtunidad.setForeground(colorin);
		txtmarca.setForeground(colorin);
		txtdescripcion.setForeground(colorin);
		txtidioma.setForeground(colorin);	
		
		editar.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Editar"+mdo+".png").getImage().getScaledInstance(editar.getWidth(), editar.getHeight(), Image.SCALE_DEFAULT)));
		
		codigo.setForeground(colorin);
		precio.setForeground(colorin);
		producto.setForeground(colorin);
		existencias.setForeground(colorin);
		marca.setForeground(colorin);
		
	}
	
	
	public void limpiar() {
	
		codigo.setText("");
        producto.setText("");
        precio.setText("");
        existencias.setText("");        
        marca.setText("");        
        descripcion.setText("");        
        sold.setText("");
        
        ImageIcon ima = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/image-not-found.png").getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
         foto.setIcon(ima);
         foto.repaint();
		
		
	}
	

	
	public void mostrar() {

        Conexion a = new Conexion();

        try {

            ResultSet res = a.consulta("SELECT * FROM Inventario");

            while (res.next()) {

                Object[] products = {res.getString("Code"), res.getString("Product"), res.getString("Price"), res.getString("Amount"), res.getString("Brand")};
                modelo.addRow(products);

            }

        } catch (Exception e) {
        }

    }
	
	
	 public Archivo muestra(String cod){
	        
         
        Archivo prod=null;
        
        try {
            Conexion a = new Conexion();
            ResultSet res=a.consulta( "SELECT * FROM Inventario WHERE `Code`='"+cod+"'");

                prod=new Archivo(res.getString("Code"),res.getString("Product"),res.getString("Brand"),res.getString("Description"),res.getFloat("Amount"), res.getFloat("Sold"),res.getFloat("Price"),res.getString("Unid"),res.getString("Image"));
               
                
            res.close();
                
        } catch (Exception e) {
        }
     
        
        
        return prod;
    }
	
	 
	 
	 public File actionimage() {
			
			
			File archivo=new File("./src/ResourcePackCaja/image-not-found.png");
			
			try {
				
					try {
			            
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			               
			           } catch (Exception e) {}
			               
			           JFileChooser jf=new JFileChooser();
			           jf.showOpenDialog(this);
			           
			           archivo= jf.getSelectedFile();
			          
			           String arc= Mecanics.getExtension(archivo);
			           
			           if(arc.equalsIgnoreCase("png")==false && arc.equalsIgnoreCase("jpg")==false) {
			        	   JOptionPane.showMessageDialog(null, "ERROR, Seleccione un archivo con los formatos permitidos. \n\n->PNG\n->JPEG\n");
			        	   archivo=new File("./src/ResourcePackCaja/image-not-found.png");
			           }
		           
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Accion Cancelada");
			}
	           
	           return archivo;
	    }       
	 
	 
	 public Archivo mayor() {
		 
		 Archivo prod=null;
		 
		 Conexion a = new Conexion();
		 
		 try {

	            ResultSet res = a.consulta("SELECT * FROM Inventario");

	            prod=muestra(res.getString("Code"));
	            
	            while (res.next()) {
	            	
	              Archivo aux=muestra(res.getString("Code"));
	               if(aux.getSold()>prod.getSold()) {
	            	   prod=aux;
	               }

	            }

	        } catch (Exception e) {
	        }
	     
		 return prod;
	 }
	 
	 
	public Archivo menor() {
			 
			 Archivo prod=null;
			 
			 Conexion a = new Conexion();
			 
			 try {
	
		            ResultSet res = a.consulta("SELECT * FROM Inventario");
	
		            prod=muestra(res.getString("Code"));
		            
		            while (res.next()) {
		            	
		              Archivo aux=muestra(res.getString("Code"));
		               if(aux.getSold()<prod.getSold()) {
		            	   prod=aux;
		               }
	
		            }
	
		        } catch (Exception e) {
		        	
		        }
		     
			 
			 return prod;
		 }
	 
	}
