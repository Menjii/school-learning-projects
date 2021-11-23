package pl.pwsztar.mobilerestaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwsztar.mobilerestaurant.model.PromotionDto;
import pl.pwsztar.mobilerestaurant.model.WorkerDto;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionDto,Integer> {
}
