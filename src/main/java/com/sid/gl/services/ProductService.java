package com.sid.gl.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sid.gl.dto.ProductRequest;
import com.sid.gl.dto.ProductResponse;
import com.sid.gl.exceptions.ProductNotFoundException;
import com.sid.gl.mapper.ProductMapper;
import com.sid.gl.repository.ProductRepository;
import com.sid.gl.utils.ApiMessage;
import com.sid.gl.utils.Message;
import com.sid.gl.utils.MessageStatus;
import com.sid.gl.utils.Result;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Result<ProductResponse> createProduct(ProductRequest productRequest) {
        log.info("creating product ProductRequest: {}", productRequest);
        ProductResponse response= ProductMapper.toProductResponse(productRepository.save(ProductMapper.toProduct(productRequest)));
        return Result.createResultWithBody(HttpStatus.CREATED.value(),new Message(ApiMessage.CREATE_PRODUCT_SUCCESS, MessageStatus.INFO) , response);
    }

    public Result<ProductResponse> getProduct(Long id) throws ProductNotFoundException {
        ProductResponse productResponse = ProductMapper.toProductResponse(productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found with id: " + id)
        ));
        return Result.createResultWithBody(HttpStatus.OK.value(),new Message(ApiMessage.PRODUCT_FOUND, MessageStatus.INFO) , productResponse);
    }

    public Result<ProductResponse> updateProduct(Long id, ProductRequest productRequest) throws ProductNotFoundException {
        ProductResponse response= ProductMapper.toProductResponse(productRepository.findById(id).map(product -> {
            product.setName(productRequest.name());
            product.setDescription(productRequest.description());
            product.setPrice(productRequest.price());
            return productRepository.save(product);
        }).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id)));

        return Result.createResultWithBody(HttpStatus.OK.value(),new Message(ApiMessage.UPDATE_PRODUCT_SUCCESS, MessageStatus.INFO) , response);
    }

    public Result<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponses = productRepository.findAll().stream().map(ProductMapper::toProductResponse).toList();
        return Result.createResultWithBody(HttpStatus.OK.value(),new Message(ApiMessage.GET_PRODUCT_SUCCESS, MessageStatus.INFO) , productResponses);
    }

    public Result<Void> deleteProduct(Long id) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
        return Result.createResultWithoutBody(HttpStatus.OK.value(),new Message(ApiMessage.DELETE_PRODUCT_SUCCESS, MessageStatus.INFO));
    }



}
