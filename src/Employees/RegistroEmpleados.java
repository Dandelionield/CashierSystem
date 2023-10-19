//package Employees;

//import Main.Runner;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import javax.swing.table.DefaultTableModel;

public class RegistroEmpleados {
    private static String url = "./imagenes/";
    private static Boolean edit = true;
    private static JFrame screen = new JFrame("Ventana");
    private static JPanel mainPanel = new JPanel();
    private static JPanel userPanel = new JPanel();

    //private static byte theme = Mecanics.getMode(true);
    //private static byte language = Mecanics.getLanguage(true);
    private static byte language = 0;
    private static byte mode = 0;

    private static Color color1, color2, colorField;
    private static String[] CedulaI = {"Cedula", "Cedula"};
    private static String[] NombreI = {"Nombre", "Name"};
    private static String[] ApellidoI = {"Apellido", "Last name"};
    private static String[] ContactoI = {"Contacto", "Contact"};
    private static String[] EmailI = {"Email", "Email"};
    private static String[] DireccionI = {"Direccion", "Address"};
    private static String[] EdadI = {"Edad", "Age"};
    private static String[] GeneroI = {"Genero", "Gender"};
    private static String[][] ElegirGI = {{"Femenino", "Female"}, {"Masculino", "Male"}, {"No decirlo", "Not say it"}};
    private static String[] PermisosI = {"Permisos", "Permit"};
    private static String[][] ElegirPI = {{"Empleado", "Employee"}, {"Administrador", "Administrator"}};
    private static String[] PedirI = {"Ingrese su ", "Enter your "};

    private static JLabel jlCedula = new JLabel();
    private static JLabel jlNombre = new JLabel();
    private static JLabel jlApellido = new JLabel();
    private static JLabel jlContacto = new JLabel();
    private static JLabel jlEmail = new JLabel();
    private static JLabel jlDireccion = new JLabel();
    private static JLabel jlEdad = new JLabel();
    private static JLabel jlGenero = new JLabel();
    private static JLabel jlPermisos = new JLabel();

    private static JTextField jtCedula = new JTextField("", 15);
    private static JTextField jtNombre = new JTextField("", 15);
    private static JTextField jtApellido = new JTextField("", 30);
    private static JTextField jtContacto = new JTextField("", 30);
    private static JTextField jtEmail = new JTextField("", 30);
    private static JTextField jtDireccion = new JTextField("", 30);

    private static JLabel jlUserCedula = new JLabel();
    private static JLabel jlUserNombre = new JLabel("", SwingConstants.CENTER);
    private static JLabel jlUserContacto = new JLabel();
    private static JLabel jlUserEmail = new JLabel();
    private static JLabel jlUserDireccion = new JLabel();
    private static JLabel jlUserEdad = new JLabel();
    private static JLabel jlUserGenero = new JLabel();
    private static JLabel jlUserPermisos = new JLabel();

    private static Image imgExit;
    private static Image imgEdit;
    private static Image imgAccept;
    private static Image imgCancel;
    private static Image imgDelete;
    private static Image imgUser;
    private static Image imgLanguage;
    private static Image imgMode;
    private static Image imgPdf;

    private static JLabel jlExit = new JLabel();
    private static JLabel jlEdit = new JLabel();
    private static JLabel jlAccept = new JLabel();
    private static JLabel jlCancel = new JLabel();
    private static JLabel jlDelete = new JLabel();
    private static JLabel jlLanguage = new JLabel();
    private static JLabel jlMode = new JLabel();
    private static JLabel jlPdf = new JLabel();
    private static JLabel jlImg = new JLabel();

