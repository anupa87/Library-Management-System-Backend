package com.rest_api.fs14backend.category;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategoryById(UUID categoryId) {
    Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
    if (categoryOptional.isPresent()) {
      return categoryOptional.get();
    } else {
      throw new NotFoundException("Category not found");
    }
  }

  public Category addCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Category updateCategory(UUID categoryId, Category updatedCategory) {
    Category category = getCategoryById(categoryId);

    category.setName(updatedCategory.getName());
    return categoryRepository.save(category);
  }

  public void deleteCategory(UUID categoryId) {
    categoryRepository.deleteById(categoryId);
  }
}
