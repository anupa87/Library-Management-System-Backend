package com.rest_api.fs14backend.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping ("/categories")
  public Category addCategory(@RequestBody Category category) {
    return categoryService.addCategory(category);
  }

  @GetMapping ("/categories")
  public List<Category> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/categories/{categoryId}")
  public Category getCategoryById(@PathVariable UUID categoryId) {
    return categoryService.getCategoryById(categoryId);
  }

  @PutMapping("/categories/{categoryId}")
  public Category updateCategory(@PathVariable UUID categoryId, @RequestBody Category updatedCategory) {
    return categoryService.updateCategory(categoryId, updatedCategory);
  }

  @DeleteMapping("/categories/{categoryId}")
  public void deleteCategory(@PathVariable UUID categoryId) {
    categoryService.deleteCategory(categoryId);
  }
}