    private static JRadioButton g1 = new JRadioButton(), g2 = new JRadioButton(), g3 = new JRadioButton();
    private static JRadioButton p1 = new JRadioButton(), p2 = new JRadioButton();
    private static JSpinner jsEdad = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1));
    private static DefaultTableModel modelo;
    private static JTable jtaEmpleados = new JTable();

    public static void main(String[] abc){
        RegistroEmpleados p = new RegistroEmpleados();
    }

    public RegistroEmpleados(){
        float ini = System.currentTimeMillis()*1000;
        /*          DECLARACION DE LOS OBJETOS          */
        ChangeMode();
        ChangeLanguage();

        Image imgLock = new ImageIcon(url + "CandadoCerrado.png").getImage();
        Image imgUnlock = new ImageIcon(url + "CandadoAbierto.png").getImage();
        JLabel jlLock = new JLabel(new ImageIcon(imgLock.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        JLabel jlImgSelect = new JLabel(new ImageIcon(url + "vacio.png"));
        jlMode.setIcon(new ImageIcon(imgMode.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        
        jtaEmpleados.setModel(modelo);
        JScrollPane jscEmpleados = new JScrollPane(jtaEmpleados);
        ButtonGroup bgGenero = new ButtonGroup();
        ButtonGroup bgPermisos = new ButtonGroup();

        //Border
        MatteBorder border = new MatteBorder(0, 0, 2, 0, Color.GRAY);

        /*          CONFIGURACION           */

        //Labels
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

        //Textfields
        jtCedula.setBounds(159,62,280,20);
        jtEmail.setBounds(146, 182, 293, 20);
        jscEmpleados.setBounds(16, 300, 604, 190);

        jtCedula.setBorder(border);
        jtNombre.setBorder(border);
        jtApellido.setBorder(border);
        jtContacto.setBorder(border);
        jtEmail.setBorder(border);
        jtDireccion.setBorder(border);

        //JLabels User
        jlUserNombre.setBounds(10, 220, 285, 25);
        jlUserNombre.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE));
        jlUserCedula.setBounds(10, 270, 300, 25);
        jlUserContacto.setBounds(10, 300, 300, 25);
        jlUserEmail.setBounds(10, 330, 300, 25);
        jlUserDireccion.setBounds(10, 360, 300, 25);
        jlUserEdad.setBounds(10, 390, 200, 25);
        jlUserGenero.setBounds(10, 420, 200, 25);
        jlUserPermisos.setBounds(10, 450, 300, 25);

        Font jlFuente2 = new Font("Clarendon Blk BT", Font.PLAIN, 20);
        jlUserCedula.setFont(jlFuente2);
        jlUserContacto.setFont(jlFuente2);
        jlUserEmail.setFont(jlFuente2);
        jlUserDireccion.setFont(jlFuente2);
        jlUserEdad.setFont(jlFuente2);
        jlUserGenero.setFont(jlFuente2);
        jlUserPermisos.setFont(jlFuente2);

        jlUserNombre.setForeground(Color.GRAY);

        //JRadioButton
        g1.setBounds(520, 92, 90, 20);
        g2.setBounds(520, 112, 90, 20);
        g3.setBounds(520, 132, 90, 20);

        Font jlFuente3 = new Font("Clarendon Blk BT", Font.PLAIN, 12);
        g1.setFont(jlFuente3);
        g2.setFont(jlFuente3);
        g3.setFont(jlFuente3);
        p1.setFont(jlFuente3);
        p2.setFont(jlFuente3);

        //User
        jlExit.setBounds(5,5,40,40);
        jlCancel.setBounds(581, 250, 40, 40);
        jlAccept.setBounds(532, 250, 40, 40);
        jlDelete.setBounds(482, 250, 40, 40);
        jlEdit.setBounds(16, 250, 40, 40);
        jlLock.setBounds(62, 250, 40, 40);
        jlLanguage.setBounds(581,5,40,40);
        jlMode.setBounds(532,5,40,40);
        jlPdf.setBounds(482,5,40,40);
        jlImg.setBounds(55,10,200,200);
        jlImgSelect.setBounds(55,10,200,200);

        jlExit.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) { screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
            @Override public void mouseEntered(MouseEvent e) { screen.setCursor(new Cursor(Cursor.HAND_CURSOR)); }

            @Override public void mousePressed(MouseEvent e) {
                
            }
        });

        jlCancel.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) { screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
            @Override public void mouseEntered(MouseEvent e) { screen.setCursor(new Cursor(Cursor.HAND_CURSOR)); }

            @Override public void mousePressed(MouseEvent e) {
                jlCancel.setBounds(586, 255, 30, 30);
                jlCancel.setIcon(new ImageIcon(imgCancel.getScaledInstance(30,30,Image.SCALE_SMOOTH)));
            }

            @Override public void mouseReleased(MouseEvent e) {
                jlCancel.setBounds(581, 250, 40, 40);
                jlCancel.setIcon(new ImageIcon(imgCancel.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
            }
        });

        jlAccept.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) { screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
            @Override public void mouseEntered(MouseEvent e) { screen.setCursor(new Cursor(Cursor.HAND_CURSOR)); }

            @Override public void mousePressed(MouseEvent e) {
                jlAccept.setBounds(537, 255, 30, 30);
                jlAccept.setIcon(new ImageIcon(imgAccept.getScaledInstance(30,30,Image.SCALE_SMOOTH)));
            }

            @Override public void mouseReleased(MouseEvent e) {
                jlAccept.setBounds(532, 250, 40, 40);
                jlAccept.setIcon(new ImageIcon(imgAccept.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
            }
        });

        jlEdit.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) { screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
            @Override public void mouseEntered(MouseEvent e) { screen.setCursor(new Cursor(Cursor.HAND_CURSOR)); }

            @Override public void mousePressed(MouseEvent e) {
                edit = !edit;

                jlEdit.setBounds(21, 255, 30, 30);
                jlEdit.setIcon(new ImageIcon(imgEdit.getScaledInstance(30,30,Image.SCALE_SMOOTH)));
                jlLock.setIcon(new ImageIcon((edit ? imgLock : imgUnlock).getScaledInstance(40,40,Image.SCALE_SMOOTH)));
            }

            @Override public void mouseReleased(MouseEvent e) {
                jlEdit.setBounds(16, 250, 40, 40);
                jlEdit.setIcon(new ImageIcon(imgEdit.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
            }
        });

        jlDelete.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) { screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
            @Override public void mouseEntered(MouseEvent e) { if (!edit) screen.setCursor(new Cursor(Cursor.HAND_CURSOR)); }

            @Override public void mousePressed(MouseEvent e) {
                if (!edit) {
                    jlDelete.setBounds(487, 255, 30, 30);
                    jlDelete.setIcon(new ImageIcon(imgDelete.getScaledInstance(30,30,Image.SCALE_SMOOTH)));
                }
            }

            @Override public void mouseReleased(MouseEvent e) {
                jlDelete.setBounds(482, 250, 40, 40);
                jlDelete.setIcon(new ImageIcon(imgDelete.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
            }
        });

        jlLanguage.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) { screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
            @Override public void mouseEntered(MouseEvent e) { screen.setCursor(new Cursor(Cursor.HAND_CURSOR)); }

            @Override public void mousePressed(MouseEvent e) {
                jlLanguage.setBounds(586, 10, 30, 30);
                jlLanguage.setIcon(new ImageIcon(imgLanguage.getScaledInstance(30,30,Image.SCALE_SMOOTH)));

                //Cambiar lenguaje
                language = (byte) ((language == 0) ? 1 : 0);
                ChangeLanguage();
            }

            @Override public void mouseReleased(MouseEvent e) {
                jlLanguage.setBounds(581, 5, 40, 40);
                jlLanguage.setIcon(new ImageIcon(imgLanguage.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
            }
        });

        jlMode.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) { screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
            @Override public void mouseEntered(MouseEvent e) { screen.setCursor(new Cursor(Cursor.HAND_CURSOR)); }

            @Override public void mousePressed(MouseEvent e) {
                jlMode.setBounds(537, 10, 30, 30);

                //Cambiar modo
                mode = (byte) ((mode == 0) ? 1 : 0);
                ChangeMode();
            }

            @Override public void mouseReleased(MouseEvent e) {
                jlMode.setBounds(532, 5, 40, 40);
                jlMode.setIcon(new ImageIcon(imgMode.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
            }
        });

        jlPdf.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) { screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
            @Override public void mouseEntered(MouseEvent e) { screen.setCursor(new Cursor(Cursor.HAND_CURSOR)); }

            @Override public void mousePressed(MouseEvent e) {
                jlPdf.setBounds(487, 10, 30, 30);
                jlPdf.setIcon(new ImageIcon(imgPdf.getScaledInstance(30,30,Image.SCALE_SMOOTH)));
            }

            @Override public void mouseReleased(MouseEvent e) {
                jlPdf.setBounds(482, 5, 40, 40);
                jlPdf.setIcon(new ImageIcon(imgPdf.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
            }
        });

        jlImgSelect.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) {
                jlImgSelect.setIcon(new ImageIcon(url + "vacio.png"));
                screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override public void mouseEntered(MouseEvent e) {
                jlImgSelect.setIcon(new ImageIcon(url + "camera_icon.png"));
                screen.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} 
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {}

                JFileChooser j = new JFileChooser("C:/Users/" + System.getProperty("user.name"));
                j.showOpenDialog(null);

                if (j.getSelectedFile() != null) {
                    Image img = new ImageIcon(j.getSelectedFile()+"").getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
                    jlImg.setIcon(new ImageIcon(img));
                }
            }
        });
        
        //FocusListener
        jtCedula.addFocusListener(FocusText(jtCedula));
        jtNombre.addFocusListener(FocusText(jtNombre));
        jtApellido.addFocusListener(FocusText(jtApellido));
        jtContacto.addFocusListener(FocusText(jtContacto));
        jtEmail.addFocusListener(FocusText(jtEmail));
        jtDireccion.addFocusListener(FocusText(jtDireccion));

        //DocumentListener
        DocumentListener userName = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { updateFieldState(); }
            @Override public void removeUpdate(DocumentEvent e) { updateFieldState(); }
            @Override public void changedUpdate(DocumentEvent e) { updateFieldState(); }

            public void updateFieldState() {
                if (jtNombre.getText().isEmpty() && jtApellido.getText().isEmpty()) {
                    jlUserNombre.setText(NombreI[language] + ((language == 0) ? " y " : " and ") + ApellidoI[language]);
                    jlUserNombre.setForeground(Color.GRAY);
                } else {
                    jlUserNombre.setText(jtNombre.getText() + " " + jtApellido.getText());
                    jlUserNombre.setForeground(colorField);
                }
            }
        };

        jtNombre.getDocument().addDocumentListener(userName);
        jtApellido.getDocument().addDocumentListener(userName);
        jtCedula.getDocument().addDocumentListener(DocumentL(jtCedula, jlUserCedula, CedulaI, ""));
        jtContacto.getDocument().addDocumentListener(DocumentL(jtContacto, jlUserContacto, ContactoI, "0123456789"));
        jtEmail.getDocument().addDocumentListener(DocumentL(jtEmail, jlUserEmail, EmailI, "User@gmail.com"));
        jtDireccion.getDocument().addDocumentListener(DocumentL(jtDireccion, jlUserDireccion, DireccionI, ""));
        jsEdad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jlUserEdad.setText(EdadI[language] + ": " + jsEdad.getValue());
            }
        });

        //KeyListener
        jtCedula.addKeyListener(Tecla(jtNombre, null));
        jtNombre.addKeyListener(Tecla(jtApellido, jtCedula));
        jtApellido.addKeyListener(Tecla(jtContacto, jtNombre));
        jtContacto.addKeyListener(Tecla(jtEmail, jtApellido));
        jtEmail.addKeyListener(Tecla(jtDireccion, jtContacto));
        jtDireccion.addKeyListener(Tecla(null, jtEmail));

        /*          RadioButton          */
        bgGenero.add(g1);
        bgGenero.add(g2);
        bgGenero.add(g3);

        bgPermisos.add(p1);
        bgPermisos.add(p2);

        g1.addActionListener(RButton(g1, jlUserGenero, GeneroI, ElegirGI[0]));
        g2.addActionListener(RButton(g2, jlUserGenero, GeneroI, ElegirGI[1]));
        g3.addActionListener(RButton(g3, jlUserGenero, GeneroI, ElegirGI[2]));

        p1.addActionListener(RButton(p1, jlUserPermisos, PermisosI, ElegirPI[0]));
        p2.addActionListener(RButton(p2, jlUserPermisos, PermisosI, ElegirPI[1]));

        /*          JPANEL PRINCIPAL            */
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
        mainPanel.add(jlLanguage);
        mainPanel.add(jlMode);
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

        userPanel.setLayout(null);
        userPanel.setBounds(640, 0, 320, 540);

        mainPanel.add(userPanel); 
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 960, 540);

        /*          JFRAME          */
        screen.add(mainPanel);
        screen.setResizable(false);
        screen.setLayout(null);
        screen.setSize(960, 540);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
        screen.setIconImage(new ImageIcon(url + "Icono.png").getImage());
        System.out.println(System.currentTimeMillis()*1000-ini);
    }

    private static void ChangeLanguage() {
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
        jlUserEdad.setText(EdadI[language] + ": " + jsEdad.getValue());
        jlUserGenero.setText(GeneroI[language] + ": " + ((g1.isSelected()) ? ElegirGI[0][language] : 
                                                        ((g2.isSelected()) ? ElegirGI[1][language] :
                                                        ((g3.isSelected()) ? ElegirGI[2][language] : "X"))));
        jlUserPermisos.setText(PermisosI[language] + ": " + ((p1.isSelected()) ? ElegirPI[0][language] : 
                                                            ((p2.isSelected()) ? ElegirPI[1][language] : "X")));

        modelo = new DefaultTableModel(
            new Object [][] {}, new Object[] { 
                CedulaI[language], 
                NombreI[language], 
                ApellidoI[language], 
                ContactoI[language], 
                EmailI[language], 
                DireccionI[language], 
                EdadI[language], 
                GeneroI[language], 
                PermisosI[language]}) 
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jtaEmpleados.setModel(modelo);

        jlApellido.setBounds(16, 122, 149 + (language*20), 21);
        jtNombre.setBounds(165 - (language * 15), 92, 274 + (language * 15), 20);
        jtApellido.setBounds(165 + (language * 10), 122, 274 - (language * 10), 20);
        jtContacto.setBounds(170 - (language * 10), 152, 269 + (language * 10), 20);
        jtDireccion.setBounds(177 - (language * 15), 212, 262 + (language * 15), 20);
        p1.setBounds(530 - (language * 25), 182, 105, 20);
        p2.setBounds(530 - (language * 25), 202, 105, 20);
        jsEdad.setBounds(585 - (language * 15), 62, 40, 20);
        
    }

    private static void ChangeMode() {
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

        String m = ((mode == 0) ? "Light": "Dark") + ".png";

        imgExit = new ImageIcon(url + "Cerrar" + m).getImage();
        imgEdit = new ImageIcon(url + "Editar" + m).getImage();
        imgAccept = new ImageIcon(url + "Aceptar" + m).getImage();
        imgCancel = new ImageIcon(url + "Cancelar" + m).getImage();
        imgDelete = new ImageIcon(url + "Borrar" + m).getImage();
        imgUser = new ImageIcon(url + "Usuario" + m).getImage();
        imgLanguage = new ImageIcon(url + "idioma" + m).getImage();
        imgMode = new ImageIcon(url + m).getImage();
        imgPdf = new ImageIcon(url + "Pdf" + m).getImage();

        jlExit.setIcon(new ImageIcon(imgExit.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        jlEdit.setIcon(new ImageIcon(imgEdit.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        jlAccept.setIcon(new ImageIcon(imgAccept.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        jlCancel.setIcon(new ImageIcon(imgCancel.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        jlDelete.setIcon(new ImageIcon(imgDelete.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        jlLanguage.setIcon(new ImageIcon(imgLanguage.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        jlMode.setIcon(new ImageIcon(imgMode.getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        jlPdf.setIcon(new ImageIcon(imgPdf.getScaledInstance(40,40,Image.SCALE_SMOOTH)));
        jlImg.setIcon(new ImageIcon(imgUser.getScaledInstance(200,200,Image.SCALE_SMOOTH)));
    }

    private static FocusListener FocusText(JTextField jtext) {
        return new FocusListener() {
            @Override public void focusGained(FocusEvent evt) { jtext.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE)); }
            @Override public void focusLost(FocusEvent evt) { jtext.setBorder(new MatteBorder(0, 0, 2, 0, Color.GRAY)); }
        };
    }

    private static DocumentListener DocumentL(JTextField jtext, JLabel jLabel, String[] msg, String msg2) {
        return new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { updateFieldState(); }
            @Override public void removeUpdate(DocumentEvent e) { updateFieldState(); }
            @Override public void changedUpdate(DocumentEvent e) { updateFieldState(); }

            public void updateFieldState() {
                jLabel.setText(msg[language] + ": " + ((jtext.getText().isEmpty()) ? msg2 : jtext.getText()));
            }
        };
    }

    private static ActionListener RButton(JRadioButton button, JLabel jlabel, String[] txt, String[] txt2) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(button.isSelected()) jlabel.setText(txt[language] + ": " + txt2[language]);
            }
        };
    }

    private static KeyAdapter Tecla(JTextField down, JTextField up) {
        return new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) && down != null) { down.requestFocus(); }
                if (e.getKeyCode() == KeyEvent.VK_UP && up != null) { up.requestFocus(); }
            }
        };
    }
}