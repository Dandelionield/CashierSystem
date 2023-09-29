package CashCashier;

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
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
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

        int m = getMode(true);
        int l = getLanguage(true);

        int Fila = 0;
        int z = 14;
        Color[] Fondo = {new Color(238, 248, 254), new Color(20, 35, 54)};
        String[] FacturaTitulo = {"Factura", "Check"};
        String[] ProductoTexto = {"Producto", "Product"};
        String[] CantidadTexto = {"Cantidad", "Amount"};
        String[] PrecioTexto = {"Precio", "Price"};
        String[] TotalTexto = {"Total", "Total"};
        String[] EmpleadoTexto = new String[2];

        int indice = Mecanics.getEmploye(p.getEmploye().getCode());

        if (indice != -1) {

            Trabajador d = Mecanics.Employe.get(indice);

            EmpleadoTexto[0] = "Empleado: " + d.getName() + " " + d.getLastName();
            EmpleadoTexto[1] = "Employe: " + d.getName() + " " + d.getLastName();

        }

        String[] ClienteTexto = {"Cliente: " + p.getClient().getID() + " " + p.getClient().getName(), "Client: " + p.getClient().getID() + " " + p.getClient().getName()};
        String[] PagoTexto = {"Pago: " + p.getPay() + "$", "Payment: " + p.getPay() + "$"};
        String[] VueltoTexto = {"Vuelto: " + p.getChange() + "$", "Vuelto: " + p.getChange() + "$"};
        String[] TotalPayTexto = {"Total a Pagar: " + p.getTotal() + "$", "Total Bill: " + p.getTotal() + "$"};

        JLabel[][] Label = new JLabel[p.getBuyout().length][4];

        JFrame frameFactura = new JFrame();

        frameFactura.setResizable(false);
        frameFactura.setTitle(FacturaTitulo[l]);
        frameFactura.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/ResourcePackCaja/Icono.png"));
        frameFactura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFactura.setBounds(200, 200, 450, 400 + (25 * p.getBuyout().length));
        frameFactura.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();

        contentPane.setBackground(Fondo[m]);

        JLabel Portada = new JLabel("");

        Portada.setBounds(170, 10, 100, 100);
        ImageIcon icono = new ImageIcon(new ImageIcon("./src/ResourcePackCaja/Icono.png").getImage().getScaledInstance(Portada.getWidth(), Portada.getHeight(), Image.SCALE_SMOOTH));

        Portada.setIcon(icono);
        Portada.setBackground(new Color(0, 0, 0, 0));
        Portada.setOpaque(true);

        JLabel Titulo = new JLabel(getTitle(true));

        if (m == 0) {

            Titulo.setForeground(Color.BLACK);

        } else if (m == 1) {

            Titulo.setForeground(Color.WHITE);

        }

        Titulo.setHorizontalAlignment(SwingConstants.CENTER);
        Titulo.setBackground(new Color(0, 0, 0, 0));
        Titulo.setOpaque(true);
        Titulo.setFont(new Font("Clarendon Blk BT", Font.BOLD, 20));
        Titulo.setBounds(17, 130, 400, 30);

        JLabel Direccion = new JLabel(getAddress(true));

        if (m == 0) {

            Direccion.setForeground(Color.BLACK);

        } else if (m == 1) {

            Direccion.setForeground(Color.WHITE);

        }

        Direccion.setHorizontalAlignment(SwingConstants.CENTER);
        Direccion.setBackground(new Color(0, 0, 0, 0));
        Direccion.setOpaque(true);
        Direccion.setFont(new Font("Clarendon Blk BT", Font.BOLD, 15));
        Direccion.setBounds(17, 165, 400, 30);

        JLabel Empleado = new JLabel(EmpleadoTexto[l]);

        if (m == 0) {

            Empleado.setForeground(Color.BLACK);

        } else if (m == 1) {

            Empleado.setForeground(Color.WHITE);

        }

        Empleado.setHorizontalAlignment(SwingConstants.LEFT);
        Empleado.setBackground(new Color(0, 0, 0, 0));
        Empleado.setOpaque(true);
        Empleado.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Empleado.setBounds(17, 210, 250, 20);

        JLabel Cliente = new JLabel(ClienteTexto[l]);

        if (m == 0) {

            Cliente.setForeground(Color.BLACK);

        } else if (m == 1) {

            Cliente.setForeground(Color.WHITE);

        }

        Cliente.setHorizontalAlignment(SwingConstants.LEFT);
        Cliente.setBackground(new Color(0, 0, 0, 0));
        Cliente.setOpaque(true);
        Cliente.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Cliente.setBounds(17, 240, 300, 20);

        JLabel Producto = new JLabel(ProductoTexto[l]);

        if (m == 0) {

            Producto.setForeground(Color.BLACK);

        } else if (m == 1) {

            Producto.setForeground(Color.WHITE);

        }

        Producto.setHorizontalAlignment(SwingConstants.CENTER);
        Producto.setBackground(new Color(0, 0, 0, 0));
        Producto.setOpaque(true);
        Producto.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Producto.setBounds(17, 280, 80, 20);

        JLabel Cantidad = new JLabel(CantidadTexto[l]);

        if (m == 0) {

            Cantidad.setForeground(Color.BLACK);

        } else if (m == 1) {

            Cantidad.setForeground(Color.WHITE);

        }

        Cantidad.setHorizontalAlignment(SwingConstants.CENTER);
        Cantidad.setBackground(new Color(0, 0, 0, 0));
        Cantidad.setOpaque(true);
        Cantidad.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Cantidad.setBounds(117, 280, 80, 20);

        JLabel Precio = new JLabel(PrecioTexto[l]);

        if (m == 0) {

            Precio.setForeground(Color.BLACK);

        } else if (m == 1) {

            Precio.setForeground(Color.WHITE);

        }

        Precio.setHorizontalAlignment(SwingConstants.CENTER);
        Precio.setBackground(new Color(0, 0, 0, 0));
        Precio.setOpaque(true);
        Precio.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Precio.setBounds(217, 280, 80, 20);

        JLabel Total = new JLabel(TotalTexto[l]);

        if (m == 0) {

            Total.setForeground(Color.BLACK);

        } else if (m == 1) {

            Total.setForeground(Color.WHITE);

        }

        Total.setHorizontalAlignment(SwingConstants.CENTER);
        Total.setBackground(new Color(0, 0, 0, 0));
        Total.setOpaque(true);
        Total.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Total.setBounds(317, 280, 80, 20);

        JLabel Pago = new JLabel(PagoTexto[l]);

        if (m == 0) {

            Pago.setForeground(Color.BLACK);

        } else if (m == 1) {

            Pago.setForeground(Color.WHITE);

        }

        Pago.setHorizontalAlignment(SwingConstants.LEFT);
        Pago.setBackground(new Color(0, 0, 0, 0));
        Pago.setOpaque(true);
        Pago.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Pago.setBounds(17, frameFactura.getHeight() - 80, 120, 20);

        JLabel Vuelto = new JLabel(VueltoTexto[l]);

        if (m == 0) {

            Vuelto.setForeground(Color.BLACK);

        } else if (m == 1) {

            Vuelto.setForeground(Color.WHITE);

        }

        Vuelto.setHorizontalAlignment(SwingConstants.LEFT);
        Vuelto.setBackground(new Color(0, 0, 0, 0));
        Vuelto.setOpaque(true);
        Vuelto.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Vuelto.setBounds(137, frameFactura.getHeight() - 80, 120, 20);

        JLabel TotalPagar = new JLabel(TotalPayTexto[l]);

        if (m == 0) {

            TotalPagar.setForeground(Color.BLACK);

        } else if (m == 1) {

            TotalPagar.setForeground(Color.WHITE);

        }

        TotalPagar.setHorizontalAlignment(SwingConstants.LEFT);
        TotalPagar.setBackground(new Color(0, 0, 0, 0));
        TotalPagar.setOpaque(true);
        TotalPagar.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        TotalPagar.setBounds(247, frameFactura.getHeight() - 80, 230, 20);

        JLabel Date = new JLabel(p.getDate());

        if (m == 0) {

            Date.setForeground(Color.BLACK);

        } else if (m == 1) {

            Date.setForeground(Color.WHITE);

        }

        Date.setHorizontalAlignment(SwingConstants.LEFT);
        Date.setBackground(new Color(0, 0, 0, 0));
        Date.setOpaque(true);
        Date.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Date.setBounds(0, 0, 150, 20);

        JLabel Codigo = new JLabel(p.getCode());

        if (m == 0) {

            Codigo.setForeground(Color.BLACK);

        } else if (m == 1) {

            Codigo.setForeground(Color.WHITE);

        }

        Codigo.setHorizontalAlignment(SwingConstants.RIGHT);
        Codigo.setBackground(new Color(0, 0, 0, 0));
        Codigo.setOpaque(true);
        Codigo.setFont(new Font("Clarendon Blk BT", Font.BOLD, 13));
        Codigo.setBounds(278, 0, 150, 20);

        contentPane.add(Titulo);
        contentPane.add(Portada);
        contentPane.add(Direccion);
        contentPane.add(Empleado);
        contentPane.add(Cliente);
        contentPane.add(Producto);
        contentPane.add(Cantidad);
        contentPane.add(Precio);
        contentPane.add(Total);
        contentPane.add(Pago);
        contentPane.add(Vuelto);
        contentPane.add(TotalPagar);
        contentPane.add(Date);
        contentPane.add(Codigo);

        contentPane.setLayout(null);
        contentPane.setComponentZOrder(Portada, 0);
        contentPane.setComponentZOrder(Titulo, 1);
        contentPane.setComponentZOrder(Direccion, 2);
        contentPane.setComponentZOrder(Empleado, 3);
        contentPane.setComponentZOrder(Cliente, 4);
        contentPane.setComponentZOrder(Producto, 5);
        contentPane.setComponentZOrder(Cantidad, 6);
        contentPane.setComponentZOrder(Precio, 7);
        contentPane.setComponentZOrder(Total, 8);
        contentPane.setComponentZOrder(Pago, 9);
        contentPane.setComponentZOrder(Vuelto, 10);
        contentPane.setComponentZOrder(TotalPagar, 11);
        contentPane.setComponentZOrder(Date, 12);
        contentPane.setComponentZOrder(Codigo, 13);

        for (Object[] q : p.getBuyout()) {

            Label[Fila][0] = new JLabel(q[1].toString());

            if (m == 0) {

                Label[Fila][0].setForeground(Color.BLACK);

            } else if (m == 1) {

                Label[Fila][0].setForeground(Color.WHITE);

            }

            Label[Fila][0].setHorizontalAlignment(SwingConstants.LEFT);
            Label[Fila][0].setBackground(new Color(0, 0, 0, 0));
            Label[Fila][0].setOpaque(true);
            Label[Fila][0].setFont(new Font("Clarendon Blk BT", Font.PLAIN, 11));
            Label[Fila][0].setBounds(17, 310 + (20 * Fila), 100, 20);

            Label[Fila][1] = new JLabel("");

            for (Archivo b : Archive) {

                if (q[0].toString().equalsIgnoreCase(b.getCode()) == true) {

                    Label[Fila][1].setText(q[2].toString() + " " + b.getUnid());

                    break;

                }

            }

            if (m == 0) {

                Label[Fila][1].setForeground(Color.BLACK);

            } else if (m == 1) {

                Label[Fila][1].setForeground(Color.WHITE);

            }

            Label[Fila][1].setHorizontalAlignment(SwingConstants.LEFT);
            Label[Fila][1].setBackground(new Color(0, 0, 0, 0));
            Label[Fila][1].setOpaque(true);
            Label[Fila][1].setFont(new Font("Clarendon Blk BT", Font.PLAIN, 12));
            Label[Fila][1].setBounds(138, 310 + (20 * Fila), 80, 20);

            Label[Fila][2] = new JLabel(q[3].toString());

            if (m == 0) {

                Label[Fila][2].setForeground(Color.BLACK);

            } else if (m == 1) {

                Label[Fila][2].setForeground(Color.WHITE);

            }

            Label[Fila][2].setHorizontalAlignment(SwingConstants.LEFT);
            Label[Fila][2].setBackground(new Color(0, 0, 0, 0));
            Label[Fila][2].setOpaque(true);
            Label[Fila][2].setFont(new Font("Clarendon Blk BT", Font.PLAIN, 12));
            Label[Fila][2].setBounds(234, 310 + (20 * Fila), 80, 20);

            Label[Fila][3] = new JLabel(q[4].toString());

            if (m == 0) {

                Label[Fila][3].setForeground(Color.BLACK);

            } else if (m == 1) {

                Label[Fila][3].setForeground(Color.WHITE);

            }

            Label[Fila][3].setHorizontalAlignment(SwingConstants.CENTER);
            Label[Fila][3].setBackground(new Color(0, 0, 0, 0));
            Label[Fila][3].setOpaque(true);
            Label[Fila][3].setFont(new Font("Clarendon Blk BT", Font.PLAIN, 12));
            Label[Fila][3].setBounds(317, 310 + (20 * Fila), 80, 20);

            contentPane.add(Label[Fila][0]);
            contentPane.add(Label[Fila][1]);
            contentPane.add(Label[Fila][2]);
            contentPane.add(Label[Fila][3]);

            contentPane.setComponentZOrder(Label[Fila][0], z);
            z++;
            contentPane.setComponentZOrder(Label[Fila][1], z);
            z++;
            contentPane.setComponentZOrder(Label[Fila][2], z);
            z++;
            contentPane.setComponentZOrder(Label[Fila][3], z);
            z++;

            Label[Fila][0].setVisible(true);
            Label[Fila][1].setVisible(true);
            Label[Fila][2].setVisible(true);
            Label[Fila][3].setVisible(true);

            Fila++;

        }

        Titulo.setVisible(true);
        Portada.setVisible(true);
        Direccion.setVisible(true);
        Empleado.setVisible(true);
        Cliente.setVisible(true);
        Producto.setVisible(true);
        Cantidad.setVisible(true);
        Precio.setVisible(true);
        Total.setVisible(true);
        Pago.setVisible(true);
        Vuelto.setVisible(true);
        TotalPagar.setVisible(true);
        Date.setVisible(true);
        Codigo.setVisible(true);

        frameFactura.setContentPane(contentPane);

        frameFactura.setVisible(true);

        SwingUtilities.invokeLater(() -> {

            JFrame Run = frameFactura;

            try {

                Thread.sleep(1000);
                JFrameToPNG(frameFactura, p.getCode() + ".png");

            } catch (InterruptedException | AWTException | IOException e) {

                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);

            }

        });

    }

    private static void JFrameToPNG(JFrame frame, String fileName) throws AWTException, IOException {

        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BufferedImage image = robot.createScreenCapture(new Rectangle(screenSize));
        BufferedImage frameImage = image.getSubimage(frame.getLocationOnScreen().x + 8, frame.getLocationOnScreen().y + 32, frame.getWidth() - 20, frame.getHeight() - 45);

        File file = new File(fileName);
        ImageIO.write(frameImage, "png", file);

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
