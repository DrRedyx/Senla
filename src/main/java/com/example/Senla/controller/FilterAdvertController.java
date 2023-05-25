package com.example.Senla.controller;

import java.util.Set;

import com.example.Senla.dto.ShortAdvertDTO;
import com.example.Senla.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ilyas Nigamatullin
 */
@RestController
@RequestMapping("/filter")
@AllArgsConstructor
public class FilterAdvertController {

  @Autowired
  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Set<ShortAdvertDTO>> filterByCategory(@RequestParam String filter) {
    return ResponseEntity.ok(categoryService.filterByCategory(filter));
  }
}
