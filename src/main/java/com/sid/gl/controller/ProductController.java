package com.sid.gl.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.sid.gl.constant.ApiPath;
import com.sid.gl.dto.ProductRequest;
import com.sid.gl.dto.ProductResponse;
import com.sid.gl.exceptions.ProductNotFoundException;
import com.sid.gl.services.ProductService;
import com.sid.gl.utils.Result;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@CrossOrigin(origins = "http://app.moodd.xyz")
@RestController
@RequestMapping(value = ApiPath.V1+ApiPath.PRODUCT)
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product API")
public class ProductController {

    private final ProductService productService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Result<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<ProductResponse>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result<ProductResponse> getProduct(Long id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Result<ProductResponse> updateProduct(Long id, @RequestBody @Valid ProductRequest request) throws ProductNotFoundException {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> deleteProduct(Long id) throws ProductNotFoundException {
        return productService.deleteProduct(id);
    }

}
