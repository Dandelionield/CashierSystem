package Employees;

import static java.nio.file.StandardCopyOption.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Desktop;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import Main.Runner;
import Main.Mecanics;
import Main.Menu;
import Objects.Conexion;
import Objects.Trabajador;

public class RegistroEmpleados {
    private static String url = "./src/ResourcePackCaja/";
    private static Boolean edit = true;
    private static JFrame screen = new JFrame();
    private static JPanel mainPanel = new JPanel();
    private static JPanel userPanel = new JPanel();

    private int mode = Mecanics.getMode();
    private int language = Mecanics.getLanguage();
    private String User = "";
    private String Name = "";

    private static char genero = '.';
    private static int permisos = -1;
    private static int selection = -1;
	private static String Selected = null;

    private static Color color1, color2, colorField;
    private static String[] CedulaI = { "Cedula", "Cedula" };
    private static String[] NombreI = { "Nombre", "Name" };
    private static String[] ApellidoI = { "Apellido", "Last name" };
    private static String[] ContactoI = { "Contacto", "Contact" };
    private static String[] EmailI = { "Email", "Email" };
    private static String[] DireccionI = { "Direccion", "Address" };
    private static String[] EdadI = { "Edad", "Age" };
    private static String[] GeneroI = { "Genero", "Gender" };
    private static String[][] ElegirGI = { { "Femenino", "Female" }, { "Masculino", "Male" },
            { "No decirlo", "Not say it" } };
    private static String[] PermisosI = { "Permisos", "Permit" };
    private static String[][] ElegirPI = { { "Empleado", "Employee" }, { "Administrador", "Administrator" } };
    private static String[] CodeI = { "Codigo", "Code" };
    private static String[] PedirI = { "Ingrese su ", "Enter your " };

    private static JLabel jlCedula = new JLabel();
    private static JLabel jlNombre = new JLabel();
    private static JLabel jlApellido = new JLabel();
    private static JLabel jlContacto = new JLabel();
    private static JLabel jlEmail = new JLabel();
    private static JLabel jlDireccion = new JLabel();
    private static JLabel jlEdad = new JLabel();
    private static JLabel jlGenero = new JLabel();
    private static JLabel jlPermisos = new JLabel();

    private static JTextField jtCedula = new JTextField();
    private static JTextField jtNombre = new JTextField();
    private static JTextField jtApellido = new JTextField();
    private static JTextField jtContacto = new JTextField();
    private static JTextField jtEmail = new JTextField();
    private static JTextField jtDireccion = new JTextField();

    private static JLabel jlUserCedula = new JLabel();
    private static JLabel jlUserNombre = new JLabel("", SwingConstants.CENTER);
    private static JLabel jlUserContacto = new JLabel();
    private static JLabel jlUserEmail = new JLabel();
    private static JLabel jlUserDireccion = new JLabel();
    private static JLabel jlUserEdad = new JLabel();
    private static JLabel jlUserGenero = new JLabel();
    private static JLabel jlUserPermisos = new JLabel();
    private static JLabel jlUserCode = new JLabel();

    private static Image imgExit;
    private static Image imgEdit;
    private static Image imgAccept;
    private static Image imgCancel;
    private static Image imgDelete;
    private static Image imgUser;
    private static Image imgPdf;
    private static Image imgUnlock = new ImageIcon(url + "CandadoAbierto.png").getImage();
    private static Image imgLock = new ImageIcon(url + "CandadoCerrado.png").getImage();

    private static JLabel jlExit = new JLabel();
    private static JLabel jlEdit = new JLabel();
    private static JLabel jlAccept = new JLabel();
    private static JLabel jlCancel = new JLabel();
    private static JLabel jlDelete = new JLabel();
    private static JLabel jlPdf = new JLabel();
    private static JLabel jlImg = new JLabel();
    private static JLabel jlLock = new JLabel(new ImageIcon(imgLock.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
    private static JLabel jlImgSelect = new JLabel(new ImageIcon(url + "vacio.png"));
    private static JLabel fondo = new JLabel(new ImageIcon(
            new ImageIcon(url + "Fondo.jpg").getImage().getScaledInstance(1040, 630, Image.SCALE_SMOOTH)));

    private static JRadioButton g1 = new JRadioButton(), g2 = new JRadioButton(), g3 = new JRadioButton();
    private static JRadioButton p1 = new JRadioButton(), p2 = new JRadioButton();
    private static JSpinner jsEdad = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1));
    private static ButtonGroup bgGenero = new ButtonGroup();
    private static ButtonGroup bgPermisos = new ButtonGroup();
    private static DefaultTableModel modelo;
    private static JTable jtaEmpleados = new JTable();
    private static JScrollPane jscEmpleados = new JScrollPane();
    private static File imagenUser = new File(url + "image-not-found.png");

    private static DocumentListener user;
    private static ChangeListener edadListener;
    private static ActionListener generoListener;
    private static ActionListener permisoListener;
    private static MouseAdapter buttongroup;
    private static MouseAdapter deleteListener;
    private static MouseAdapter userCodeListener;
    private static MouseAdapter acceptListener;
    private static MouseAdapter cancelListener;
    private static MouseAdapter editListener;
    private static MouseAdapter pdfListener;

    private static Conexion basedata = new Conexion();

    public RegistroEmpleados(String user, String name) {//Employe
        User = user;
        Name = name;

        main();
        Recargar();

        screen.setTitle((language == 0 ? "Empleados" : "Employees") + " : " + Name);
    }

