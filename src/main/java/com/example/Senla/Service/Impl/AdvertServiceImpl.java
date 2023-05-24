package com.example.Senla.Service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.Senla.DTO.AdvertDTO;
import com.example.Senla.DTO.FullAdvertDto;
import com.example.Senla.DTO.SearchAdvertDTO;
import com.example.Senla.DTO.ShortAdvertDTO;
import com.example.Senla.Entity.Advert;
import com.example.Senla.Entity.Category;
import com.example.Senla.Entity.Person;
import com.example.Senla.Exception.AccessDenied;
import com.example.Senla.Exception.AdvertNotFoundException;
import com.example.Senla.Exception.UserNotFoundException;
import com.example.Senla.Mapper.AdvertMapper;
import com.example.Senla.Repository.AdvertRepo;
import com.example.Senla.Repository.PersonRepo;
import com.example.Senla.Service.AdvertService;
import com.example.Senla.Service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ilyas Nigamatullin
 */
@Service
public class AdvertServiceImpl implements AdvertService {

  Logger logger = LoggerFactory.getLogger(AdvertServiceImpl.class);

  private final AdvertRepo advertRepo;
  private final AdvertMapper advertMapper;
  private final CategoryService categoryService;
  private final PersonRepo personRepo;

  @Autowired
  public AdvertServiceImpl(AdvertRepo advertRepo, AdvertMapper advertMapper, CategoryService categoryService, PersonRepo personRepo) {
    this.advertMapper = advertMapper;
    this.advertRepo = advertRepo;
    this.categoryService = categoryService;
    this.personRepo = personRepo;
  }


  @Transactional
  @Override
  public void saveAdvert(AdvertDTO advertDTO, String username) {
    logger.info("Save advert");
    Advert saveAdvert = advertMapper.createAdvertDTOToEntity(advertDTO);
    Person person = personRepo.findByUsername(username).get();
    String categoryName = advertDTO.getCategory();
    saveAdvert.setActual(true);
    saveAdvert.setPerson(person);
    setAdvertCategory(saveAdvert, categoryName);
    advertRepo.save(saveAdvert);
  }

  @Transactional
  @Override
  public void updateAdvert(int id, AdvertDTO advertDTO, String username) {
    Advert updateAdvert = advertRepo.findById(id).orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
    if (username.equals(updateAdvert.getPerson().getUsername())) {
      logger.info("Update advert");
      updateAdvert.setPrice(advertDTO.getPrice());
      updateAdvert.setTitle(advertDTO.getTitle());
      updateAdvert.setDescription(advertDTO.getDescription());
      String categoryName = advertDTO.getCategory();
      setAdvertCategory(updateAdvert, categoryName);
      advertRepo.save(updateAdvert);
    }
    else {
      throw new AccessDenied("Это не ваше объявление");
    }
  }

  public void setAdvertCategory(Advert advert, String categoryName) {
    Category category = categoryService.addCategory(categoryName, advert);
    Set<Category> categorySet = advert.getCategorySet() == null ? new HashSet<>() : advert.getCategorySet();
    categorySet.add(category);
    advert.setCategorySet(categorySet);
  }

  @Transactional(readOnly = true)
  @Override
  public List<ShortAdvertDTO> getAllAdverts() {
    logger.info("Get adverts");
    return advertMapper.advertEntityListToShortAdvertDTOList(advertRepo.getAllActualAdverts());
  }
  @Transactional
  @Override
  public void deleteAdvert(int id, String username) {
    Advert advert = advertRepo.findById(id).orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
    if (advert.getPerson().getUsername().equals(username)) {
      advertRepo.deleteById(id);
    }
    else {
      throw new AccessDenied("Это не ваше объявление");
    }
  }

  @Transactional
  @Override
  public FullAdvertDto getAdvert(int id){
    Advert advert = advertRepo.findById(id).orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
    logger.info("Get Advert");
    return advertMapper.entityToFullAdvertDTO(advert);
  }

  @Transactional
  @Override
  public void saleAdvert(int id, String username) {
    Advert advert = advertRepo.findById(id).orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
    if (!advert.getPerson().getUsername().equals(username)) {
      logger.info("Thank`s");
      advert.setSale(true);
      advertRepo.save(advert);
    }
    else {
      throw new AccessDenied("Это не ваше объявление");
    }
  }

  @Transactional
  @Override
  public void paidAdvertToTop(int id, String username) {
    Advert advert = advertRepo.findById(id).orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
    if (advert.getPerson().getUsername().equals(username)) {
      logger.info("Thank`s");
      advert.setPaid(true);
      advertRepo.save(advert);
    }
    else {
      throw new AccessDenied("Это не ваше объявление");
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<ShortAdvertDTO> getAllMyAdvert(String username) {
    logger.info("Get my adverts");
    Person person = personRepo.findByUsername(username).get();
    List<Advert> advertList = advertRepo.getAllByPersonId(person.getId());
    if (advertList == null) {
      throw new AdvertNotFoundException("У вас еще нет объявлений");
    }
    else {
      List<ShortAdvertDTO> advertDTOS = advertMapper.advertEntityListToShortAdvertDTOList(advertList);
      return advertDTOS;
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<ShortAdvertDTO> searchAdverts(SearchAdvertDTO searchAdvertDTO) {
    logger.info("Search adverts");
    return advertMapper.advertEntityListToShortAdvertDTOList(
        advertRepo.getAdvertsByTitleContains(searchAdvertDTO.getTitle()));
  }
}
