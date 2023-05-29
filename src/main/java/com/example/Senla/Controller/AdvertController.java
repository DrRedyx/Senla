package com.example.Senla.Controller;

import java.util.List;

import com.example.Senla.DTO.FullAdvertDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import com.example.Senla.DTO.AdvertDTO;
import com.example.Senla.DTO.ShortAdvertDTO;
import com.example.Senla.Service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ilyas Nigamatullin
 */
@Tag(name = "Work with adverts")
@RestController
@RequestMapping("/adverts")
public class AdvertController {

  @Autowired
  private final AdvertService advertService;

  public AdvertController(AdvertService advertService) {
    this.advertService = advertService;
  }

  @Operation(summary = "Create new advert")
  @PostMapping(path = "/add")
  public ResponseEntity<AdvertDTO> saveAdvert(@RequestBody AdvertDTO advertDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    advertService.saveAdvert(advertDTO, authentication.getName());
    return ResponseEntity.ok(advertDTO);
  }

  @GetMapping
  @Operation(summary = "Get all adverts sorted by user grade")
  public ResponseEntity<List<ShortAdvertDTO>> getAllAdverts() {
    return ResponseEntity.ok(advertService.getAllAdverts());
  }

  @PatchMapping(path = "/{id}/update")
  @Operation(summary = "Upgrade your advert")
  public ResponseEntity<AdvertDTO> updateAdvert(@PathVariable int id, @RequestBody AdvertDTO advertDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    advertService.updateAdvert(id, advertDTO, authentication.getName());
    return ResponseEntity.ok(advertDTO);
  }

  @DeleteMapping(path = "delete/{id}")
  @Operation(summary = "Delete ypur advert")
  public ResponseEntity<?> deleteAdvert(@PathVariable int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    advertService.deleteAdvert(id, authentication.getName());
    return ResponseEntity.ok("Объявление успешно удалено");
  }

  @GetMapping("/my")
  @Operation(summary = "Get all my create advert")
  public ResponseEntity<List<ShortAdvertDTO>> getAllMyAdverts() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(advertService.getAllMyAdvert(authentication.getName()));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get advert by id")
  public ResponseEntity<FullAdvertDto> getAdvert(@PathVariable int id) {
    return ResponseEntity.ok(advertService.getAdvert(id));
  }

}
