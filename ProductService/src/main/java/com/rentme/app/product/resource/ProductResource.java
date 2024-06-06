package com.rentme.app.product.resource;

import com.rentme.app.product.model.ProductRequest;
import com.rentme.app.product.model.ProductResponse;
import com.rentme.app.product.service.IProductService;
import com.rentme.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "products")
public class ProductResource {


    private final IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<Void>> save(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.save(request));
    }

    @GetMapping("/by.id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<ProductResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<List<ProductResponse>>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(productService.findAll(page, size));
    }

    @GetMapping("by.category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<List<ProductResponse>>> getByCategory(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int limit,
            @PathVariable String category
    ) {
        return ResponseEntity.ok(productService.findByCategory(page, limit, category));
    }


    @PutMapping
    public ResponseEntity<GlobalResponse<Void>> update(@RequestBody ProductRequest request,
                                                       @RequestParam(name = "id") Long id) {

        return ResponseEntity.ok(productService.update(request, id));
    }

    @DeleteMapping
    public ResponseEntity<GlobalResponse<Void>> delete(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }


}