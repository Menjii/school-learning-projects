package pl.pwsztar.mobilerestaurant.utilities;

import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InvoiceGenerator {
  public static ByteArrayOutputStream generateTemplate(List<String> pdfPages, String title) {
    try {
      URL fontResourceURL = InvoiceGenerator.class.getResource("/templates/invoices/fonts/opensans.ttf");
      URL fontResourceURL2 = InvoiceGenerator.class.getResource("/templates/invoices/fonts/opensansb.ttf");

      ByteArrayOutputStream os = new ByteArrayOutputStream();

      ITextRenderer iTextRenderer = new ITextRenderer(300, 230);
      if (fontResourceURL != null) {
        iTextRenderer.getFontResolver().addFont(fontResourceURL.getPath(), BaseFont.IDENTITY_H,
          BaseFont.NOT_EMBEDDED);
      }
      if (fontResourceURL2 != null) {
        iTextRenderer.getFontResolver().addFont(fontResourceURL2.getPath(), BaseFont.IDENTITY_H,
          BaseFont.NOT_EMBEDDED);
      }
      iTextRenderer.setDocumentFromString(pdfPages.get(0));
      iTextRenderer.layout();
      iTextRenderer.createPDF(os, false);

      for (int i = 1; i < pdfPages.size(); i++) {
        iTextRenderer.setDocumentFromString(pdfPages.get(i));
        iTextRenderer.layout();
        iTextRenderer.writeNextDocument();
      }

      iTextRenderer.getOutputDevice().setMetadata("title", title);
      iTextRenderer.finishPDF();

      os.close();
      return os;
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String htmlToXhtml(String html, PdfInvoiceData invoiceData) {
    String cssValue = FileUtils.getFileContext("/templates/invoices/css/" + invoiceData.getStyleName() + ".css");
    String titleBuilder = "Faktura";
    StringBuilder tmpPath = new StringBuilder();
    tmpPath
      .append("invoices")
      .append("/")
      .append(invoiceData.getOrder().getId());

    File file = new File(tmpPath.toString());
    file.mkdirs();

    tmpPath.append("/")
      .append("faktura.pdf");

    file = new File(tmpPath.toString());


    InvoicePage invoicePage = new InvoicePage(html, cssValue, invoiceData);
    invoicePage.setTitle(titleBuilder);
    invoicePage.setUpData();

    List<String> tmpSchedule = new ArrayList<>();
    tmpSchedule.add(invoicePage.getHtml());

    try {
      OutputStream os = new FileOutputStream(file);
      ByteArrayOutputStream result = generateTemplate(tmpSchedule, titleBuilder);
      if (result != null) {
        os.write(result.toByteArray());
      }
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return invoicePage.getHtml();
  }
}
