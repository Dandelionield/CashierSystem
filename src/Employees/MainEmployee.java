import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MainEmployee extends JFrame {
    public static Color primary = new Color(121, 80, 242);
    public static Color secundary = new Color(233, 236, 239);
    public static Color tertiary = new Color(206, 212, 218);
    private boolean isLock = false;
    private final String IMAGE_URL = "./ImagePack/";
    private String filters = "SELECT * FROM Trabajador;";
    private Conexion basedata = new Conexion();

    private DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JTable userTable = new JTable(tableModel);
    private JScrollPane scrollTable = new JScrollPane(userTable);

    private JComboboxCheck addColumn = new JComboboxCheck("Añadir columnas");

    //UserInfo

    private CustomTextField jtName = new CustomTextField("Ingrese su nombre y apellido");
    private CustomTextField jtCedula = new CustomTextField("Ingrese su cedula");
    private CustomTextField jtContact = new CustomTextField("Ingrese su numero");
    private CustomTextField jtEmail = new CustomTextField("Ingrese su correo");
    private CustomTextField jtAdress = new CustomTextField("Ingrese su direccion");
    
    private JComboBox<String> jcGender = new JComboBox<>(new String[] {"Genero", "Masculino", "Femenino", "No decirlo"});
    private JComboBox<String> jcRoles = new JComboBox<>(new String[] {"Roles", "Empleado", "Administrador"});

    private JSpinner jsAge = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1));

    public static void main(String[] args) {
        new MainEmployee();
    }

    public MainEmployee() {
        setTitle("Administración de empleados");
        setSize(1054, 667);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setBackground(tertiary);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(getMainPanel());
        repaint();
    }

    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, getWidth() - 1, getHeight() - 1);
        mainPanel.setBackground(tertiary);

        mainPanel.add(getTablePanel());
        mainPanel.add(getUserPanel());
        
        return mainPanel;
    }

    private JPanel getTablePanel() {
        RoundedPanel tablePanel = new RoundedPanel();

        tablePanel.setBounds(41, 40, 630, 550);
        tablePanel.setLayout(null);
        tablePanel.setBackground(secundary);
        tablePanel.setBorderColor(primary);

        CustomButton jbBack = new CustomButton();
        jbBack.setBounds(25, 25, 40, 40);
        jbBack.setIcon(IMAGE_URL + "back.png");
        jbBack.setBackground(secundary);
        jbBack.setColorFocus(tertiary);
        jbBack.setColorPressed(primary);

        CustomButton jbPdf = new CustomButton();
        jbPdf.setBounds(270, 25, 40, 40);
        jbPdf.setIcon(IMAGE_URL + "pdf.png");
        jbPdf.setBackground(secundary);
        jbPdf.setColorFocus(tertiary);
        jbPdf.setColorPressed(primary);

        CustomTextField jtSearch = new CustomTextField("Buscar");
        jtSearch.setBounds(315, 30, 155, 30);
        jtSearch.setBorderColor(primary, tertiary);

        Object[] column = {"Code", "ID", "Name", "LastName", "Phone", "Email", "Address", "Age", "Gender", "Admin"};

        JComboBox<String> filter = new JComboBox<>(new String[] {"Filtros"});
        filter.setBounds(480, 30, 120, 30);

        scrollTable.setBounds(30, 80, 570, 390);
        scrollTable.setBorder(new LineBorder(primary));

        CustomButton jbEdit = new CustomButton();
        jbEdit.setBounds(25, 485, 40, 40);
        jbEdit.setIcon(IMAGE_URL + "edit.png");
        jbEdit.setBackground(secundary);
        jbEdit.setColorFocus(tertiary);
        jbEdit.setColorPressed(primary);
        jbEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLock = !isLock;
                System.out.println(isLock);
            }
        });

        JLabel jlLock = new JLabel(
            new ImageIcon(new ImageIcon(IMAGE_URL + "lock.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))
        );
        jlLock.setBounds(70, 490, 30, 30);

        addColumn.setBounds(110, 490, 145, 30);

        for (Object object : column) {
            filter.addItem(object.toString());
            addColumn.addItem(object.toString());
        }

        JButton jbDelete = new JButton("Eliminar");
        jbDelete.setBounds(480, 490, 120, 30);
        jbDelete.setBackground(primary);
        jbDelete.setForeground(secundary);
        jbDelete.setContentAreaFilled(true);
        jbDelete.setOpaque(true);

        addColumn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addColumn.getSelectedIndex() != -1) {
                    addColumn.getItemAt(addColumn.getSelectedIndex()).setCheck();
                    addColumn.setSelectedIndex(-1);

                    reloadTable();
                }
            }
        });

        addColumn.setSelectedIndex(-1);

        jtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {funtion();}

            @Override
            public void removeUpdate(DocumentEvent e) {funtion();}

            @Override
            public void changedUpdate(DocumentEvent e) {funtion();}
            
            public void funtion() {
                if (jtSearch.getText().isEmpty()) {
                    filters = "SELECT * FROM Trabajador;";
                }

                if (!filter.getSelectedItem().equals("Filtros")) {
                    filters = "SELECT * FROM Trabajador WHERE " + filter.getSelectedItem() + " LIKE '%" + jtSearch.getText() + "%';";
                }

                reloadTable();
            }
        });

        tablePanel.add(jbBack);
        tablePanel.add(jbPdf);
        tablePanel.add(jtSearch);
        tablePanel.add(filter);
        tablePanel.add(scrollTable);
        tablePanel.add(jbEdit);
        tablePanel.add(jlLock);
        tablePanel.add(addColumn);
        tablePanel.add(jbDelete);

        return tablePanel;
    }

    private void reloadTable() { //Recargar la tabla teniendo en cuenta los filtros y columnas a mostrar
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(addColumn.getCheckList());

        try {
            ResultSet consult = basedata.Consultar(filters);

            while (consult.next()) {
                ArrayList<String> row = new ArrayList<>();

                for (Object obj : addColumn.getCheckList()) {
                    row.add(consult.getString(obj.toString()));
                }

                tableModel.addRow(row.toArray());
            }
        } catch (SQLException e) {}
    }

    private JPanel getUserPanel() {
        RoundedPanel userPanel = new RoundedPanel();

        userPanel.setBounds(690, 40,310, 550);
        userPanel.setLayout(null);
        userPanel.setBackground(secundary);
        userPanel.setBorderColor(primary);

        JLabel userPhoto = new JLabel();
        userPhoto.setBounds(80, 30, 150, 150);
        userPhoto.setIcon(
            new ImageIcon(new ImageIcon(IMAGE_URL + "UsuarioLight.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))
        );
        userPhoto.setBackground(tertiary);
        userPhoto.setOpaque(true);

        JLabel userPhotoSelection = new JLabel() {
            protected void paintBorder(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                Area area = new Area(new Rectangle(-1, 0, getWidth()+2, getHeight()));
                area.subtract(new Area(new Ellipse2D.Float(0, 0, getWidth(), getHeight()-1)));

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(secundary);
                g2.fill(area);;

                g2.setColor(primary);
                g2.drawOval(0, 0, getWidth(), getHeight()-2);
            }
        };

        userPhotoSelection.setToolTipText("Cambiar imagen");
        userPhotoSelection.setBounds(80, 30, 150, 150);
        userPhotoSelection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                userPhotoSelection.setIcon(new ImageIcon("vacio"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                userPhotoSelection.setIcon(
                    new ImageIcon(new ImageIcon(IMAGE_URL + "camera_icon.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))
                );
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException| UnsupportedLookAndFeelException e1) {}

                JFileChooser file = new JFileChooser("C:/Users/" + System.getProperty("user.name"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "png", "jpg", "jpeg");

                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                file.addChoosableFileFilter(filter);            
                file.setFileFilter(filter);

                if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    userPhoto.setIcon(new ImageIcon(new ImageIcon(String.valueOf(file.getSelectedFile())).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
                    userPhotoSelection.repaint();
                }
            }
        });

        JButton buttonChage = new JButton("Aceptar");
        buttonChage.setBounds(30, 490, 120, 30);
        buttonChage.setForeground(secundary);
        buttonChage.setBackground(primary);
        buttonChage.setContentAreaFilled(true);
        buttonChage.setOpaque(true);

        JButton buttonCancel = new JButton("Cancelar");
        buttonCancel.setBounds(158, 490, 120, 30);
        buttonCancel.setForeground(secundary);
        buttonCancel.setBackground(primary);
        buttonCancel.setContentAreaFilled(true);
        buttonCancel.setOpaque(true);

        userPanel.add(userPhotoSelection);
        userPanel.add(userPhoto);
        userPanel.add(getUserInfo());
        userPanel.add(buttonChage);
        userPanel.add(buttonCancel);

        return userPanel;
    }

    private JPanel getUserInfo() {
        JPanel userInfo = new JPanel();
        userInfo.setLayout(null);
        userInfo.setBounds(30, 193, 250, 277);
        userInfo.setBackground(secundary);

        jtName.setBounds(0, 7, 250, 30);
        jtName.setBorderColor(primary, tertiary);

        jtCedula.setBounds(0, 47, 250, 30);
        jtCedula.setBorderColor(primary, tertiary);
        jtCedula.setMatchesString("\\d{10}");

        jtContact.setBounds(0, 87, 250, 30);
        jtContact.setBorderColor(primary, tertiary);
        jtContact.setMatchesString("\\d{10}");

        jtEmail.setBounds(0, 127, 250, 30);
        jtEmail.setBorderColor(primary, tertiary);
        jtEmail.setMatchesString("\\w+@\\w+.com");

        jtAdress.setBounds(0, 167, 250, 30);
        jtAdress.setBorderColor(primary, tertiary);

        jcGender.setBounds(0, 207, 120, 30);
        jcGender.setBackground(Color.WHITE);
        jcGender.setBorder(new LineBorder(tertiary, 2));

        jcRoles.setBounds(128, 207, 120, 30);
        jcRoles.setBackground(Color.WHITE);
        jcRoles.setBorder(new LineBorder(tertiary, 2));

        jsAge.setBounds(0, 247, 120, 30);
        jsAge.setBorder(new LineBorder(tertiary, 2));

        JLabel jlCode = new JLabel("0", SwingConstants.CENTER);
        jlCode.setBounds(128, 247, 100, 30);
        jlCode.setBorder(new LineBorder(tertiary, 2));
        jlCode.setForeground(primary);

        JButton jbCopy = new JButton("...");
        jbCopy.setBounds(228, 247, 20, 30);
        jbCopy.setBackground(primary);
        jbCopy.setForeground(secundary);
        jbCopy.setContentAreaFilled(true);
        jbCopy.setOpaque(true);

        DocumentListener changeData = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {funtion();}

            @Override
            public void removeUpdate(DocumentEvent e) {funtion();}

            @Override
            public void changedUpdate(DocumentEvent e) {funtion();}
            
            public void funtion() {
                System.out.println(userIsEmpy());
                jlCode.setText(String.valueOf(Math.abs((jtName.getText() +
                                                        jtCedula.getText() +
                                                        jtContact.getText() +
                                                        jtEmail.getText() +
                                                        jtAdress.getText()).hashCode())));
            }
        };

        jtName.getDocument().addDocumentListener(changeData);
        jtCedula.getDocument().addDocumentListener(changeData);
        jtContact.getDocument().addDocumentListener(changeData);
        jtEmail.getDocument().addDocumentListener(changeData);
        jtAdress.getDocument().addDocumentListener(changeData);

        jtName.addKeyListener(textfieldKey(null, jtCedula));
        jtCedula.addKeyListener(textfieldKey(jtName, jtContact));
        jtContact.addKeyListener(textfieldKey(jtCedula, jtEmail));
        jtEmail.addKeyListener(textfieldKey(jtContact, jtAdress));
        jtAdress.addKeyListener(textfieldKey(jtEmail, null));

        userInfo.add(jtName);
        userInfo.add(jtCedula);
        userInfo.add(jtContact);
        userInfo.add(jtEmail);
        userInfo.add(jtAdress);
        userInfo.add(jcGender);
        userInfo.add(jcRoles);
        userInfo.add(jsAge);
        userInfo.add(jlCode);
        userInfo.add(jbCopy);

        return userInfo;
    }

    private boolean userIsEmpy() {
        return !(jtName.getText().isEmpty() && 
               jtCedula.getText().isEmpty() && 
               jtContact.getText().isEmpty() && 
               jtEmail.getText().isEmpty() &&
               jtAdress.getText().isEmpty() &&
               jcGender.getSelectedItem().toString().equals("Genero") &&
               jcRoles.getSelectedItem().toString().equals("Roles"));
    }

    private void componentsEvent() {

    }

    private KeyAdapter textfieldKey(JTextField up, JTextField down) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) && down != null)
                    down.requestFocus();

                if (e.getKeyCode() == KeyEvent.VK_UP && up != null)
                    up.requestFocus();
            }
        };
    }
}