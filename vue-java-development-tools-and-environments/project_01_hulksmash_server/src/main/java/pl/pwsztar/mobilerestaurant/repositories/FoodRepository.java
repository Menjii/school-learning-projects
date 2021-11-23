package pl.pwsztar.mobilerestaurant.repositories;

import org.springframework.data.jpa.repository.Query;
import pl.pwsztar.mobilerestaurant.model.FoodDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodDto, Integer> {
  @Query("SELECT f FROM FoodDto f WHERE f.categoryDto.id = ?1")
  List<FoodDto> findByCategoryId(int categoryID);
}
