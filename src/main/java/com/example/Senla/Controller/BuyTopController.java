package com.example.Senla.Controller;

import com.example.Senla.Service.AdvertService;
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
public class BuyTopController {

  @Autowired
  private final AdvertService advertService;

  @PatchMapping("/top/{advert_id}")
  public ResponseEntity<?> buyAdvertToTop(@PathVariable("advert_id") int advertId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    advertService.paidAdvertToTop(advertId, authentication.getName());
    return ResponseEntity.ok().build();
  }
}
