package com.example.Senla.mapper;

import java.util.List;
import java.util.Set;

import com.example.Senla.dto.AdvertDTO;
import com.example.Senla.dto.FullAdvertDto;
import com.example.Senla.dto.ShortAdvertDTO;
import com.example.Senla.entity.Advert;
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
