package pl.pwsztar.mobilerestaurant.controllers;

import pl.pwsztar.mobilerestaurant.exceptions.ResourceNotFoundException;
import pl.pwsztar.mobilerestaurant.repositories.FoodRepository;
import pl.pwsztar.mobilerestaurant.model.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/restaurant")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    //get address
    @GetMapping("/food")
    public List<FoodDto> getAllProducts() {
        return this.foodRepository.findAll();
    }

    @GetMapping("/foodByCategory")
    public List<FoodDto> getAllProductsByCategory(@RequestParam("categoryId") int categoryId) {
        return this.foodRepository.findByCategoryId(categoryId);
    }

    //get address by id
    @GetMapping("/food/{id}")
    public ResponseEntity<FoodDto> getProductById(@PathVariable(value = "id") int id_dania)
            throws ResourceNotFoundException {
        FoodDto foodDto = foodRepository.findById(id_dania)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id: " + id_dania));
        return ResponseEntity.ok().body(foodDto);
    }

    //save address
    @PostMapping("/food")
    public FoodDto createProduct(@RequestBody FoodDto foodDto) {
        return this.foodRepository.save(foodDto);
    }

    //update address
    @PutMapping("/food/{id}")
    public ResponseEntity<FoodDto> updateProduct(@RequestBody FoodDto danieDetails) {

        return ResponseEntity.ok(this.foodRepository.save(danieDetails));

    }
    //delete address

    @DeleteMapping("/food/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") int id_dania) throws ResourceNotFoundException {

        FoodDto foodDto = foodRepository.findById(id_dania)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id: " + id_dania));

        this.foodRepository.delete(foodDto);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
