package com.sid.gl.mapper;

import com.sid.gl.dto.ProductRequest;
import com.sid.gl.dto.ProductResponse;
import com.sid.gl.models.Product;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()
        );
    }

    public static Product toProduct(ProductRequest productRequest) {
       Product product = new Product();
       product.setName(productRequest.name());
       product.setDescription(productRequest.description());
       product.setPrice(productRequest.price());
       return product;
    }
    
}
