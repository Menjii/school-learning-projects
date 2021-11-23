package pl.pwsztar.mobilerestaurant.repositories;

import org.springframework.data.jpa.repository.Query;
import pl.pwsztar.mobilerestaurant.model.OrderDataDto;
import pl.pwsztar.mobilerestaurant.model.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDto,Integer> {

  @Query("SELECT od FROM OrderDataDto od WHERE od.order.id = ?1")
  List<OrderDataDto> findAllProduct(int id);

  @Query("SELECT o FROM OrderDto o WHERE o.realizationStatus = 'ZAPŁACONE' OR o.realizationStatus = 'W TRAKCIE REALIZACJI' OR o.realizationStatus = 'W DOSTAWIE'")
  List<OrderDto> findActualOrders();

  @Query("SELECT o FROM OrderDto o WHERE o.client.id = ?1")
  List<OrderDto> findUserOrders(Long userId);
}
