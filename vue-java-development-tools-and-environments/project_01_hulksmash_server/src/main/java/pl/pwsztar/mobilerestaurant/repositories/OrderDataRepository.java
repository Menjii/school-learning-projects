package pl.pwsztar.mobilerestaurant.repositories;

import org.springframework.data.jpa.repository.Query;
import pl.pwsztar.mobilerestaurant.model.FoodDto;
import pl.pwsztar.mobilerestaurant.model.OrderDataDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwsztar.mobilerestaurant.model.OrderDto;

@Repository
public interface OrderDataRepository extends JpaRepository<OrderDataDto,Integer> {
  @Query("SELECT o FROM OrderDto o WHERE o.id = ?1")
  OrderDto findOrderById(int orderId);

  @Query("SELECT f FROM FoodDto f WHERE f.id = ?1")
  FoodDto findFood(int foodId);
}
