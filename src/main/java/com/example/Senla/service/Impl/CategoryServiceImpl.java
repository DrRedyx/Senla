package com.example.Senla.service.Impl;

import java.util.HashSet;
import java.util.Set;

import com.example.Senla.dto.ShortAdvertDTO;
import com.example.Senla.entity.Advert;
import com.example.Senla.entity.Category;
import com.example.Senla.exception.FindCategoryException;
import com.example.Senla.mapper.AdvertMapper;
import com.example.Senla.repository.CategoryRepo;
import com.example.Senla.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ilyas Nigamatullin
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
  @Autowired
  private final CategoryRepo categoryRepo;

  @Autowired
  private final AdvertMapper advertMapper;

  @Override
  public Category addCategory(String name, Advert advert) {
    logger.info("add category");
    if (categoryRepo.findByName(name).isPresent()) {
      Category category = categoryRepo.findByName(name).get();
      Set<Advert> adverts = category.getAdvertSet();
      adverts.add(advert);
      category.setAdvertSet(adverts);
      categoryRepo.save(category);
    }
    else {
      Category category = new Category();
      category.setName(name);
      Set<Advert> adverts = new HashSet<>();
      adverts.add(advert);
      category.setAdvertSet(adverts);
      categoryRepo.save(category);
    }
    return categoryRepo.findByName(name).get();
  }

  @Override
  public Set<ShortAdvertDTO> filterByCategory(String filter) {
    logger.info("Search by filter");
    Category category = categoryRepo.findByName(filter)
        .orElseThrow(() -> new FindCategoryException("Category not find"));
    Set<Advert> advertSet = category.getAdvertSet();
    return advertMapper.advertEntitySetToShortAdvertDTOSet(advertSet);
  }
}
