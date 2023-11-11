package Employees;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.IOException;

public class Test {
  public static void main(String[] args) {
    String file = "sample_pdf.pdf";
    try {
        createPdf(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  private static void createPdf(String file) throws IOException {
    PdfWriter writer = new PdfWriter(file);
    PdfDocument pdfDoc = new PdfDocument(writer);
    Document doc = new Document(pdfDoc);

    PdfFont myFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

    Paragraph p1 = new Paragraph();
    p1.add("Hello, This is Delftstack!");
    p1.setTextAlignment(TextAlignment.CENTER);
    p1.setFont(myFont);
    p1.setFontSize(28);
    doc.add(p1);

    Paragraph p2 = new Paragraph();
    p2.add("We help you understand the concepts.");
    p2.setFontSize(18);
    doc.add(p2);

    doc.close();
  }
}
