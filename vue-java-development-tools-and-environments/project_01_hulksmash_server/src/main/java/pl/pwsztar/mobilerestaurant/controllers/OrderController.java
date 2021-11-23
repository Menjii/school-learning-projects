package pl.pwsztar.mobilerestaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwsztar.mobilerestaurant.exceptions.ResourceNotFoundException;
import pl.pwsztar.mobilerestaurant.model.OrderDataDto;
import pl.pwsztar.mobilerestaurant.model.OrderDto;
import pl.pwsztar.mobilerestaurant.repositories.OrderRepository;
import pl.pwsztar.mobilerestaurant.security.services.UserDetailsImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/restaurant")
public class OrderController {

  @Autowired
  private OrderRepository orderRepository;

  //get address
  @GetMapping("/orders")
  public Object getAllOrders() {
    List<OrderDto> orders = this.orderRepository.findAll();
    return parseOrders(orders);
  }

  @GetMapping("/orders/actual")
  public Object getActualOrders() {
    List<OrderDto> orders = this.orderRepository.findActualOrders();
    return parseOrders(orders);
  }

  @GetMapping("/orders/user")
  public Object getActualOrders(@RequestParam("id") Long id) {
    List<OrderDto> orders = this.orderRepository.findUserOrders(id);
    return parseOrders(orders);
  }


  List<Object> parseOrders(List<OrderDto> orders) {
    List<Object> result = new ArrayList<>();
    for (OrderDto order : orders) {
      Map<String, Object> singleResult = new HashMap<>();
      Object orderAll = null;
      List<OrderDataDto> products = orderRepository.findAllProduct(order.getId());
      for(OrderDataDto data : products) {
        if( orderAll == null ) {
          orderAll = new OrderDto(data.getOrder());
        }
        data.setOrder(null);
      }
      singleResult.put("client", UserDetailsImpl.build(order.getClient()));
      singleResult.put("order", orderAll);
      singleResult.put("products", products);
      result.add(singleResult);
    }
    return result;
  }


  //get address by id
  @GetMapping("/order/{id}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable(value = "id") int orderId)
    throws ResourceNotFoundException {
    OrderDto orderDto = orderRepository.findById(orderId)
      .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id: " + orderId));
    return ResponseEntity.ok().body(orderDto);
  }

  //save address
  @PostMapping("/order")
  public OrderDto createOrder(@RequestBody OrderDto orderDto) {
    return this.orderRepository.save(orderDto);
  }

  //update address
  @PutMapping("/order/{id}")
  public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto order) {
    return ResponseEntity.ok(this.orderRepository.save(order));

  }
  //delete address

  @DeleteMapping("/order/{id}")
  public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") int orderId) throws ResourceNotFoundException {

    OrderDto orderDto = orderRepository.findById(orderId)
      .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id: " + orderId));

    this.orderRepository.delete(orderDto);

    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);

    return response;
  }
}
