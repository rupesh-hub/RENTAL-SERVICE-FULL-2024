package com.rentme.app.product.service;

import com.rentme.app.product.model.ProductRequest;
import com.rentme.app.product.model.ProductResponse;
import com.rentme.app.product.model.PurchaseRequest;
import com.rentme.app.product.model.PurchaseResponse;
import com.rentme.app.util.GlobalResponse;

import java.util.List;

public interface IProductService {

    GlobalResponse<Void> save(ProductRequest request);
    GlobalResponse<ProductResponse> findById(Long id);
    GlobalResponse<List<ProductResponse>> findAll(int page, int size);
    GlobalResponse<Void> delete(Long id);
    GlobalResponse<Void> update(ProductRequest request, Long id);
    GlobalResponse<List<ProductResponse>> findByCategory(int page, int limit, String category);

    GlobalResponse<PurchaseResponse> purchase(PurchaseRequest request);
}
