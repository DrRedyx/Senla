package com.example.Senla.Controller;

import java.util.List;

import com.example.Senla.DTO.SearchAdvertDTO;
import com.example.Senla.DTO.ShortAdvertDTO;
import com.example.Senla.Service.AdvertService;
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
@RequestMapping("/search")
@AllArgsConstructor
public class SearchAdvertController {

  @Autowired
  private final AdvertService advertService;

  @GetMapping()
  public ResponseEntity<List<ShortAdvertDTO>> searchAdverts(
      @RequestBody SearchAdvertDTO searchAdvertDTO) {
    return ResponseEntity.ok(advertService.searchAdverts(searchAdvertDTO));
  }
}
