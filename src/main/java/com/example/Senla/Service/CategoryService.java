package com.example.Senla.Service;

import java.util.Set;

import com.example.Senla.DTO.FilterCategoryDTO;
import com.example.Senla.DTO.ShortAdvertDTO;
import com.example.Senla.Entity.Advert;
import com.example.Senla.Entity.Category;

/**
 * @author Ilyas Nigamatullin
 */
public interface CategoryService {

  Category addCategory(String name, Advert advert);

  Set<ShortAdvertDTO> filterByCategory(FilterCategoryDTO filterCategoryDTO);
}