    private void main() {
        /* DECLARACION DE LOS OBJETOS */
        ChangeMode();
        ChangeLanguage();

        jtaEmpleados.setModel(modelo);
        jscEmpleados.setViewportView(jtaEmpleados);

        // Border
        MatteBorder border = new MatteBorder(0, 0, 2, 0, Color.GRAY);

        /* CONFIGURACION */

        // Labels
        jlCedula.setBounds(16, 62, 139, 21);
        jlNombre.setBounds(16, 92, 148, 21);
        jlContacto.setBounds(16, 152, 157, 21);
        jlEmail.setBounds(16, 182, 129, 21);
        jlDireccion.setBounds(16, 212, 159, 21);
        jlEdad.setBounds(455, 62, 126, 21);
        jlGenero.setBounds(455, 92, 70, 21);
        jlPermisos.setBounds(455, 182, 90, 21);

        Font jlFuente = new Font("Clarendon Blk BT", Font.PLAIN, 16);
        jlCedula.setFont(jlFuente);
        jlNombre.setFont(jlFuente);
        jlApellido.setFont(jlFuente);
        jlContacto.setFont(jlFuente);
        jlEmail.setFont(jlFuente);
        jlDireccion.setFont(jlFuente);
        jlEdad.setFont(jlFuente);
        jlGenero.setFont(jlFuente);
        jlPermisos.setFont(jlFuente);

        // Textfields
        jtCedula.setBounds(159, 62, 280, 20);
        jtEmail.setBounds(146, 182, 293, 20);
        jscEmpleados.setBounds(16, 300, 604, 190);

        jtCedula.setBorder(border);
        jtNombre.setBorder(border);
        jtApellido.setBorder(border);
        jtContacto.setBorder(border);
        jtEmail.setBorder(border);
        jtDireccion.setBorder(border);

        // JLabels User
        jlUserNombre.setBounds(10, 220, 285, 25);
        jlUserNombre.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
        jlUserCedula.setBounds(10, 270, 300, 25);
        jlUserContacto.setBounds(10, 300, 300, 25);
        jlUserEmail.setBounds(10, 330, 300, 25);
        jlUserDireccion.setBounds(10, 360, 300, 25);
        jlUserEdad.setBounds(10, 390, 200, 25);
        jlUserGenero.setBounds(10, 420, 200, 25);
        jlUserPermisos.setBounds(10, 450, 300, 25);
        jlUserCode.setBounds(10, 478, 300, 25);

        Font jlFuente2 = new Font("Clarendon Blk BT", Font.PLAIN, 20);
        jlUserCedula.setFont(jlFuente2);
        jlUserContacto.setFont(jlFuente2);
        jlUserEmail.setFont(jlFuente2);
        jlUserDireccion.setFont(jlFuente2);
        jlUserEdad.setFont(jlFuente2);
        jlUserGenero.setFont(jlFuente2);
        jlUserPermisos.setFont(jlFuente2);
        jlUserCode.setFont(jlFuente2);

        jlUserNombre.setForeground(Color.GRAY);

        // JRadioButton
        g1.setBounds(520, 92, 90, 20);
        g2.setBounds(520, 112, 90, 20);
        g3.setBounds(520, 132, 90, 20);

        Font jlFuente3 = new Font("Clarendon Blk BT", Font.PLAIN, 12);
        g1.setFont(jlFuente3);
        g2.setFont(jlFuente3);
        g3.setFont(jlFuente3);
        p1.setFont(jlFuente3);
        p2.setFont(jlFuente3);

        // User
        jlExit.setBounds(5, 5, 40, 40);
        jlCancel.setBounds(581, 250, 40, 40);
        jlAccept.setBounds(532, 250, 40, 40);
        jlDelete.setBounds(482, 250, 40, 40);
        jlEdit.setBounds(16, 250, 40, 40);
        jlLock.setBounds(62, 250, 40, 40);
        jlPdf.setBounds(582, 5, 40, 40);
        jlImg.setBounds(55, 10, 200, 200);
        jlImgSelect.setBounds(55, 10, 200, 200);

        if (jlExit.getMouseListeners().length == 0) {
            Listener();
        }

        changeListener();


        /* RadioButton */
        bgGenero.add(g1);
        bgGenero.add(g2);
        bgGenero.add(g3);

        bgPermisos.add(p1);
        bgPermisos.add(p2);

        /* JPANEL PRINCIPAL */
        mainPanel.add(jlCedula);
        mainPanel.add(jtCedula);

        mainPanel.add(jlNombre);
        mainPanel.add(jtNombre);

        mainPanel.add(jlApellido);
        mainPanel.add(jtApellido);

        mainPanel.add(jlContacto);
        mainPanel.add(jtContacto);

        mainPanel.add(jlEmail);
        mainPanel.add(jtEmail);

        mainPanel.add(jlDireccion);
        mainPanel.add(jtDireccion);

        mainPanel.add(jlEdad);
        mainPanel.add(jsEdad);

        mainPanel.add(jlGenero);
        mainPanel.add(g1);
        mainPanel.add(g2);
        mainPanel.add(g3);

        mainPanel.add(jlPermisos);
        mainPanel.add(p1);
        mainPanel.add(p2);

        mainPanel.add(jscEmpleados);

        mainPanel.add(jlExit);
        mainPanel.add(jlAccept);
        mainPanel.add(jlCancel);
        mainPanel.add(jlDelete);
        mainPanel.add(jlEdit);
        mainPanel.add(jlLock);
        mainPanel.add(jlPdf);

        userPanel.add(jlImgSelect);
        userPanel.add(jlImg);
        userPanel.add(jlUserNombre);
        userPanel.add(jlUserCedula);
        userPanel.add(jlUserContacto);
        userPanel.add(jlUserEmail);
        userPanel.add(jlUserDireccion);
        userPanel.add(jlUserEdad);
        userPanel.add(jlUserGenero);
        userPanel.add(jlUserPermisos);
        userPanel.add(jlUserCode);

        userPanel.setLayout(null);
        userPanel.setBounds(640, 0, 320, 540);

        mainPanel.add(userPanel);
        mainPanel.setLayout(null);
        mainPanel.setBounds(30, 40, 960, 510);

        // JLabel fondo
        fondo.setBounds(0, 0, 1040, 630);

        /* JFRAME */
        screen.add(mainPanel);
        screen.add(fondo);
        screen.setResizable(false);
        screen.setLayout(null);
        screen.setSize(1040, 630);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
        screen.setIconImage(new ImageIcon(url + "Registro.png").getImage());
        screen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        if (screen.getWindowListeners().length == 0) {
            screen.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    boolean scp = Mecanics.Leave(screen);

                    if (scp == true) {
                        Runner lg = new Runner();
                        Runner.contentPane.removeAll();
                        Runner.Opciones = new Menu(lg, User, Name);
                        Runner.contentPane.add(Runner.Opciones, Integer.valueOf(0));

                        lg.setVisible(true);
                        screen.dispose();
                        screen.repaint();
                    }
                }
            });
        }
    }

    private void changeListener() {
        JPopupMenu PopUpMenu = new JPopupMenu();
        PopUpMenu.add(new JMenuItem(language == 0 ? "Copiar" : "Copy"));

        userCodeListener = new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                PopUpMenu.setVisible(true);
                PopUpMenu.show(jlUserCode, 0, jlUserCode.getHeight());
            }

            public void mouseExited(MouseEvent e) {
                PopUpMenu.setVisible(false);
            }

            public void mouseClicked(MouseEvent e) {
                StringSelection stringSelection = new StringSelection(CodeGenerate());
				
				Trabajador p = Trabajador.get(Selected);

                if (!edit && p!=null) {
                    stringSelection = new StringSelection(p.getCode());
                }

                /*if (!edit && selection != -1) {
                    stringSelection = new StringSelection(Mecanics.Employe.get(selection).getCode());
                }//*/

                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        };

        user = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            public void updateFieldState() {
                if (jtNombre.getText().isEmpty() && jtApellido.getText().isEmpty()) {
                    jlUserNombre.setText(NombreI[language] + ((language == 0) ? " y " : " and ") + ApellidoI[language]);
                    jlUserNombre.setForeground(Color.GRAY);
                } else {
                    jlUserNombre.setText(jtNombre.getText() + " " + jtApellido.getText());
                    jlUserNombre.setForeground(colorField);
                }

                jlUserCedula.setText(CedulaI[language] + ": " + ((jtCedula.getText().isEmpty()) ? "123456789" : jtCedula.getText()));
                jlUserContacto.setText(ContactoI[language] + ": " + ((jtContacto.getText().isEmpty()) ? "0123456789" : jtContacto.getText()));
                jlUserEmail.setText(EmailI[language] + ": " + ((jtEmail.getText().isEmpty()) ? "User@gmail.com" : jtEmail.getText()));
                jlUserDireccion.setText(DireccionI[language] + ": " + ((jtDireccion.getText().isEmpty()) ? "" : jtDireccion.getText()));
				
				Trabajador p = Trabajador.get(Selected);
				
				jlUserCode.setText(CodeI[language] + ": " + (p!=null && !edit ? p.getCode() : CodeGenerate()));
				
                //jlUserCode.setText(CodeI[language] + ": " + (selection != -1 && !edit ? Mecanics.Employe.get(selection).getCode() : CodeGenerate()));
            }
        };

        edadListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jlUserEdad.setText(EdadI[language] + ": " + jsEdad.getValue());
				
				Trabajador p = Trabajador.get(Selected);
				
				jlUserCode.setText(CodeI[language] + ": " + (p!=null ? p.getCode() : CodeGenerate()));
				
                //jlUserCode.setText(CodeI[language] + ": " + (selection != -1 ? Mecanics.Employe.get(selection).getCode() : CodeGenerate()));
            }
        };

        buttongroup = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
				
				Trabajador p = Trabajador.get(Selected);
				
                if (!edit && p!=null) {
                    if (p.getAdmin()) {
                        p2.setSelected(true);
                        jlUserPermisos.setText(PermisosI[language] + ": " + ElegirPI[1][language]);
                    } else {
                        p1.setSelected(true);
                        jlUserPermisos.setText(PermisosI[language] + ": " + ElegirPI[0][language]);
                    }

                    switch (genero) {
                        case 'F':
                            g1.setSelected(true);
                            jlUserGenero.setText(GeneroI[language] + ": " + ElegirGI[0][language]);
                            break;
                        case 'M':
                            g2.setSelected(true);
                            jlUserGenero.setText(GeneroI[language] + ": " + ElegirGI[1][language]);
                            break;
                        default:
                            g3.setSelected(true);
                            jlUserGenero.setText(GeneroI[language] + ": " + ElegirGI[2][language]);
                            break;
                    }

                    jlUserEdad.setText(EdadI[language] + ": " + jsEdad.getValue());
                }
            }
        };

        deleteListener = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!edit)
                    screen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!edit) {
                    jlDelete.setBounds(487, 255, 30, 30);
                    jlDelete.setIcon(new ImageIcon(imgDelete.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jlDelete.setBounds(482, 250, 40, 40);
                jlDelete.setIcon(new ImageIcon(imgDelete.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

                /*boolean comp = !Mecanics.Employe.get(selection).getCode().equals(User.trim());

                if (!edit && selection != -1 && comp) {
                    try {
                        basedata.sentence("DELETE FROM Trabajador WHERE ID = '" + Mecanics.Employe.get(selection).getID() + "';");
                        File destino = new File("./src/Employees/ImageEmployees/" + Mecanics.Employe.get(selection).getID() + ".png");

                        if (destino.exists())
                            Files.delete(destino.toPath());
                        LimpiarDatos();
                        JOptionPane.showMessageDialog(null, language == 0 ? "Empleado eliminado!" : "Employee removed!", language == 0 ? "Verificado!" : "Verified!", JOptionPane.PLAIN_MESSAGE);

                        selection = -1;
                        Recargar();
                    } catch (SQLException | IOException e1) {
                        e1.printStackTrace();
                    }
                }//*/
				
				Trabajador p = Trabajador.get(Selected);
				
				boolean comp = false;
				
				if (p!=null){
					
					comp = !p.getCode().equals(User.trim());
					
				}

                if (!edit && p!=null && comp) {
                    try {
                        basedata.sentence("DELETE FROM Trabajador WHERE ID = '" + p.getID() + "';");
                        File destino = new File("./src/Employees/ImageEmployees/" + p.getID() + ".png");

                        if (destino.exists())
                            Files.delete(destino.toPath());
                        LimpiarDatos();
                        JOptionPane.showMessageDialog(null, language == 0 ? "Empleado eliminado!" : "Employee removed!", language == 0 ? "Verificado!" : "Verified!", JOptionPane.PLAIN_MESSAGE);

                        Selected = null;
                        Recargar();
                    } catch (SQLException | IOException e1) {
                        e1.printStackTrace();
                    }
                }

                if (!comp) {
                    JOptionPane.showMessageDialog(null, language == 0 ? "No puedes eliminarte a ti mismo!" : "You can't eliminate yourself!", language == 0 ? "Advertencia!" : "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        generoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (g1.isSelected()) {
                    jlUserGenero.setText(GeneroI[language] + ": " + ElegirGI[0][language]);
                    genero = 'F';
                }
                if (g2.isSelected()) {
                    jlUserGenero.setText(GeneroI[language] + ": " + ElegirGI[1][language]);
                    genero = 'M';
                }
                if (g3.isSelected()) {
                    jlUserGenero.setText(GeneroI[language] + ": " + ElegirGI[2][language]);
                    genero = 'N';
                }
            }
        };

        permisoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p1.isSelected()) {
                    jlUserPermisos.setText(PermisosI[language] + ": " + ElegirPI[0][language]);
                    permisos = 0;
                }
                if (p2.isSelected()) {
                    jlUserPermisos.setText(PermisosI[language] + ": " + ElegirPI[1][language]);
                    permisos = 1;
                }
            }
        };

        cancelListener = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jlCancel.setBounds(586, 255, 30, 30);
                jlCancel.setIcon(new ImageIcon(imgCancel.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jlCancel.setBounds(581, 250, 40, 40);
                jlCancel.setIcon(new ImageIcon(imgCancel.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

                LimpiarDatos();
            }
        };

        acceptListener = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jlAccept.setBounds(537, 255, 30, 30);
                jlAccept.setIcon(new ImageIcon(imgAccept.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jlAccept.setBounds(532, 250, 40, 40);
                jlAccept.setIcon(new ImageIcon(imgAccept.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

                if (CheckTextfield()) {
                    try {
                        boolean comp = true;
						
						Trabajador[] q = Trabajador.get();
						
						if (q!=null){
						
							for (Trabajador empleados : q) {
								if (!edit) break; 
								if (empleados.getID().equals(jtCedula.getText())) {
									comp = false;
									JOptionPane.showMessageDialog(null, language == 0 ? "Cedula repetida!" : "Repeated cedula!", language == 0 ? "Advertencia!" : "Warning!", JOptionPane.WARNING_MESSAGE);
									break;
								}
							}
							
						}
						
                        /*for (Trabajador empleados : Mecanics.Employe) {
                            if (!edit) break; 
                            if (empleados.getID().equals(jtCedula.getText())) {
                                comp = false;
                                JOptionPane.showMessageDialog(null, language == 0 ? "Cedula repetida!" : "Repeated cedula!", language == 0 ? "Advertencia!" : "Warning!", JOptionPane.WARNING_MESSAGE);
                                break;
                            }
                        }//*/

                        if (edit && comp) {
                            basedata.sentence("INSERT INTO `Trabajador` (`Code`, `ID`, `Name`, `LastName`, `Phone`, `Email`, `Address`, `Gender`, `Age`, `Admin`, `Image`) VALUES ('" +
                                        CodeGenerate() + "' , '" +
                                        jtCedula.getText() + "', '" +
                                        jtNombre.getText() + "', '" +
                                        jtApellido.getText() + "', '" +
                                        jtContacto.getText() + "', '" +
                                        jtEmail.getText() + "' , '" +
                                        jtDireccion.getText() + "', '" +
                                        genero + "' ," +
                                        Integer.parseInt(jsEdad.getValue() + "") + ", '" +
                                        permisos +
                                        "', './src/Employees/ImageEmployees/" + jtCedula.getText() + ".png');");

                            JOptionPane.showMessageDialog(null, language == 0 ? "Usuario ingresado!" : "User logged in!", language == 0 ? "Verificado!" : "Verified!", JOptionPane.PLAIN_MESSAGE);
                        }
						
						Trabajador p = Trabajador.get(Selected);
						
						if (!edit && p!=null) {
                            basedata.sentence("UPDATE `Trabajador` SET 'Name' = '" + jtNombre.getText() +
                                    "', 'LastName' = '" + jtApellido.getText() +
                                    "', 'Phone' = '" + jtContacto.getText() +
                                    "', 'Email' = '" + jtEmail.getText() +
                                    "', 'Address' = '" + jtDireccion.getText() +
                                    "', 'Gender' = '" + genero +
                                    "', 'Age' = '" + Integer.parseInt(jsEdad.getValue() + "") +
                                    "', 'Admin' = '" + permisos +
                                    "', 'Image' = './src/Employees/ImageEmployees/"
                                    + p.getID() + ".png" +
                                    "' WHERE ID = '" + p.getID() + "';");

                            JOptionPane.showMessageDialog(null, language == 0 ? "Usuario editado!" : "User edited!", language == 0 ? "Verificado!" : "Verified!", JOptionPane.PLAIN_MESSAGE);
                        }

                        /*if (!edit) {
                            basedata.sentence("UPDATE `Trabajador` SET 'Name' = '" + jtNombre.getText() +
                                    "', 'LastName' = '" + jtApellido.getText() +
                                    "', 'Phone' = '" + jtContacto.getText() +
                                    "', 'Email' = '" + jtEmail.getText() +
                                    "', 'Address' = '" + jtDireccion.getText() +
                                    "', 'Gender' = '" + genero +
                                    "', 'Age' = '" + Integer.parseInt(jsEdad.getValue() + "") +
                                    "', 'Admin' = '" + permisos +
                                    "', 'Image' = './src/Employees/ImageEmployees/"
                                    + Mecanics.Employe.get(selection).getID() + ".png" +
                                    "' WHERE ID = '" + Mecanics.Employe.get(selection).getID() + "';");

                            JOptionPane.showMessageDialog(null, language == 0 ? "Usuario editado!" : "User edited!", language == 0 ? "Verificado!" : "Verified!", JOptionPane.PLAIN_MESSAGE);
                        }//*/
                    } catch (SQLException e1) {
                        System.out.println(e1);
                    }

                    Recargar();

                    try {
						
						Trabajador p = Trabajador.get(Selected);
						
						File destino = new File("./src/Employees/ImageEmployees/"
                                + (edit ? jtCedula.getText() : p.getID()) + ".png");
						
                        /*File destino = new File("./src/Employees/ImageEmployees/"
                                + (edit ? jtCedula.getText() : Mecanics.Employe.get(selection).getID()) + ".png");//*/

                        Files.copy(imagenUser.toPath(), destino.toPath(), REPLACE_EXISTING);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, language == 0 ? "Datos erroneos" : "Wrong data", language == 0 ? "Advertencia!" : "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        editListener = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                edit = !edit;

                jlEdit.setBounds(21, 255, 30, 30);
                jlEdit.setIcon(new ImageIcon(imgEdit.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                jlLock.setIcon(
                        new ImageIcon((edit ? imgLock : imgUnlock).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

                jlDelete.setEnabled(!edit);
                jtaEmpleados.clearSelection();
                Selected = null;
                jlNombre.requestFocus(false);

                LimpiarDatos();
                if (edit) {
                    jtCedula.setEditable(true);
                } else
                    jtCedula.setEditable(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jlEdit.setBounds(16, 250, 40, 40);
                jlEdit.setIcon(new ImageIcon(imgEdit.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            }
        };

        pdfListener = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jlPdf.setBounds(587, 10, 30, 30);
                jlPdf.setIcon(new ImageIcon(imgPdf.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                jlPdf.setBounds(582, 5, 40, 40);
                jlPdf.setIcon(new ImageIcon(imgPdf.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                reporte();
            }
        };

        g1.addActionListener(generoListener);
        g2.addActionListener(generoListener);
        g3.addActionListener(generoListener);

        p1.addActionListener(permisoListener);
        p2.addActionListener(permisoListener);

        jtaEmpleados.addMouseListener(buttongroup);
        jtNombre.getDocument().addDocumentListener(user);
        jtApellido.getDocument().addDocumentListener(user);
        jtCedula.getDocument().addDocumentListener(user);
        jtContacto.getDocument().addDocumentListener(user);
        jtEmail.getDocument().addDocumentListener(user);
        jtDireccion.getDocument().addDocumentListener(user);
        jsEdad.addChangeListener(edadListener);
        jlDelete.addMouseListener(deleteListener);
        jlUserCode.addMouseListener(userCodeListener);
        jlAccept.addMouseListener(acceptListener);
        jlCancel.addMouseListener(cancelListener);
        jlEdit.addMouseListener(editListener);
        jlPdf.addMouseListener(pdfListener);
    }

    private void Listener() {
        jlExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                screen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Runner lg = new Runner();
                Runner.contentPane.removeAll();
                Runner.Opciones = new Menu(lg, User, Name);

                Runner.contentPane.add(Runner.Opciones, Integer.valueOf(0));

                lg.setVisible(true);

                jtNombre.getDocument().removeDocumentListener(user);
                jtApellido.getDocument().removeDocumentListener(user);
                jtCedula.getDocument().removeDocumentListener(user);
                jtContacto.getDocument().removeDocumentListener(user);
                jtEmail.getDocument().removeDocumentListener(user);
                jtDireccion.getDocument().removeDocumentListener(user);
                jlDelete.removeMouseListener(deleteListener);
                jlUserCode.removeMouseListener(userCodeListener);
                jlAccept.removeMouseListener(acceptListener);
                jlCancel.removeMouseListener(cancelListener);
                jlEdit.removeMouseListener(editListener);
                jlPdf.removeMouseListener(pdfListener);
                jsEdad.removeChangeListener(edadListener);
                g1.removeActionListener(generoListener);
                g2.removeActionListener(generoListener);
                g3.removeActionListener(generoListener);

                p1.removeActionListener(permisoListener);
                p2.removeActionListener(permisoListener);
                jtaEmpleados.removeMouseListener(buttongroup);

                LimpiarDatos();
                edit = true;
                jtCedula.setEditable(true);
                jlLock.setIcon(new ImageIcon(imgLock.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                screen.dispose();
            }
        });

        jlImgSelect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                jlImgSelect.setIcon(new ImageIcon(url + "vacio.png"));
                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jlImgSelect.setIcon(new ImageIcon(url + "camera_icon.png"));
                screen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e1) {
                }

                JFileChooser file = new JFileChooser("C:/Users/" + System.getProperty("user.name"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "png", "jpg", "jpeg");

                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                file.addChoosableFileFilter(filter);            
                file.setFileFilter(filter);

                if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    imagenUser = file.getSelectedFile();
                    jlImg.setIcon(new ImageIcon(new ImageIcon(String.valueOf(file.getSelectedFile())).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }
            }
        });

        jtaEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                /*selection = jtaEmpleados.getSelectedRow();

                if (!edit) {
                    jtCedula.setText(Mecanics.Employe.get(selection).getID());
                    jtNombre.setText(Mecanics.Employe.get(selection).getName());
                    jtApellido.setText(Mecanics.Employe.get(selection).getLastName());
                    jtContacto.setText(Mecanics.Employe.get(selection).getPhone());
                    jtEmail.setText(Mecanics.Employe.get(selection).getEmail());
                    jtDireccion.setText(Mecanics.Employe.get(selection).getAddress());
                    jsEdad.setValue(Mecanics.Employe.get(selection).getAge());
                    genero = Mecanics.Employe.get(selection).getGender();
                    permisos = Mecanics.Employe.get(selection).getAdmin() ? 1 : 0;
                    imagenUser = new File(Mecanics.Employe.get(selection).getImage());

                    jlImg.setIcon(new ImageIcon(new ImageIcon(Mecanics.Employe.get(selection).getImage()).getImage()
                            .getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                }//*/
				
				int indice = jtaEmpleados.getSelectedRow();
				
				if (indice!=-1){
				
					Object bup = jtaEmpleados.getValueAt(indice, 0);
					
					Selected = bup!=null ? bup.toString() : null;
					
					if (!edit && Selected!=null){
						
						Trabajador p = Trabajador.get(Selected);
						
						jtCedula.setText(p.getID());
						jtNombre.setText(p.getName());
						jtApellido.setText(p.getLastName());
						jtContacto.setText(p.getPhone());
						jtEmail.setText(p.getEmail());
						jtDireccion.setText(p.getAddress());
						jsEdad.setValue(p.getAge());
						genero = p.getGender();
						permisos = p.getAdmin() ? 1 : 0;
						imagenUser = new File(p.getImage());

						jlImg.setIcon(new ImageIcon(new ImageIcon(p.getImage()).getImage()
								.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
					}
					
			}
				
            }
        });

        // FocusListener
        FocusTextField(jtCedula, "\\d{10}");
        FocusTextField(jtNombre, ".*[^,;]*");
        FocusTextField(jtApellido, ".*[^,;]*");
        FocusTextField(jtContacto, "\\d{10}");
        FocusTextField(jtEmail, "\\w*@\\w*.com[^,;]*");
        FocusTextField(jtDireccion, ".*[^,;]*");

        EditTextField(jtCedula, "\\d{10}");
        EditTextField(jtNombre, "[^,;]*");
        EditTextField(jtApellido, "[^,;]*");
        EditTextField(jtContacto, "\\d{10}");
        EditTextField(jtEmail, "\\w*@\\w*.com[^,;]*");
        EditTextField(jtDireccion, "[^,;]*");

        // KeyListener
        jtCedula.addKeyListener(Tecla(jtNombre, null));
        jtNombre.addKeyListener(Tecla(jtApellido, jtCedula));
        jtApellido.addKeyListener(Tecla(jtContacto, jtNombre));
        jtContacto.addKeyListener(Tecla(jtEmail, jtApellido));
        jtEmail.addKeyListener(Tecla(jtDireccion, jtContacto));
        jtDireccion.addKeyListener(Tecla(null, jtEmail));
    }

    private void ChangeLanguage() {
        jlCedula.setText(PedirI[language] + CedulaI[language] + ":");
        jlNombre.setText(PedirI[language] + NombreI[language] + ":");
        jlApellido.setText(PedirI[language] + ApellidoI[language] + ":");
        jlContacto.setText(PedirI[language] + ContactoI[language] + ":");
        jlEmail.setText(PedirI[language] + EmailI[language] + ":");
        jlDireccion.setText(PedirI[language] + DireccionI[language] + ":");
        jlEdad.setText(PedirI[language] + EdadI[language] + ":");
        jlGenero.setText(GeneroI[language] + ":");
        jlPermisos.setText(PermisosI[language] + ":");

        g1.setText(ElegirGI[0][language]);
        g2.setText(ElegirGI[1][language]);
        g3.setText(ElegirGI[2][language]);
        p1.setText(ElegirPI[0][language]);
        p2.setText(ElegirPI[1][language]);

        jlUserNombre.setText(NombreI[language] + ((language == 0) ? " y " : " and ") + ApellidoI[language]);
        jlUserCedula.setText(CedulaI[language] + ": 123456789");
        jlUserContacto.setText(ContactoI[language] + ": 0123456789");
        jlUserEmail.setText(EmailI[language] + ": User@gmail.com");
        jlUserDireccion.setText(DireccionI[language] + ": ");
        jlUserEdad.setText(EdadI[language] + ": " + jsEdad.getValue());
        jlUserGenero.setText(GeneroI[language] + ": X");
        jlUserPermisos.setText(PermisosI[language] + ":  X");
        jlUserCode.setText(CodeI[language] + ": 0");

        modelo = new DefaultTableModel(
                new Object[][] {}, new Object[] {
                        CodeI[language],
                        CedulaI[language],
                        NombreI[language],
                        ApellidoI[language],
                        ContactoI[language],
                        EmailI[language],
                        DireccionI[language],
                        EdadI[language],
                        GeneroI[language],
                        PermisosI[language] }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jtaEmpleados.setModel(modelo);

        jlApellido.setBounds(16, 122, 149 + (language * 20), 21);
        jtNombre.setBounds(165 - (language * 15), 92, 274 + (language * 15), 20);
        jtApellido.setBounds(165 + (language * 10), 122, 274 - (language * 10), 20);
        jtContacto.setBounds(170 - (language * 10), 152, 269 + (language * 10), 20);
        jtDireccion.setBounds(177 - (language * 15), 212, 262 + (language * 15), 20);
        p1.setBounds(530 - (language * 25), 182, 105, 20);
        p2.setBounds(530 - (language * 25), 202, 105, 20);
        jsEdad.setBounds(585 - (language * 15), 62, 40, 20);

    }

    private void ChangeMode() {
        if (mode == 0) {
            color1 = new Color(238, 248, 254);
            color2 = new Color(175, 186, 199);
            colorField = Color.BLACK;
        } else {
            color1 = new Color(20, 35, 54);
            color2 = new Color(34, 37, 40);
            colorField = new Color(238, 248, 254);
        }

        jlCedula.setForeground(colorField);
        jlNombre.setForeground(colorField);
        jlApellido.setForeground(colorField);
        jlContacto.setForeground(colorField);
        jlEmail.setForeground(colorField);
        jlDireccion.setForeground(colorField);
        jlEdad.setForeground(colorField);
        jlGenero.setForeground(colorField);
        jlPermisos.setForeground(colorField);

        jtNombre.setForeground(colorField);
        jtApellido.setForeground(colorField);
        jtCedula.setForeground(colorField);
        jtContacto.setForeground(colorField);
        jtEmail.setForeground(colorField);
        jtDireccion.setForeground(colorField);

        jtCedula.setBackground(color1);
        jtNombre.setBackground(color1);
        jtApellido.setBackground(color1);
        jtContacto.setBackground(color1);
        jtEmail.setBackground(color1);
        jtDireccion.setBackground(color1);

        jlUserCedula.setForeground(colorField);
        jlUserContacto.setForeground(colorField);
        jlUserEmail.setForeground(colorField);
        jlUserDireccion.setForeground(colorField);
        jlUserEdad.setForeground(colorField);
        jlUserGenero.setForeground(colorField);
        jlUserPermisos.setForeground(colorField);
        jlUserCode.setForeground(colorField);

        g1.setForeground(colorField);
        g2.setForeground(colorField);
        g3.setForeground(colorField);
        p1.setForeground(colorField);
        p2.setForeground(colorField);

        g1.setBackground(color1);
        g2.setBackground(color1);
        g3.setBackground(color1);
        p1.setBackground(color1);
        p2.setBackground(color1);

        userPanel.setBackground(color2);
        mainPanel.setBackground(color1);

        String m = ((mode == 0) ? "Light" : "Dark") + ".png";

        imgExit = new ImageIcon(url + "Cerrar" + m).getImage();
        imgEdit = new ImageIcon(url + "Editar" + m).getImage();
        imgAccept = new ImageIcon(url + "Aceptar" + m).getImage();
        imgCancel = new ImageIcon(url + "Cancelar" + m).getImage();
        imgDelete = new ImageIcon(url + "Borrar" + m).getImage();
        imgUser = new ImageIcon(url + "image-not-found.png").getImage();
        imgPdf = new ImageIcon(url + "Pdf" + m).getImage();

        jlExit.setIcon(new ImageIcon(imgExit.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        jlEdit.setIcon(new ImageIcon(imgEdit.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        jlAccept.setIcon(new ImageIcon(imgAccept.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        jlCancel.setIcon(new ImageIcon(imgCancel.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        jlDelete.setIcon(new ImageIcon(imgDelete.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        jlPdf.setIcon(new ImageIcon(imgPdf.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        jlImg.setIcon(new ImageIcon(imgUser.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

        jlDelete.setEnabled(false);
    }

    private static void FocusTextField(JTextField jtext, String pattern) {
        jtext.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                jtext.setBorder(new MatteBorder(0, 0, 2, 0,
                        (jtext.getText().matches(pattern) || jtext.getText().isEmpty()) ? Color.BLUE : Color.RED));
            }

            @Override
            public void focusLost(FocusEvent evt) {
                jtext.setBorder(new MatteBorder(0, 0, 2, 0,
                        (jtext.getText().matches(pattern) || jtext.getText().isEmpty()) ? Color.GRAY : Color.RED));
            }
        });
    }

    private static void EditTextField(JTextField jtext, String pattern) {
        jtext.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            public void updateFieldState() {
                if (jtext.isFocusOwner()) {
                    jtext.setBorder(new MatteBorder(0, 0, 2, 0,
                            (jtext.getText().matches(pattern) || jtext.getText().isEmpty()) ? Color.BLUE : Color.RED));
                }
            }
        });
    }

    private static KeyAdapter Tecla(JTextField down, JTextField up) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) && down != null) {
                    down.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP && up != null) {
                    up.requestFocus();
                }
            }
        };
    }

    private void LimpiarDatos() {
        jtCedula.setText("");
        jtNombre.setText("");
        jtApellido.setText("");
        jtContacto.setText("");
        jtEmail.setText("");
        jtDireccion.setText("");
        jsEdad.setValue(18);
        bgGenero.clearSelection();
        bgPermisos.clearSelection();
        genero = '.';
        permisos = -1;
        if (language == 0) {
            jlUserGenero.setText(GeneroI[0] + ": X");
            jlUserPermisos.setText(PermisosI[0] + ":  X");
        } else {
            jlUserGenero.setText(GeneroI[1] + ": X");
            jlUserPermisos.setText(PermisosI[1] + ":  X");
        }
        imagenUser = new File("./src/ResourcePackCaja/image-not-found.png");
        jlImg.setIcon(new ImageIcon(
                new ImageIcon(imagenUser.toString()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
    }

    private static void Recargar() {
        modelo.setRowCount(0);

        try {
            ResultSet resultado = basedata.consulta("SELECT * FROM Trabajador;");

            while (resultado.next()) {
                modelo.addRow(new String[] { resultado.getString("Code"),
                        resultado.getString("ID"),
                        resultado.getString("Name"),
                        resultado.getString("LastName"),
                        resultado.getString("Phone"),
                        resultado.getString("Email"),
                        resultado.getString("Address"),
                        resultado.getString("Age"),
                        resultado.getString("Gender"),
                        resultado.getString("Admin") });
            }

        } catch (SQLException e) {
        }
    }

    private static String CodeGenerate() {
        if (jtCedula.getText().isEmpty() && jtNombre.getText().isEmpty() && jtApellido.getText().isEmpty()
                && jtContacto.getText().isEmpty() && jtEmail.getText().isEmpty() && jtDireccion.getText().isEmpty()) {
            return "0";
        }

        return String.valueOf(Math.abs((jtCedula.getText() + jtNombre.getText() + jtApellido.getText()
                + jtContacto.getText() + jtEmail.getText() + jtDireccion.getText() + jsEdad.getValue()).hashCode()));
    }

    private static boolean CheckTextfield() {
        return (jtCedula.getText().matches("\\d{10}") &&
                jtNombre.getText().length() > 0 && jtNombre.getText().matches("[^,;]*") &&
                jtApellido.getText().length() > 0 && jtApellido.getText().matches("[^,;]*") &&
                jtContacto.getText().matches("\\d{10}") &&
                jtEmail.getText().matches("\\w*@\\w*.com[^,;]*") &&
                jtDireccion.getText().length() > 0 && jtDireccion.getText().matches("[^,;]*") &&
                genero != '.' &&
                permisos != -1);
    }

    public void reporte() {
        Path Downloads = Paths.get(System.getProperty("user.home"), "Downloads");
        String dir = Downloads.toString() + "\\EMP- " + LocalDate.now() + ".pdf";

        int i = 1;
        while (new File(dir).exists())
            dir = Downloads.toString() + "\\EMP- " + LocalDate.now() + "(" + (i++) + ").pdf";

        try (PdfWriter pdfw = new PdfWriter(new File(dir))) {
            PdfDocument pdfdoc = new PdfDocument(pdfw);

            pdfdoc.setDefaultPageSize(PageSize.LETTER);

            Document doc = new Document(pdfdoc);

            com.itextpdf.layout.element.Image Logo = new com.itextpdf.layout.element.Image(ImageDataFactory.create(url + "Registro.png"));

            Logo.setAutoScale(false);
            Logo.scaleToFit(180, 180);
            Logo.setHorizontalAlignment(HorizontalAlignment.CENTER);

            Paragraph Title = new Paragraph(language == 0 ? "Empleados" : "Employees");

            Title.setFontSize(20);
            Title.setBold();
            Title.setTextAlignment(TextAlignment.CENTER);
            Title.setVerticalAlignment(VerticalAlignment.MIDDLE);

            Table tableta = new Table(2);
            tableta.setWidth(UnitValue.createPercentValue(100));
            tableta.addCell(new Paragraph(language == 0 ? "Imagen" : "Image"));
            tableta.addCell(new Paragraph(language == 0 ? "Datos" : "Data"));

            for (int j = 0; j < 2; j++)
                tableta.getCell(0, j).setBorder(null).setBold().setFontSize(15).setTextAlignment(TextAlignment.CENTER);

            try {
                //ArrayList<Trabajador> employes = Mecanics.Employe;

				ArrayList<Trabajador> employes = new ArrayList<>();
				
				Trabajador[] q = Trabajador.get();
				
				if (q!=null){
				
					for (Trabajador p : q){
						
						employes.add(p);
						
					}
					
				}

                for (int j = 0; j < employes.size(); j++) {
                    String datos = CodeI[language] + ": " + employes.get(j).getCode() + "\n" +
                                   CedulaI[language] + ": " + employes.get(j).getID() + "\n" +
                                   NombreI[language] + ": " + employes.get(j).getName() + "\n" +
                                   ApellidoI[language] + ": " + employes.get(j).getLastName() + "\n" +
                                   ContactoI[language] + ": " + employes.get(j).getPhone() + "\n" +
                                   EmailI[language] + ": " + employes.get(j).getEmail() + "\n" +
                                   DireccionI[language] + ": " + employes.get(j).getAddress() + "\n" +
                                   EdadI[language] + ": " + employes.get(j).getAge() + "\n" +
                                   GeneroI[language] + ": " + employes.get(j).getGender() + "\n" +
                                   PermisosI[language] + ": " + employes.get(j).getAdmin() + "\n\n";

                    agregarFila(tableta, employes.get(j).getImage(), datos);

                    tableta.getCell(j + 1, 0).setBorder(null).setFontSize(8).setTextAlignment(TextAlignment.LEFT);

                    tableta.getCell(j + 1, 1).setBorder(null).setFontSize(8)
                                                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
                }
            } catch (Exception e) {}

            doc.add(Title);
            doc.add(Logo);
            doc.add(tableta);
            doc.close();
            pdfdoc.close();

            Desktop.getDesktop().open(new File(dir));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void agregarFila(Table table, String nombreImagen, String texto) {
        Cell cellImagen = new Cell();
        Cell cellTexto = new Cell();

        try {
            com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(ImageDataFactory.create(nombreImagen));

            image.setAutoScale(false);
            image.scaleToFit(100, 100);
            cellImagen.add(image);
        } catch (IOException e) { e.printStackTrace(); }

        cellTexto.add(new Paragraph(texto));
        table.addCell(cellImagen);
        table.addCell(cellTexto);
    }
}