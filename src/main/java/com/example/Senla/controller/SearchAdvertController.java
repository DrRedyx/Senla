package com.example.Senla.controller;

import java.util.List;

import com.example.Senla.dto.ShortAdvertDTO;
import com.example.Senla.service.AdvertService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ilyas Nigamatullin
 */

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchAdvertController {

  @Autowired
  private final AdvertService advertService;

  @GetMapping()
  public ResponseEntity<List<ShortAdvertDTO>> searchAdverts(
      @RequestParam String search) {
    return ResponseEntity.ok(advertService.searchAdverts(search));
  }
}
