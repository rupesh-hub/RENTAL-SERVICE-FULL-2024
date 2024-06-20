package com.rentme.app.product.service;

import com.rentme.app.category.repository.CategoryRepository;
import com.rentme.app.exception.ProductException;
import com.rentme.app.product.entity.Product;
import com.rentme.app.product.mapper.ProductMapper;
import com.rentme.app.product.model.ProductRequest;
import com.rentme.app.product.model.ProductResponse;
import com.rentme.app.product.model.PurchaseRequest;
import com.rentme.app.product.model.PurchaseResponse;
import com.rentme.app.product.repository.ProductRepository;
import com.rentme.app.util.GlobalResponse;
import com.rentme.app.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public GlobalResponse<Void> save(ProductRequest request) {

        String categoryName = request.getCategory();
        var category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new ProductException("Category not found by name " + categoryName));

        var product = ProductMapper.toEntity(request);
        product.setCategory(category);

        productRepository.save(product);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<ProductResponse> findById(Long id) {
        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductException("Product by " + id + " is not found."));
        return GlobalResponse.success(ProductMapper.toResponse(product));
    }

    @Override
    public GlobalResponse<List<ProductResponse>> findAll(int page, int size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));

        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            var productResponse = ProductMapper.toResponse(product);
            productResponses.add(productResponse);
        }

        return GlobalResponse.success(
                productResponses,
                Paging.builder()
                        .page(page)
                        .size(size)
                        .totalElement(productPage.getTotalElements())
                        .totalPage(productPage.getTotalPages())
                        .first(productPage.isFirst())
                        .last(productPage.isLast())
                        .build()
        );
    }

    @Override
    public GlobalResponse<Void> delete(Long id) {
        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductException("Product by " + id + " is not found."));
        productRepository.delete(product);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<Void> update(ProductRequest request, Long id) {
        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductException("Product by " + id + " is not found."));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        productRepository.save(product);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<List<ProductResponse>> findByCategory(int page, int limit, String category) {
        Page<Product> productPage = productRepository.findByCategory(category, PageRequest.of(page, limit));

        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            var productResponse = ProductMapper.toResponse(product);
            productResponses.add(productResponse);
        }

        return GlobalResponse.success(
                productResponses,
                Paging.builder()
                        .page(page)
                        .size(limit)
                        .totalElement(productPage.getTotalElements())
                        .totalPage(productPage.getTotalPages())
                        .first(productPage.isFirst())
                        .last(productPage.isLast())
                        .build()
        );
    }

    @Override
    public GlobalResponse<PurchaseResponse> purchase(PurchaseRequest request) {
        return null;
    }

}