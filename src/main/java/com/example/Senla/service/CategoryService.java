package com.example.Senla.service;

import java.util.Set;

import com.example.Senla.dto.ShortAdvertDTO;
import com.example.Senla.entity.Advert;
import com.example.Senla.entity.Category;

/**
 * @author Ilyas Nigamatullin
 */
public interface CategoryService {

  Category addCategory(String name, Advert advert);

  Set<ShortAdvertDTO> filterByCategory(String filter);
}
