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
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Main.Login;
import Main.Runner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Inventario extends JFrame {



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventario frame = new Inventario();
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
	
	private JTable table;	
	private DefaultTableModel modelo;
    
	
	private ImageIcon regi;
    static String [] len={"Informacion de producto:","Productos Disponibles:","Codigo:", "Producto:", "Precio:", "Existencias:","Unidad:","Marca:","Descripcion:","Seleccionar Imagen","Guardar","Eliminar","(ESP)"};
	private JComboBox<String> unidad;
	private JLabel foto;
	private JTextArea descripcion;;
	static boolean access=false, idioma=true, modi=false;
	private JButton btnEliminar, btnimage, btnguardar;
	private JLabel lenguaje;
	private JLabel mode;
	private JLabel volver;
	private JLabel editar;

	private final Components cp = new Components("./src/ResourcePackCaja/", Color.WHITE, 0);
	
	public Inventario() {
		
		
		setResizable(false);
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(49, 25, 903, 504);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel fondo=new JLabel("");
		fondo.setBounds(0, 0, 1013, 609);
		ImageIcon imgfondo = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Fondo.JPG").getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_DEFAULT));
        fondo.setIcon(imgfondo);
        contentPane.add(fondo);
		
		foto = new JLabel("");
		foto.setBounds(25, 98, 204, 176);
		ImageIcon pfoto = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/image-not-found.png").getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
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
			           
			           ruta=miu.getImage();
			           
			           ImageIcon ima = new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
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
		table.setBackground(new Color(192, 192, 192));
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
		
		codigo = cp.TextPanel("", cp.setBounds(321, 65, 86, 20), SwingConstants.LEFT, new Font("Microsoft JhengHei UI", Font.BOLD, 12), Color.BLUE, Color.BLUE, true, true);
		/*codigo = new JTextField();
		codigo.setBounds(321, 65, 86, 20);
		codigo.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
		codigo.setBackground(null);//*/
		panel.add(codigo);
		codigo.setColumns(10);
		
		txtproductos = new JLabel(len[3]);
		txtproductos.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtproductos.setBounds(262, 98, 72, 26);
		panel.add(txtproductos);
		
		producto = new JTextField();
		producto.setBounds(344, 102, 144, 20);
		producto.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
		producto.setBackground(null);
		panel.add(producto);
		producto.setColumns(10);
		
		txtprecio = new JLabel(len[4]);
		txtprecio.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtprecio.setBounds(262, 133, 72, 26);
		panel.add(txtprecio);
		
		precio = new JTextField();
		precio.setColumns(10);
		precio.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
		precio.setBackground(null);
		precio.setBounds(344, 137, 144, 20);
		panel.add(precio);
		
		txtexistencia = new JLabel(len[5]);
		txtexistencia.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtexistencia.setBounds(262, 170, 72, 26);
		panel.add(txtexistencia);
		
		existencias = new JTextField();
		existencias.setColumns(10);
		existencias.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
		existencias.setBackground(null);
		existencias.setBounds(344, 174, 144, 20);
		panel.add(existencias);
		
		txtmarca = new JLabel(len[7]);
		txtmarca.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtmarca.setBounds(262, 242, 72, 26);
		panel.add(txtmarca);
		
		marca = new JTextField();
		marca.setColumns(10);
		marca.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
		marca.setBackground(null);
		marca.setBounds(344, 246, 144, 20);
		panel.add(marca);
		
		txtunidad = new JLabel(len[6]);
		txtunidad.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtunidad.setBounds(262, 207, 72, 26);
		panel.add(txtunidad);
		
		DefaultComboBoxModel<String> modl = new DefaultComboBoxModel<>(new String[] {"u", "Kg", "g", "L"});
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
		               System.out.println(ruta);
		               iArchivo("./src/");
		               
		            ImageIcon ima = new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
		               foto.setIcon(ima);
		               foto.repaint();
		           }
				
			}
		});
		btnimage.setBounds(25, 283, 204, 23);
		panel.add(btnimage);
		
		btnguardar = new JButton(len[10]);
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		

		        if (access == false) {
		        	
		       	Conexion a = new Conexion();
		        			
		            try {

		                Archivo prod = new Archivo(codigo.getText(), producto.getText(), marca.getText(), descripcion.getText(), Float.parseFloat(existencias.getText()), Float.parseFloat(precio.getText()), unidad.getSelectedItem().toString(), ruta);

		                a.sentence("INSERT INTO `Inventario` (`Code`, `Product`, `Brand`, `Description`, `Amount`,`Price`,`Unid`,`Image`) "
		                        + "VALUES ('" + prod.getCode().toUpperCase() + "', '" + prod.getProduct() + "', '" + prod.getBrand() + "', '" + prod.getDescription() + "', '" + prod.getAmount() + "', '" + prod.getPrice() + "', '" + prod.getUnid() + "', '" + prod.getImage() + "');");

		            } catch (Exception b) {
		                JOptionPane.showMessageDialog(null, "Verifique los datos\nError: " + b.toString());
		                repaint();
		            }
		        }

		        if (access == true) {
		        		
		           	Conexion a = new Conexion();
		        	
		            try {
		                a.sentence("UPDATE `Inventario` SET  `Product`='"+producto.getText()+"', `Brand`='"+marca.getText()+"', `Description`='"+descripcion.getText()+"', `Amount`='"+Float.parseFloat(existencias.getText())+"',`Price`='"+Float.parseFloat(precio.getText())+"',`Unid`='"+unidad.getSelectedItem().toString()+"',`Image`='"+ruta+"'  WHERE `Code`='" + codigo.getText().toUpperCase() + "';");

		            } catch (Exception b) {
		                JOptionPane.showMessageDialog(null, "Verifique los datos\nError: " + b.toString());
		                repaint();
		            }

		        }

		        modelo.setRowCount(0);
		        mostrar();


				
				
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
		candado.setBounds(843, 0, 50, 50);		
		ImageIcon can = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoCerrado.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_DEFAULT));
        candado.setIcon(can);		
		panel.add(candado);
		
		
		editar = new JLabel("");
		editar.setBounds(452, 11, 50, 50);
		ImageIcon ed = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/EditarLight.png").getImage().getScaledInstance(editar.getWidth(), editar.getHeight(), Image.SCALE_DEFAULT));
        editar.setIcon(ed);

		
		editar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				 access = (!access);
			        

			        if (access == true) {

			            regi = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoAbierto.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_DEFAULT));
			            candado.setIcon(regi);
			            btnguardar.setText("Editar");
			        }

			        if (access == false) {

			            regi = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoCerrado.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_DEFAULT));
			            candado.setIcon(regi);
			            btnguardar.setText("Guardar");

			        }

			        repaint();
						
			}
		});

		panel.add(editar);
		
		volver = new JLabel("");
		volver.setBounds(25, 21, 50, 50);
		ImageIcon imgv = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CerrarLight.png").getImage().getScaledInstance(volver.getWidth(), volver.getHeight(), Image.SCALE_DEFAULT));
        volver.setIcon(imgv);
		volver.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				    

			    
						
			}
		});
		panel.add(volver);
		
		
		
		mode = new JLabel("");
		mode.setBounds(80, 21, 50, 50);
		ImageIcon imgmode = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Dark.png").getImage().getScaledInstance(mode.getWidth(), mode.getHeight(), Image.SCALE_DEFAULT));
        mode.setIcon(imgmode);
		mode.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				 modi=!modi;
				 
				 modifymode(modi);
				
				
						
			}
		});
		panel.add(mode);
		
		lenguaje = new JLabel("");
		lenguaje.setBounds(137, 21, 50, 50);
		ImageIcon imglen = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/idiomaLight.png").getImage().getScaledInstance(lenguaje.getWidth(), lenguaje.getHeight(), Image.SCALE_DEFAULT));
        lenguaje.setIcon(imglen);
		lenguaje.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				idioma=!(idioma);
				
				if(idioma==true) {
					String leno[]= {"Informacion de producto:","Productos Disponibles:","Codigo:", "Producto:", "Precio:", "Existencias:","Unidad:","Marca:","Descripcion:","Seleccionar Imagen","Guardar","Eliminar","(ESP)"};
					len=leno;
				}
				
				if(idioma==false) {
					String leno[]= {"Product information:","Available products:","Code:", "Product:", "Price:", "Stock:","Unit:","Brand:","Description:","Select Image","Save","Delete","(ENG)"};
					len=leno;
				}
				
				
				modifylen();
				
		        repaint();
				
				
			}
		});
		panel.add(lenguaje);
		
		txtidioma = new JLabel(len[12]);
		txtidioma.setBounds(197, 35, 42, 21);
		panel.add(txtidioma);
		

		
		
	}
	
	protected void iArchivo(String ruta)  {


		
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
		String []titulos={len[2], len[3], len[4], len[5], len[7]};
		modelo = new DefaultTableModel(null,titulos);
		table.setModel(modelo);
		mostrar();	
	}
	
	public void modifymode(boolean b) {
		
		Color colorin=Color.BLACK,fondo=Color.WHITE;
		String md="Dark",mdo="Light";
		
		if(b==false) {
			colorin=Color.WHITE;
			fondo=Color.DARK_GRAY;
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
		mode.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/"+md+".png").getImage().getScaledInstance(mode.getWidth(), mode.getHeight(), Image.SCALE_DEFAULT)));
		lenguaje.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/idioma"+mdo+".png").getImage().getScaledInstance(lenguaje.getWidth(), lenguaje.getHeight(), Image.SCALE_DEFAULT)));
		editar.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Editar"+mdo+".png").getImage().getScaledInstance(lenguaje.getWidth(), lenguaje.getHeight(), Image.SCALE_DEFAULT)));
		volver.setIcon(new ImageIcon(new ImageIcon("./src/ResourcePackCaja/cerrar"+mdo+".png").getImage().getScaledInstance(lenguaje.getWidth(), lenguaje.getHeight(), Image.SCALE_DEFAULT)));
		
		
		codigo.setForeground(colorin);
		precio.setForeground(colorin);
		producto.setForeground(colorin);
		existencias.setForeground(colorin);
		marca.setForeground(colorin);
		
		
		
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
            ResultSet res=a.consulta( "SELECT * FROM inventario WHERE `Code`='"+cod+"'");

                prod=new Archivo(res.getString("Code"),res.getString("Product"),res.getString("Brand"),res.getString("Description"),Float.parseFloat(res.getString("Amount")),Float.parseFloat(res.getString("Price")),res.getString("Unid"),res.getString("Image"));

            res.close();
                
        } catch (Exception e) {
        }
     
        
        
        return prod;
    }
	
	public File actionimage() {
		
		try {
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               
           } catch (Exception e) {}
               
           JFileChooser jf=new JFileChooser();
           jf.showOpenDialog(this);
           File archivo= jf.getSelectedFile();
           
           
           return archivo;
    }       
}
