package pl.pwsztar.mobilerestaurant.controllers;

import pl.pwsztar.mobilerestaurant.exceptions.ResourceNotFoundException;
import pl.pwsztar.mobilerestaurant.repositories.CategoryRepository;
import pl.pwsztar.mobilerestaurant.model.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/restaurant")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    //get address
    @GetMapping("/category")
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    //get address by id
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(value = "id") int categoryId)
            throws ResourceNotFoundException {
        CategoryDto categoryDto = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id: " + categoryId));
        return ResponseEntity.ok().body(categoryDto);
    }

    //save address
    @PostMapping("/category")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return this.categoryRepository.save(categoryDto);
    }

    //update address
    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryDto> updateCategory( @RequestBody CategoryDto kategoriaDetails) {

        return ResponseEntity.ok(this.categoryRepository.save(kategoriaDetails));

    }
    //delete address

    @DeleteMapping("/category/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") int categoryId) throws ResourceNotFoundException {

        CategoryDto categoryDto = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id: " + categoryId));

        this.categoryRepository.delete(categoryDto);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
