package Inventory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import Main.Mecanics;
import Objects.Archivo;
import Objects.Conexion;

public class invMecanics {
	
	public static void roundPanel(JPanel panel, int radious) {
		
		panel.setBorder(new RoundedBorder(radious));
	}
	
	public static void reporte1() {
		
		String[] len={ "Informacion de producto:", "Productos Disponibles:", "Codigo:", "Producto:", "Precio:",
				"Existencias:", "Unidad:", "Marca:", "Descripcion:", "Seleccionar Imagen", "Guardar", "Eliminar",
				"(ESP)", "Editar", "Vendido:", "Mas popular:", "Menos popular:", "Producto mas vendido: ",
				"Producto menos vendido: ", "Inventario" };
		
		if(Mecanics.getMode(true)==1) {
			
			len= new String[]{ "Product information:", "Available products:", "Code:", "Product:", "Price:", "Stock:",
					"Unit:", "Brand:", "Description:", "Select Image", "Save", "Delete", "(ENG)", "Edit", "Sold:",
					"Most popular:", "Least popular:", "Best selling product: ", "Least selling product: ", "Inventory" };
		}
		

		Path Downloads = Paths.get(System.getProperty("user.home"), "Downloads");
		String url = Downloads.toString() + "\\INV- " + LocalDate.now() + ".pdf";

		if (new File(url).exists()) {
			int i = 1;
			while (new File(url).exists()) {

				url = Downloads.toString() + "\\INV- " + LocalDate.now() + "(" + i + ").pdf";
				i++;
			}
		}

		try (PdfWriter pdfw = new PdfWriter(new File(url))) {

			PdfDocument pdfdoc = new PdfDocument(pdfw);
			pdfdoc.setDefaultPageSize(PageSize.LETTER);
			Document doc = new Document(pdfdoc);

			com.itextpdf.layout.element.Image Logo = new com.itextpdf.layout.element.Image(
					ImageDataFactory.create("./src/ResourcePackCaja/Inventario.png"));
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
				int i = 0;

				while (res.next()) {

					Archivo p = muestra(res.getString("Code"));

					tableta.addCell(new Paragraph(p.getCode()));
					tableta.addCell(new Paragraph(p.getProduct()));
					tableta.addCell(new Paragraph(p.getPrice() + "$"));
					tableta.addCell(new Paragraph(p.getAmount() + ""));
					tableta.addCell(new Paragraph(p.getUnid()));
					tableta.addCell(new Paragraph(p.getBrand()));
					tableta.addCell(new Paragraph(p.getSold() + ""));

					for (int j = 0; j <= 6; j++) {

						tableta.getCell(i + 1, j).setBorder(null).setFontSize(13)
								.setTextAlignment(TextAlignment.CENTER);
					}

					i++;
				}

			} catch (Exception e) {
			}

			Paragraph may = new Paragraph("\n" + len[17] + MayorMenor()[0].getCode() + ": " + MayorMenor()[0].getProduct());
			Title.setFontSize(20);
			Title.setBold();
			Title.setTextAlignment(TextAlignment.CENTER);
			Title.setVerticalAlignment(VerticalAlignment.MIDDLE);

			Paragraph men = new Paragraph("\n" + len[18] + MayorMenor()[1].getCode() + ": " + MayorMenor()[1].getProduct());
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

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public static Archivo muestra(String cod) {

		Archivo prod = null;

		try {
			Conexion a = new Conexion();
			ResultSet res = a.consulta("SELECT * FROM Inventario WHERE `Code`='" + cod + "'");

			prod = new Archivo(res.getString("Code"), res.getString("Product"), res.getString("Brand"),
					res.getString("Description"), res.getFloat("Amount"), res.getFloat("Sold"), res.getFloat("Price"),
					res.getString("Unid"), res.getString("Image"));

			res.close();

		} catch (Exception e) {
		}

		return prod;
	}

	public static Archivo[] MayorMenor() {

		Archivo[] prod = new Archivo[2];

		Conexion a = new Conexion();

		try {

			ResultSet res = a.consulta("SELECT * FROM Inventario");

			prod[0] = muestra(res.getString("Code"));
			prod[1] = muestra(res.getString("Code"));

			while (res.next()) {

				Archivo aux = muestra(res.getString("Code"));
				if (aux.getSold() > prod[0].getSold()) {
					prod[0] = aux;
				}

				if (aux.getSold() < prod[1].getSold()) {
					prod[1] = aux;
				}

			}

		} catch (Exception e) {
		}

		return prod;
	}

}

class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}