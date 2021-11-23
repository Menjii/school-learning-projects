package pl.pwsztar.mobilerestaurant.utilities;

import pl.pwsztar.mobilerestaurant.model.OrderDataDto;
import pl.pwsztar.mobilerestaurant.model.OrderDto;

import java.util.List;
import java.util.Map;

public class PdfInvoiceData {
  String styleName;
  String title;
  OrderDto order;
  List<OrderDataDto> orderData;

  public PdfInvoiceData() {
  }

  public String getStyleName() {
    return styleName;
  }

  public OrderDto getOrder() {
    return order;
  }

  public void setOrder(OrderDto order) {
    this.order = order;
  }

  public void setStyleName(String styleName) {
    this.styleName = styleName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<OrderDataDto> getOrderData() {
    return orderData;
  }

  public void setOrderData(List<OrderDataDto> orderData) {
    this.orderData = orderData;
  }
}
