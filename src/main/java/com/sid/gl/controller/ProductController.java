package com.sid.gl.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.sid.gl.constant.ApiPath;
import com.sid.gl.dto.ProductRequest;
import com.sid.gl.dto.ProductResponse;
import com.sid.gl.services.ProductService;
import com.sid.gl.utils.Result;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = ApiPath.V1+ApiPath.PRODUCT)
@RequiredArgsConstructor
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

}
