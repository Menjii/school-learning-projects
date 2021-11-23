package pl.pwsztar.mobilerestaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwsztar.mobilerestaurant.exceptions.ResourceNotFoundException;
import pl.pwsztar.mobilerestaurant.model.MailMessage;
import pl.pwsztar.mobilerestaurant.model.OrderDataDto;
import pl.pwsztar.mobilerestaurant.model.OrderDto;
import pl.pwsztar.mobilerestaurant.repositories.OrderDataRepository;
import pl.pwsztar.mobilerestaurant.utilities.FileUtils;
import pl.pwsztar.mobilerestaurant.utilities.InvoiceGenerator;
import pl.pwsztar.mobilerestaurant.utilities.MailService;
import pl.pwsztar.mobilerestaurant.utilities.PdfInvoiceData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/restaurant")
public class OrderDataController {

  @Autowired
  private OrderDataRepository orderDataRepository;

  //get address
  @GetMapping("/order-data")
  public List<OrderDataDto> getAllOrderData() {
    return this.orderDataRepository.findAll();
  }


  //get address by id
  @GetMapping("/order-data/{id}")
  public ResponseEntity<OrderDataDto> getOrderDataByOrderId(@PathVariable(value = "id") int id_zamowienia)
    throws ResourceNotFoundException {
    OrderDataDto orderDataDto = orderDataRepository.findById(id_zamowienia)
      .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id: " + id_zamowienia));
    return ResponseEntity.ok().body(orderDataDto);
  }

  //save address
  @PostMapping("/order-data")
  public OrderDataDto createOrderData(@RequestBody OrderDataDto orderDataDto) {
    return this.orderDataRepository.save(orderDataDto);
  }

  @PostMapping("/order-data/all")
  public List<OrderDataDto> createFullOrder(@RequestBody List<OrderDataDto> orderDataDto) {
    OrderDto order = orderDataRepository.findOrderById(orderDataDto.get(0).getOrder().getId());
    List<OrderDataDto> orders = this.orderDataRepository.saveAll(orderDataDto);
    for( OrderDataDto orderDto : orders ) {
      orderDto.setFood(orderDataRepository.findFood(orderDto.getFood().getId()));
    }
    System.out.println(orders);
    List<String> recipients = new ArrayList<>();
    recipients.add(order.getClient().getEmail());
    MailMessage mailMessage = new MailMessage();
    mailMessage.setSubject("Faktura za zamówienie");
    mailMessage.setText("test");
    mailMessage.setRecipients(recipients);
    String html = FileUtils.getFileContext("/templates/invoices/inv001.html");
    List<String> pdfPages = new ArrayList<>();
    PdfInvoiceData pdfInvoiceData = new PdfInvoiceData();
    pdfInvoiceData.setStyleName("inv001");
    pdfInvoiceData.setOrderData(orders);
    pdfInvoiceData.setOrder(order);
    pdfInvoiceData.setTitle("a");
    pdfPages.add(InvoiceGenerator.htmlToXhtml(html, pdfInvoiceData));
    mailMessage.setFile(InvoiceGenerator.generateTemplate(pdfPages, "Faktura"));
    MailService.sendMessage(mailMessage);
    System.out.println("finish");
    return orders;
  }

  //update address
  @PutMapping("/order-data/{id}")
  public ResponseEntity<OrderDataDto> updateOrderData(@RequestBody OrderDataDto orderData) throws ResourceNotFoundException {
    return ResponseEntity.ok(this.orderDataRepository.save(orderData));

  }

  //delete address
  @DeleteMapping("/order-data/{id}")
  public Map<String, Boolean> deleteOrderData(@PathVariable(value = "id") int id_zamowienia) throws ResourceNotFoundException {

    OrderDataDto orderDataDto = orderDataRepository.findById(id_zamowienia)
      .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id: " + id_zamowienia));

    this.orderDataRepository.delete(orderDataDto);

    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);

    return response;
  }
}
