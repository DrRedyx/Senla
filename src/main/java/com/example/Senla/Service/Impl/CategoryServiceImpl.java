package com.example.Senla.Service.Impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.Senla.DTO.FilterCategoryDTO;
import com.example.Senla.DTO.ShortAdvertDTO;
import com.example.Senla.Entity.Advert;
import com.example.Senla.Entity.Category;
import com.example.Senla.Exception.FindCategoryException;
import com.example.Senla.Mapper.AdvertMapper;
import com.example.Senla.Repository.CategoryRepo;
import com.example.Senla.Service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
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

  @Transactional(readOnly = true)
  @Override
  public Set<ShortAdvertDTO> filterByCategory(FilterCategoryDTO filterCategoryDTO) {
    logger.info("Search by filter");
    Category category = categoryRepo.findByName(filterCategoryDTO.getName())
        .orElseThrow(() -> new FindCategoryException("Category not find"));
    Set<Advert> advertSet = category.getAdvertSet();
    return advertMapper.advertEntitySetToShortAdvertDTOSet(advertSet);
  }
}
