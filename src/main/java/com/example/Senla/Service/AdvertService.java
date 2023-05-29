package com.example.Senla.Service;

import java.util.List;

import com.example.Senla.DTO.AdvertDTO;
import com.example.Senla.DTO.FullAdvertDto;
import com.example.Senla.DTO.SearchAdvertDTO;
import com.example.Senla.DTO.ShortAdvertDTO;
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

  List<ShortAdvertDTO> searchAdverts(SearchAdvertDTO searchAdvertDTO);

  List<ShortAdvertDTO> getMySaleAdverts(String username);
}
