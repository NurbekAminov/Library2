package org.example.service;

import lombok.Setter;
import org.example.dto.CategoryDto;
import org.example.entity.CategoryEntity;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
@Setter
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Scanner scanner;
    public void getCategoryList() {
        List<CategoryDto> categoryDTOList = categoryRepository.getCategoryList();
        categoryDTOList.forEach(System.out::println);
    }

    public void addCategory() {
        System.out.print("ENTER CATEGORY NAME -> ");
        String name = scanner.next();
        CategoryEntity temp = categoryRepository.getCategoryByName(name);
        if (temp != null){
            System.out.println("This category already exists");
            return;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setVisible(true);
        categoryEntity.setCreatedDate(LocalDateTime.now());

        boolean result = categoryRepository.addCategory(categoryEntity);
        if (result){
            System.out.println("Category added");
        }else {
            System.out.println("Category not added");
        }
    }

    public void deleteCategory() {
        System.out.print("ENTER CATEGORY ID -> ");
        int id = scanner.nextInt();
        CategoryEntity categoryEntity = categoryRepository.getCategoryById(id);
        if (categoryEntity == null){
            System.out.println("Category not found");
            return;
        }
        if (!categoryEntity.getVisible()){
            System.out.println("This category already deleted");
            return;
        }
        int result = categoryRepository.changeVisibleCategory(id,false);
        if (result > 0){
            System.out.println("Category deleted");
        }else {
            System.out.println("Category not deleted");
        }
    }

    public void reactivationStatusCategory() {
        System.out.print("ENTER CATEGORY ID -> ");
        int id = scanner.nextInt();
        CategoryEntity categoryEntity = categoryRepository.getCategoryById(id);
        if (categoryEntity == null){
            System.out.println("Category not found");
            return;
        }
        if (categoryEntity.getVisible()){
            System.out.println("This category already reactivated");
            return;
        }
        int result = categoryRepository.changeVisibleCategory(id, true);
        if (result > 0){
            System.out.println("Category status reactivated");
        }else {
            System.out.println("Category not reactivated");
        }
    }
}

