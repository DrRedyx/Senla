package com.example.Senla.service;

import java.util.List;

import com.example.Senla.dto.AdvertDTO;
import com.example.Senla.dto.FullAdvertDto;
import com.example.Senla.dto.ShortAdvertDTO;
import org.springframework.stereotype.Service;

/**
 * @author Ilyas Nigamatullin
 */
@Service
public interface AdvertService {

  void saveAdvert(AdvertDTO advertDTO, String username);

  void updateAdvert(int id, AdvertDTO advertDTO, String username);

  List<ShortAdvertDTO> getAllAdverts();

  void deleteAdvert(int id, String username);

  FullAdvertDto getAdvert(int id);

  void saleAdvert(int id, String username);

  void paidAdvertToTop(int id, String username);

  List<ShortAdvertDTO> getAllMyAdvert(String username);

  List<ShortAdvertDTO> searchAdverts(String search);


}
