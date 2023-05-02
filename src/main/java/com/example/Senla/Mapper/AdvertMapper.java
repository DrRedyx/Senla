package com.example.Senla.Mapper;

import java.util.List;
import java.util.Set;

import com.example.Senla.DTO.AdvertDTO;
import com.example.Senla.DTO.FullAdvertDto;
import com.example.Senla.DTO.ShortAdvertDTO;
import com.example.Senla.Entity.Advert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


/**
 * @author Ilyas Nigamatullin
 */
@Mapper(componentModel = "spring")
@Component
public interface AdvertMapper {

  Advert createAdvertDTOToEntity(AdvertDTO advertDTO);

  AdvertDTO entityToDTO(Advert advert);

  List<ShortAdvertDTO> advertEntityListToShortAdvertDTOList(List<Advert> adverts);
  Set<ShortAdvertDTO> advertEntitySetToShortAdvertDTOSet(Set<Advert> adverts);

  @Mapping(source = "person.firstName", target = "ownerFirstName")
  @Mapping(source = "person.lastName", target = "ownerLastName")
  FullAdvertDto entityToFullAdvertDTO(Advert advert);
}
