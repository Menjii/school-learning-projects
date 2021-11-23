package pl.pwsztar.mobilerestaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pwsztar.mobilerestaurant.model.PromotionDto;
import pl.pwsztar.mobilerestaurant.repositories.PromotionRepository;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/restaurant")
public class PromotionController {

  @Autowired
  private PromotionRepository promotionRepository;

  //get address
  @GetMapping("/promotion")
  public Optional<PromotionDto> getPromotionById(@RequestParam("id") int id) {
    Optional<PromotionDto> test = this.promotionRepository.findById(id);
    return test;
  }

  @GetMapping("/promotionStatus")
  public PromotionDto changePromotionStatus(@RequestParam("id") int id) {
    PromotionDto test = this.promotionRepository.findById(id).get();
    test.setActive(!test.isActive());
    this.promotionRepository.save(test);
    return test;
  }
}
