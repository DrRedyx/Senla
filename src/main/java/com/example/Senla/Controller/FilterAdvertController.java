package com.example.Senla.Controller;

import java.util.Set;

import com.example.Senla.DTO.FilterCategoryDTO;
import com.example.Senla.DTO.ShortAdvertDTO;
import com.example.Senla.Service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ilyas Nigamatullin
 */
@RestController
@RequestMapping("/filter")
@AllArgsConstructor
@Tag(name = "Filter by category")
public class FilterAdvertController {

  @Autowired
  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Set<ShortAdvertDTO>> filterByCategory(@RequestBody FilterCategoryDTO filterCategoryDTO) {
    return ResponseEntity.ok(categoryService.filterByCategory(filterCategoryDTO));
  }
}
