
package com.example.YouTube.controller;

import com.example.YouTube.dto.CategoryDTO;
import com.example.YouTube.enums.AppLanguage;
import com.example.YouTube.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/adm/create")
    @Operation(summary = "Api for create", description = "this api is used to create category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDTO dto) {
        log.info("Create category {}", dto.getName());
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PostMapping("/adm/update/{id}")
    @Operation(summary = "Api for update", description = "this api is used to update category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer categoryId,
                                    @Valid @RequestBody CategoryDTO dto,
                                    @RequestHeader(value = "Accept-Language",defaultValue = "EN")
                                    AppLanguage language) {
        log.info("Update category{}", dto.getName());
        return ResponseEntity.ok(categoryService.update(categoryId, dto,language));
    }

    @DeleteMapping("/adm/delete/{id}")
    @Operation(summary = "Api for delete", description = "this api is used to delete category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer categoryId,
                                    @RequestHeader(value = "Accept-Language",defaultValue = "EN")AppLanguage language) {
        log.info("delete category");
        return ResponseEntity.ok(categoryService.delete(categoryId,language));
    }

    @GetMapping("/any/categoryList")
    @Operation(summary = "This API returns all categories in list View")
    public ResponseEntity<List<CategoryDTO>>getList(){
        return ResponseEntity.ok(categoryService.getList());
    }



}
