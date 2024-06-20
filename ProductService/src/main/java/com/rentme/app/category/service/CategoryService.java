package com.rentme.app.category.service;

import com.rentme.app.category.entity.Category;
import com.rentme.app.category.mapper.CategoryMapper;
import com.rentme.app.category.model.CategoryRequest;
import com.rentme.app.category.model.CategoryResponse;
import com.rentme.app.category.repository.CategoryRepository;
import com.rentme.app.exception.ProductException;
import com.rentme.app.util.GlobalResponse;
import com.rentme.app.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public GlobalResponse<Void> save(CategoryRequest request) {
        var category = CategoryMapper.toEntity(request);
        categoryRepository.save(category);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<CategoryResponse> findById(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ProductException("Category is not exists by " + id));

        return
                GlobalResponse.success(
                        CategoryMapper.toResponse(category)
                );
    }

    @Override
    public GlobalResponse<CategoryResponse> findByName(String name) {
        var category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ProductException("Category name not exists by " + name));

        return
                GlobalResponse.success(
                        CategoryMapper.toResponse(category)
                );
    }

    @Override
    public GlobalResponse<List<CategoryResponse>> findAll(int page, int size) {
        Page<Category> categoryPage = categoryRepository.findAll(PageRequest.of(page, size));

        List<CategoryResponse> productResponses = categoryPage.getContent()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();

        return GlobalResponse.success(
                productResponses,
                Paging.builder()
                        .page(page)
                        .size(size)
                        .totalElement(categoryPage.getTotalElements())
                        .totalPage(categoryPage.getTotalPages())
                        .first(categoryPage.isFirst())
                        .last(categoryPage.isLast())
                        .build()
        );
    }

    @Override
    public GlobalResponse<Void> update(CategoryRequest request, Long id) {

        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ProductException("Category is not exists by " + id));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        categoryRepository.save(category);

        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<Void> delete(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ProductException("Category is not exists by " + id));

        categoryRepository.delete(category);

        return GlobalResponse.success();
    }
}