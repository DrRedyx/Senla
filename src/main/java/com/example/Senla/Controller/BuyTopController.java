package com.example.Senla.Controller;

import com.example.Senla.Service.AdvertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ilyas Nigamatullin
 */

@RestController
@RequestMapping("/buy")
@AllArgsConstructor
@Tag(name = "Top to advert")
public class BuyTopController {

  @Autowired
  private final AdvertService advertService;

  @PatchMapping("/top/{advert_id}")
  @Operation(summary = "Buy advert to top")
  public ResponseEntity<?> buyAdvertToTop(@PathVariable("advert_id") int advertId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    advertService.paidAdvertToTop(advertId, authentication.getName());
    return ResponseEntity.ok("Спасибо за покупку продвижения");
  }
}
