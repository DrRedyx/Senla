package com.example.Senla.Controller;

import java.util.List;

import com.example.Senla.DTO.FullAdvertDto;
import org.springframework.data.domain.PageRequest;
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
@RestController
@RequestMapping("/adverts")
public class AdvertController {

  @Autowired
  private final AdvertService advertService;

  public AdvertController(AdvertService advertService) {
    this.advertService = advertService;
  }

  @PostMapping(path = "/add")
  public ResponseEntity<AdvertDTO> saveAdvert(@RequestBody AdvertDTO advertDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    advertService.saveAdvert(advertDTO, authentication.getName());
    return ResponseEntity.ok(advertDTO);
  }

  @GetMapping
  public ResponseEntity<List<ShortAdvertDTO>> getAllAdverts(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return ResponseEntity.ok(advertService.getAllAdverts(PageRequest.of(page, size)));
  }

  @PatchMapping(path = "/{id}/update")
  public ResponseEntity<AdvertDTO> updateAdvert(@PathVariable int id, @RequestBody AdvertDTO advertDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    advertService.updateAdvert(id, advertDTO, authentication.getName());
    return ResponseEntity.ok(advertDTO);
  }

  @DeleteMapping(path = "delete/{id}")
  public ResponseEntity<?> deleteAdvert(@PathVariable int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    advertService.deleteAdvert(id, authentication.getName());
    return ResponseEntity.ok("Объявление успешно удалено");
  }

  @GetMapping("/my")
  public ResponseEntity<List<ShortAdvertDTO>> getAllMyAdverts() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(advertService.getAllMyAdvert(authentication.getName()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<FullAdvertDto> getAdvert(@PathVariable int id) {
    return ResponseEntity.ok(advertService.getAdvert(id));
  }

}
