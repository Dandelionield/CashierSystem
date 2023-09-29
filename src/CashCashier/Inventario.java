package CashCashier;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Inventario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel txttabla;
	private JLabel txtcodigo;
	private JTextField codigo;
	private JTextField producto;
	private JTextField precio;
	private JTextField existencias;
	private JTextField marca;
	private JScrollPane scrolldescripcion;
	private String ruta="";

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
	
	private DefaultTableModel modelo;
    private ImageIcon regi;
    private String [] len={"Informacion de producto:","Productos Disponibles:","Codigo:", "Producto:", "Precio:", "Existencias:","Unidad:","Marca:","Descripcion:","Seleccionar Imagen","Guardar","Eliminar"};
	private JComboBox<String> unidad;
	private JLabel foto;
	private JTextArea descripcion;;
   
	
	public Inventario() {
		setResizable(false);
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 903, 504);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		foto = new JLabel("");
		foto.setBounds(270, 62, 204, 176);
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

		
		JLabel txtInformacion = new JLabel("Informacion de Producto:");
		txtInformacion.setBounds(10, 22, 191, 26);
		txtInformacion.setFont(new java.awt.Font("Arial", 3, 14));
		panel.add(txtInformacion);
		
		txttabla = new JLabel("Productos Disponibles:");
		txttabla.setBounds(512, 25, 218, 21);
		txttabla.setFont(new java.awt.Font("Arial", 3, 14));
		panel.add(txttabla);
		
		txtcodigo = new JLabel("Codigo:");
		txtcodigo.setBounds(10, 62, 60, 26);
		txtcodigo.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12));
		panel.add(txtcodigo);
		
		codigo = new JTextField();
		codigo.setBounds(69, 66, 86, 20);
		panel.add(codigo);
		codigo.setColumns(10);
		
		JLabel txtproducto = new JLabel("Producto:");
		txtproducto.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtproducto.setBounds(10, 99, 72, 26);
		panel.add(txtproducto);
		
		producto = new JTextField();
		producto.setBounds(92, 103, 144, 20);
		panel.add(producto);
		producto.setColumns(10);
		
		JLabel txtprecio = new JLabel("Precio:");
		txtprecio.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtprecio.setBounds(10, 134, 72, 26);
		panel.add(txtprecio);
		
		precio = new JTextField();
		precio.setColumns(10);
		precio.setBounds(92, 138, 144, 20);
		panel.add(precio);
		
		JLabel txtexistencias = new JLabel("Existencias:");
		txtexistencias.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtexistencias.setBounds(10, 171, 72, 26);
		panel.add(txtexistencias);
		
		existencias = new JTextField();
		existencias.setColumns(10);
		existencias.setBounds(92, 175, 144, 20);
		panel.add(existencias);
		
		JLabel txtmarca = new JLabel("Marca:");
		txtmarca.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtmarca.setBounds(10, 243, 72, 26);
		panel.add(txtmarca);
		
		marca = new JTextField();
		marca.setColumns(10);
		marca.setBounds(92, 247, 144, 20);
		panel.add(marca);
		
		JLabel txtunidad = new JLabel("Unidad:");
		txtunidad.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtunidad.setBounds(10, 208, 72, 26);
		panel.add(txtunidad);
		
		DefaultComboBoxModel<String> modl = new DefaultComboBoxModel<>(new String[] {"u", "Kg", "g", "L"});
		unidad = new JComboBox<>(modl);
		unidad.setBounds(92, 214, 60, 22);
		panel.add(unidad);
		
		JLabel txtdescripcion = new JLabel("Descripcion:");
		txtdescripcion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		txtdescripcion.setBounds(10, 280, 72, 26);
		panel.add(txtdescripcion);
		
		
		descripcion = new JTextArea();
		descripcion.setBounds(10, 317, 228, 176);
		descripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(descripcion);
		
		
		JButton btnimage = new JButton("añadir imagen");
		btnimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent d) {
				
		       File archivo = actionimage();
		        
		        
		        if(archivo !=null){
		               
		               ruta= archivo.getAbsolutePath();
		               System.out.println(ruta);
		            //   iArchivo(ruta,"C:\\ResourcePackCaja\\Resourses inventory");
		               
		            ImageIcon ima = new ImageIcon(new ImageIcon(ruta).getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
		               foto.setIcon(ima);
		               foto.repaint();
		           }
				
			}
		});
		btnimage.setBounds(270, 246, 204, 23);
		panel.add(btnimage);
		
		JButton btnguardar = new JButton("Guardar");
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
		btnguardar.setBounds(270, 394, 204, 36);
		panel.add(btnguardar);
		
		JButton btnEliminar = new JButton("Eliminar");
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
		btnEliminar.setBounds(270, 441, 204, 36);
		panel.add(btnEliminar);
		
		JLabel candado = new JLabel("");
		candado.setBounds(424, 317, 50, 50);		
		ImageIcon can = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoCerrado.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_DEFAULT));
        candado.setIcon(can);		
		panel.add(candado);
		
		
		JLabel editar = new JLabel("");
		editar.setBounds(364, 317, 50, 50);
		ImageIcon ed = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/EditarLight.png").getImage().getScaledInstance(editar.getWidth(), editar.getHeight(), Image.SCALE_DEFAULT));
        editar.setIcon(ed);

		
		editar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				 access = (!access);
			        

			        if (access == true) {

			            regi = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoAbierto.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_DEFAULT));
			            candado.setIcon(regi);
			        }

			        if (access == false) {

			            regi = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/CandadoCerrado.png").getImage().getScaledInstance(candado.getWidth(), candado.getHeight(), Image.SCALE_DEFAULT));
			            candado.setIcon(regi);

			        }

			        repaint();
						
			}
		});

		panel.add(editar);
		

		
		
	}
	
	
	
	private boolean access=false;
	

	
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
