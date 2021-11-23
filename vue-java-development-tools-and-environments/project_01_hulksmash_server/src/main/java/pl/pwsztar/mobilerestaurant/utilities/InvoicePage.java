package pl.pwsztar.mobilerestaurant.utilities;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.pwsztar.mobilerestaurant.model.OrderDataDto;
import pl.pwsztar.mobilerestaurant.model.User;

public class InvoicePage {
  private PdfInvoiceData invoiceData;
  private Document document;


  public InvoicePage(String html, String css, PdfInvoiceData scheduleData) {
    this.document = Jsoup.parse(html);
    this.invoiceData = scheduleData;

    document.select("style.main-style").html(css);
    document.select(".invoice-id").html(String.valueOf(scheduleData.getOrder().getId()));
    document.select(".date").html(scheduleData.getOrder().getPaymentData().getPaymentData());
    document.select(".client-data").html(getUserData());
    document.select(".invoice-data").html(getInvoiceData());

  }

  public void savePageLocally() {

  }

  public String getUserData() {
    User user = invoiceData.getOrder().getClient();
    return user.getName() + " " + user.getSurname() + "<br />" +
      user.getAddress().getStreet() + ", " + user.getAddress().getHomeNumber() + "<br />" +
      user.getAddress().getPostNumber() + "-" + user.getAddress().getCity();
  }

  public String getInvoiceData() {
    StringBuilder sb = new StringBuilder();
    int sum = 0;
    sb.append("<tr><th class=\"w-40\">Produkt</th><th>Ilosc</th><th>Cena jednostkowa</th></tr>");
    for (OrderDataDto order : invoiceData.getOrderData()) {
      sum += order.getCount() * order.getFood().getPrize();
      sb.append("<tr>");
      sb.append("<td>").append(order.getFood().getName()).append("</td>");
      sb.append("<td>").append(order.getCount()).append("</td>");
      sb.append("<td>").append(order.getFood().getPrize()).append("zł </td>");
      sb.append("</tr>");
    }
    sb.append("<tr>")
      .append("<th colspan=\"2\">RAZEM</th><td>")
    .append(sum).append("zł </td>");
    return sb.toString();
  }

  public void setTitle(String title) {
    document.title(title);
  }

  public String getHtml() {
    return document.html();
  }

  public void setUpData() {

    document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
  }
}
